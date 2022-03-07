package com.sub.controller;

import com.sub.entity.respone.DTO.CountsResp;
import com.sub.entity.respone.Response;
import com.sub.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TestController {

    private final ResponseService repo;

    @GetMapping("/counts")
    private CountsResp countsRespList(){
        return getCountsResp();
    }

    private CountsResp getCountsResp() {
        CountsResp countsResp = new CountsResp();
        List<Response> response =  repo.getAllResponse();
            long countAll = response.stream().count();
            long countBad = response
                    .stream()
                    .filter( c -> c.getMessage() != null )
                    .count();
            long countOk = response
                    .stream()
                    .filter( c -> c.getStatusVat() != null && c.getStatusVat().equals("Czynny") )
                    .count();
            long countNok = response
                    .stream()
                    .filter( c -> c.getStatusVat() != null && !c.getStatusVat().equals("Czynny") )
                    .count();
            countsResp.setChecked(countAll);
            countsResp.setOk(countOk);
            countsResp.setWrong(countNok);
            countsResp.setBad(countBad);
        return  countsResp;
    }
}
