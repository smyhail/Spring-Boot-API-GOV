package com.sub.service.util;

import com.lowagie.text.DocumentException;
import com.sub.entity.respone.Response;
import com.sub.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
@RequiredArgsConstructor
public class ZipService {

    private final PdfService pdfService;
    private final ResponseService responseService;

    public void generateZip(HttpServletResponse response) throws IOException, DocumentException {
        response.setStatus(HttpServletResponse.SC_OK);
        String date = LocalDate.now().toString();
        response.addHeader("Content-Disposition", "attachment; filename=\"Weryfikacja_" + date + ".zip\"");
        ZipOutputStream zout = new ZipOutputStream(response.getOutputStream());

        List<Response> getGodResponses = responseService.getGodRequest();

        for (Response getGodRespons : getGodResponses) {
            Long id = getGodRespons.getId();
            //String generatedString = RandomStringUtils.randomAlphanumeric(4);

            String namePdf = getGodRespons.getNumberChecked()  ;
            Path path = Paths.get(pdfService.genPDF(id).getAbsolutePath());
            byte[] fileContent = Files.readAllBytes(path);
            String generatedString = RandomStringUtils.randomAlphanumeric(4);
            System.out.println(generatedString);
            ZipEntry zip = new ZipEntry(namePdf +"_" + generatedString +".pdf");
            zout.putNextEntry(zip);
            zout.write(fileContent);
            zout.closeEntry();

        }

        zout.close();
    }

}
