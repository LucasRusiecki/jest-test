package com.rusiecki.jesttest.controller;

import com.rusiecki.jesttest.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(CustomController.BASE_PATH)
public class CustomController {

    public static final String BASE_PATH = "/custom/";

    @Autowired
    private CustomService service;

    @RequestMapping
    public ResponseEntity findAll(){
        List responseList = service.findAll();
        if (responseList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);

    }
}
