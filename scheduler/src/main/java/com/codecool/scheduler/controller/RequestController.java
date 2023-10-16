package com.codecool.scheduler.controller;

import com.codecool.scheduler.dto.RequestDTO;
import com.codecool.scheduler.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/request")
public class RequestController {
    private final RequestService requestService;
    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }
    @PostMapping("")
    public void addRequest(@RequestBody List<RequestDTO> requestDTOlist){
        requestService.handleRequests(requestDTOlist);
    }
}
