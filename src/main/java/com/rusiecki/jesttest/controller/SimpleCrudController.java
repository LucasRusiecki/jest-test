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

    @Autowired
    private U service;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAll() {
        List<T> responseList = service.findAll();
        if (responseList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);
    }

    @RequestMapping(value = "/_doc/{id}", method = RequestMethod.GET)
    public ResponseEntity get(@PathVariable("id") String id) {
        T response = service.findById(id);
        if (response == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody T input, UriComponentsBuilder ucBuilder, String basePath) {
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path(basePath + "{id}").buildAndExpand(input.getId()).toUri());
        service.save(input);
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/_doc/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody T input) {
        input.setId(id);
        service.save(input);
        return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
    }

    @RequestMapping(value = "/_doc/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") String id) {
        service.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity deleteAll() {
        service.deleteAll();
        return new ResponseEntity(HttpStatus.OK);
    }
}
