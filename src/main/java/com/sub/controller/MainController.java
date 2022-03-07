package com.sub.controller;

import com.sub.entity.reguest.Reguest;
import com.sub.controller.DAO.ReqBody;
import com.sub.entity.respone.Response;
import com.sub.service.ResponseService;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RestController

@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:3001"})
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MainController {

    //public static final String URL = "https://wl-test.mf.gov.pl/api/search/bank-account/";
    public static final String URL = "https://wl-api.mf.gov.pl/api/search/bank-account/";
    private final RestTemplate restTemplate;
    private final ResponseService responseService;
    private String date;


    @GetMapping("/responses")
    public List<Response> getResponses(){
        return responseService.getAllResponse();
    }

    @GetMapping("/responses/ok")
    public List<Response> getGodResponses(){
        return responseService.getGodRequest();
    }

    @GetMapping("/responses/nok")
    public List<Response> getBadResponses(){
        return responseService.getBadRequest();
    }

    @GetMapping("/responses/{id}")
    public Response getSingleResponse(Long id){
        return responseService.getSingleResponse(id);
    }

    @PostMapping("/responsesParam")
    public List<Response> addMultipleResponse(@RequestParam String[] accounts, @RequestParam(required = false) String localDate ) throws JSONException {
        checkParamDate(localDate);
        List<Response> fullResponse = new ArrayList<>();
        for (String account : accounts) {
            Response response = new Response();

            try {
                Reguest reguest = restTemplate.getForObject(
                        URL + account + "?date=" + date, Reguest.class);

                System.out.println("request   " + reguest.toString());
                try {

                    String adress = reguest.getResult().getSubjects().get(0).getWorkingAddress();
                    System.out.println("adress = " + adress);


                    response.setNumberChecked(account);
                    response.setCheckedOnDate(date);

                    response.setName(reguest.getResult().getSubjects().get(0).getName());

                    response.setNip(reguest.getResult().getSubjects().get(0).getNip());
                    response.setStatusVat(reguest.getResult().getSubjects().get(0).getStatusVat());
                    response.setRegon(reguest.getResult().getSubjects().get(0).getRegon());
                    response.setPesel(reguest.getResult().getSubjects().get(0).getPesel());
                    response.setKrs(reguest.getResult().getSubjects().get(0).getKrs());

                    response.setResidenceAddress(reguest.getResult().getSubjects().get(0).getResidenceAddress());
                    response.setWorkingAddress(reguest.getResult().getSubjects().get(0).getWorkingAddress());

                    response.setRepresentatives(String.valueOf(reguest.getResult().getSubjects().get(0).getResidenceAddress()));
                    response.setAuthorizedClerks(String.valueOf(reguest.getResult().getSubjects().get(0).getAuthorizedClerks()));
                    response.setPartners(String.valueOf(reguest.getResult().getSubjects().get(0).getPartners()));

                    response.setRegistrationLegalDate(reguest.getResult().getSubjects().get(0).getRegistrationLegalDate());
                    response.setRegistrationDenialBasis(String.valueOf(reguest.getResult().getSubjects().get(0).getRegistrationDenialBasis()));
                    response.setRegistrationDenialDate(String.valueOf(reguest.getResult().getSubjects().get(0).getRegistrationDenialDate()));

                    response.setRestorationBasis(String.valueOf(reguest.getResult().getSubjects().get(0).getRestorationBasis()));
                    response.setRestorationDate(String.valueOf(reguest.getResult().getSubjects().get(0).getRestorationDate()));

                    response.setRemovalBasis(String.valueOf(reguest.getResult().getSubjects().get(0).getRemovalBasis()));
                    response.setRemovalDate(String.valueOf(reguest.getResult().getSubjects().get(0).getRemovalDate()));

                    response.setAccountNumbers(reguest.getResult().getSubjects().get(0).getAccountNumbers());
                    response.setHasVirtualAccounts(reguest.getResult().getSubjects().get(0).getHasVirtualAccounts());
                    response.setRequestDateTime(reguest.getResult().getRequestDateTime());
                    response.setRequestId(reguest.getResult().getRequestId());

                } catch (Exception e) {
                    response.setMessage("Rachunek nie figuruje na wykazie");
                    response.setRequestDateTime(reguest.getResult().getRequestDateTime());
                    response.setRequestId(reguest.getResult().getRequestId());
                }
            } catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
                if (HttpStatus.BAD_REQUEST.equals(httpClientOrServerExc.getStatusCode())) {
                    String badRequest = httpClientOrServerExc.getMessage();
                    badRequest = badRequest.replaceFirst("400 : \"", "[");
                    badRequest = badRequest.substring(0, badRequest.length() - 1) + "]";
                    JSONArray array = new JSONArray(badRequest);
                    response.setNumberChecked(account);
                    response.setMessage(array.getJSONObject(0).getString("message"));
                    response.setCode(array.getJSONObject(0).getString("code"));
                } else if (response.getRequestId() != null) {
                    System.out.println("cos tam");
                }
            }

            fullResponse.add(response);
        }
        responseService.saveAllResponses(fullResponse);
        return fullResponse;
    }

    private void checkParamDate(String localDate) {
        if (localDate != null) {
            date = localDate;
        } else {
            date = LocalDate.now().toString();
        }
    }

    @PostMapping("/responsesBody2")
    public List<Response> addResponse2(@RequestBody ReqBody reqBody) throws JSONException {
        List<Response> fullResponse = new ArrayList<>();
        String ld = reqBody.getLocalDate();
        for(int i = 0; i < reqBody.getAccount().size(); i++){
            Response response = new Response();
            String account =  reqBody.getAccount().get(i);
            System.out.println("account: "  + account);
            try {
                Reguest reguest = restTemplate.getForObject(
                        URL + account + "?date=" + ld, Reguest.class);
                System.out.println("request   " + reguest.toString());


                try {
                    String adress = reguest.getResult().getSubjects().get(0).getWorkingAddress();
                    System.out.println("adress = " + adress);


                    response.setNumberChecked(account);
                    response.setCheckedOnDate(ld);

                    response.setName(reguest.getResult().getSubjects().get(0).getName());

                    response.setNip(reguest.getResult().getSubjects().get(0).getNip());
                    response.setStatusVat(reguest.getResult().getSubjects().get(0).getStatusVat());
                    response.setRegon(reguest.getResult().getSubjects().get(0).getRegon());
                    response.setPesel(reguest.getResult().getSubjects().get(0).getPesel());
                    response.setKrs(reguest.getResult().getSubjects().get(0).getKrs());

                    response.setResidenceAddress(reguest.getResult().getSubjects().get(0).getResidenceAddress());
                    response.setWorkingAddress(reguest.getResult().getSubjects().get(0).getWorkingAddress());

                    response.setRepresentatives(String.valueOf(reguest.getResult().getSubjects().get(0).getResidenceAddress()));
                    response.setAuthorizedClerks(String.valueOf(reguest.getResult().getSubjects().get(0).getAuthorizedClerks()));
                    response.setPartners(String.valueOf(reguest.getResult().getSubjects().get(0).getPartners()));

                    response.setRegistrationLegalDate(reguest.getResult().getSubjects().get(0).getRegistrationLegalDate());
                    response.setRegistrationDenialBasis(String.valueOf(reguest.getResult().getSubjects().get(0).getRegistrationDenialBasis()));
                    response.setRegistrationDenialDate(String.valueOf(reguest.getResult().getSubjects().get(0).getRegistrationDenialDate()));

                    response.setRestorationBasis(String.valueOf(reguest.getResult().getSubjects().get(0).getRestorationBasis()));
                    response.setRestorationDate(String.valueOf(reguest.getResult().getSubjects().get(0).getRestorationDate()));

                    response.setRemovalBasis(String.valueOf(reguest.getResult().getSubjects().get(0).getRemovalBasis()));
                    response.setRemovalDate(String.valueOf(reguest.getResult().getSubjects().get(0).getRemovalDate()));

                    response.setAccountNumbers(reguest.getResult().getSubjects().get(0).getAccountNumbers());
                    response.setHasVirtualAccounts(reguest.getResult().getSubjects().get(0).getHasVirtualAccounts());
                    response.setRequestDateTime(reguest.getResult().getRequestDateTime());
                    response.setRequestId(reguest.getResult().getRequestId());

                } catch (Exception e) {
                    response.setNumberChecked(account);
                    response.setMessage("Rachunek nie figuruje na wykazie");
                    response.setRequestDateTime(reguest.getResult().getRequestDateTime());
                    response.setRequestId(reguest.getResult().getRequestId());
                }




            } catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
                if (HttpStatus.BAD_REQUEST.equals(httpClientOrServerExc.getStatusCode())) {
                    String badRequest = httpClientOrServerExc.getMessage();
                    badRequest = badRequest.replaceFirst("400 : \"", "[");
                    badRequest = badRequest.substring(0, badRequest.length() - 1) + "]";
                    JSONArray array = new JSONArray(badRequest);
                    response.setNumberChecked(account);
                    response.setMessage(array.getJSONObject(0).getString("message"));
                    response.setCode(array.getJSONObject(0).getString("code"));
                } else if (response.getRequestId() != null) {
                    System.out.println("cos tam");
                }
            }
            fullResponse.add(response);
        }

        responseService.saveAllResponses(fullResponse);
        return fullResponse;
    }

    @PostMapping("/responsesBody")
    public List<Response> addResponse(@RequestBody List <ReqBody> reqBody) throws JSONException {
        List<Response> fullResponse = new ArrayList<>();
        //String ld = reqBody.get(0).getLocalDate();
        String ld = "2022-02-19";

        System.out.println("reqBody.size() "+ reqBody.size());
        int count = reqBody.get(0).getAccount().size();
        System.out.println("count" + count);
        for(int i = 0; i < reqBody.get(0).getAccount().size(); i++){
            Response response = new Response();
            String account =  reqBody.get(0).getAccount().get(i);
            System.out.println("account: "  + account);
            try {
                 Reguest reguest = restTemplate.getForObject(
                      URL + account + "?date=" + ld, Reguest.class);
                 System.out.println("request   " + reguest.toString());

                try {
                    String adress = reguest.getResult().getSubjects().get(0).getWorkingAddress();
                    System.out.println("adress = " + adress);


                    response.setNumberChecked(account);
                    response.setCheckedOnDate(ld);

                    response.setName(reguest.getResult().getSubjects().get(0).getName());

                    response.setNip(reguest.getResult().getSubjects().get(0).getNip());
                    response.setStatusVat(reguest.getResult().getSubjects().get(0).getStatusVat());
                    response.setRegon(reguest.getResult().getSubjects().get(0).getRegon());
                    response.setPesel(reguest.getResult().getSubjects().get(0).getPesel());
                    response.setKrs(reguest.getResult().getSubjects().get(0).getKrs());

                    response.setResidenceAddress(reguest.getResult().getSubjects().get(0).getResidenceAddress());
                    response.setWorkingAddress(reguest.getResult().getSubjects().get(0).getWorkingAddress());

                    response.setRepresentatives(String.valueOf(reguest.getResult().getSubjects().get(0).getResidenceAddress()));
                    response.setAuthorizedClerks(String.valueOf(reguest.getResult().getSubjects().get(0).getAuthorizedClerks()));
                    response.setPartners(String.valueOf(reguest.getResult().getSubjects().get(0).getPartners()));

                    response.setRegistrationLegalDate(reguest.getResult().getSubjects().get(0).getRegistrationLegalDate());
                    response.setRegistrationDenialBasis(String.valueOf(reguest.getResult().getSubjects().get(0).getRegistrationDenialBasis()));
                    response.setRegistrationDenialDate(String.valueOf(reguest.getResult().getSubjects().get(0).getRegistrationDenialDate()));

                    response.setRestorationBasis(String.valueOf(reguest.getResult().getSubjects().get(0).getRestorationBasis()));
                    response.setRestorationDate(String.valueOf(reguest.getResult().getSubjects().get(0).getRestorationDate()));

                    response.setRemovalBasis(String.valueOf(reguest.getResult().getSubjects().get(0).getRemovalBasis()));
                    response.setRemovalDate(String.valueOf(reguest.getResult().getSubjects().get(0).getRemovalDate()));

                    response.setAccountNumbers(reguest.getResult().getSubjects().get(0).getAccountNumbers());
                    response.setHasVirtualAccounts(reguest.getResult().getSubjects().get(0).getHasVirtualAccounts());
                    response.setRequestDateTime(reguest.getResult().getRequestDateTime());
                    response.setRequestId(reguest.getResult().getRequestId());

                } catch (Exception e) {
                    response.setNumberChecked(account);
                    response.setMessage("Rachunek nie figuruje na wykazie");
                    response.setRequestDateTime(reguest.getResult().getRequestDateTime());
                    response.setRequestId(reguest.getResult().getRequestId());
                }
            } catch (HttpClientErrorException | HttpServerErrorException httpClientOrServerExc) {
                if (HttpStatus.BAD_REQUEST.equals(httpClientOrServerExc.getStatusCode())) {
                    String badRequest = httpClientOrServerExc.getMessage();
                    badRequest = badRequest.replaceFirst("400 : \"", "[");
                    badRequest = badRequest.substring(0, badRequest.length() - 1) + "]";
                    JSONArray array = new JSONArray(badRequest);
                    response.setNumberChecked(account);
                    response.setMessage(array.getJSONObject(0).getString("message"));
                    response.setCode(array.getJSONObject(0).getString("code"));
                } else if (response.getRequestId() != null) {
                    System.out.println("cos tam");
                }
            }
            fullResponse.add(response);
        }


        responseService.saveAllResponses(fullResponse);
        return fullResponse;


    }



    @DeleteMapping("/responses")
    public void deleteAll(){
        responseService.deleteAllResponse();
    }

    @GetMapping("/responses/name/{id}")
    public Response getSingleByName(String name){
        return responseService.getSingleByName(name);
    }


}
