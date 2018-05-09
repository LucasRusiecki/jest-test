package com.rusiecki.jesttest.controller;

import com.rusiecki.jesttest.model.IndexSettings;
import com.rusiecki.jesttest.service.IndexService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @RequestMapping(value = "/", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity createIndex(@RequestBody final IndexSettings settings){
        service.createIndex(settings);
        return null;
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity deleteAll() {
        return service.deleteAll() ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
