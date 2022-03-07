package com.sub.service;

import com.sub.entity.respone.Response;

import java.util.List;

public interface ResponseService {


    List<Response> getAllResponse ();

    List<Response> getGodRequest();

    List<Response> getBadRequest();

    Response getSingleResponse(Long id);

    Response addResponse (Response response);

    void deleteAllResponse();

    void saveAllResponses (List<Response> responses);

    Response getSingeByAccount (String numberChecked);

    Response getSingleByName(String name);



}
