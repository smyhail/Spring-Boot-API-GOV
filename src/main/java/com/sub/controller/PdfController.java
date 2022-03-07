package com.sub.controller;

import com.sub.service.util.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class PdfController {

    private final PdfService pdfService;


    @GetMapping("/createSimplePdf/{id}")
    public void downloadPDFResource3(HttpServletResponse httpServletResponse, Long id) {
        pdfService.makePdf(httpServletResponse, id);
    }


}
