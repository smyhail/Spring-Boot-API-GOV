package com.sub.service.util;

import com.lowagie.text.DocumentException;
import com.lowagie.text.pdf.BaseFont;
import com.sub.entity.respone.Response;
import com.sub.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class PdfService {

    private static final String PDF_RESOURCES = "/static/";
    private final SpringTemplateEngine templateEngine;
    private final ResponseService responseService;

    public File genPDF(Long id) throws IOException, DocumentException {
        Context context = new Context();
        Response response = responseService.getSingleResponse(id);
        String account = response.getNumberChecked();
        context.setVariable("temp", response);
        String html = templateEngine.process("template", context);

        File file = File.createTempFile(account + "_"  , ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        /** repair polish characters */
        renderer.getFontResolver().addFont("static/font/verdana.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }


    public void makePdf(HttpServletResponse httpServletResponse, Long id) {
        try {
            Path file = Paths.get(genPDF(id).getAbsolutePath());
            if (Files.exists(file)) {
                httpServletResponse.setContentType("application/pdf");
                httpServletResponse.setCharacterEncoding("UTF-8");
                httpServletResponse.addHeader("Content-Disposition",
                        "attachment; filename=" + file.getFileName());
                Files.copy(file, httpServletResponse.getOutputStream());
                httpServletResponse.getOutputStream().flush();
            }
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }
    }

}
