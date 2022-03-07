package com.sub.service;

import com.sub.entity.respone.Response;
import com.sub.repository.ResponseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ImplResponseService implements ResponseService {

    private final ResponseRepository repo;

    @Override
    public List<Response> getAllResponse (){
        return repo.findAll();
    }

    @Override
    public List<Response> getGodRequest() {
        return repo.findGodResponse();
    }

    @Override
    public List<Response> getBadRequest() {
        return repo.findBadResponse();
    }

    @Override
    public Response getSingleResponse(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public Response addResponse (Response response){
        return repo.save(response);
    }

    @Override
    public void deleteAllResponse() {
        repo.deleteAll();
    }

    @Override
    public void saveAllResponses (List<Response> responses){
        repo.saveAll(responses);
   }

    @Override
    public Response getSingeByAccount(String numberChecked) {
        return repo.getSingleByNumberChecked(numberChecked);
    }

    @Override
    public Response getSingleByName(String name) {
        return repo.getSingleByName(name);
    }


}
