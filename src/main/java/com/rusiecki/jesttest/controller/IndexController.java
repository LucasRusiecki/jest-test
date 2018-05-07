package com.rusiecki.jesttest.controller;

import com.rusiecki.jesttest.service.IndexService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(IndexController.BASE_PATH)
public class IndexController {

    static final String BASE_PATH = "/indexes";

    private final IndexService service;

    public IndexController(final IndexService service) {
        this.service = service;
    }

    @RequestMapping(value = "/")
    public ResponseEntity allIndexesInfo() {
        String response = service.allIndexesInfo();
        if (response.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
}
