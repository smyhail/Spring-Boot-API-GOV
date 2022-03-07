package com.sub.controller;


import com.lowagie.text.DocumentException;
import com.sub.service.util.ZipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ZipController {

    private final ZipService zipService;

    @GetMapping("/generateZipWithPdf")
    public void z2(HttpServletResponse response) throws IOException, DocumentException {
        zipService.generateZip(response);
    }



}
