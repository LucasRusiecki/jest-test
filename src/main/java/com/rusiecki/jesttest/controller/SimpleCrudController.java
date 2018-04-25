package com.rusiecki.jesttest.controller;

import com.rusiecki.jesttest.model.BaseDto;
import com.rusiecki.jesttest.service.SimpleCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public abstract class SimpleCrudController<T extends BaseDto, U extends SimpleCrudService<T>> {

    private final String basePath;

    @Autowired
    private U service;

    protected SimpleCrudController(String basePath) {
        this.basePath = basePath;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity getAll() {
        List<T> responseList = service.findAll();
        if (responseList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable("id") String id) {
        T response = service.findById(id);
        if (response == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody T input, UriComponentsBuilder ucBuilder) {
        HttpHeaders headers = new HttpHeaders();
        service.save(input);
        headers.setLocation(ucBuilder.path(basePath + "/{id}").buildAndExpand(input.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody T input) {
        input.setId(id);
        if (service.save(input)) {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") String id) {
        return service.delete(id) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity deleteAll() {
        return service.deleteAll() ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
