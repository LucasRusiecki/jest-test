package com.rusiecki.jesttest.controller;

import com.rusiecki.jesttest.model.BaseDto;
import com.rusiecki.jesttest.model.DocumentBuilder;
import com.rusiecki.jesttest.service.SimpleCrudService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

public abstract class SimpleCrudController<T extends BaseDto, U extends SimpleCrudService<T>> {

    private final String basePath;
    private final U service;

    SimpleCrudController(final String basePath, final U service) {
        this.basePath = basePath;
        this.service = service;
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
    public ResponseEntity get(@PathVariable("id") final String id) {
        T response = service.findById(id);
        if (response == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody final T input, final UriComponentsBuilder ucBuilder) {
        HttpHeaders headers = new HttpHeaders();
        String id = service.savePost(input);
        if (id != null) {
            headers.setLocation(ucBuilder.path(basePath + "/{id}").buildAndExpand(id).toUri());
            return new ResponseEntity<String>(headers, HttpStatus.CREATED);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable("id") final String id, @RequestBody final T input) {
        DocumentBuilder documentBuilder = SimpleRestPath.getByPath(basePath).getBuilder().get();
        T objectWithId = (T) documentBuilder.id(id).copy(input).build();
        if (service.saveGet(objectWithId)) {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        }
        return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable("id") final String id) {
        return service.delete(id) ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity deleteAll() {
        return service.deleteAll() ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
