package com.rusiecki.jesttest.controller;

import com.rusiecki.jesttest.service.DocumentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(DocumentController.BASE_PATH)
public class DocumentController {

    static final String BASE_PATH = "/documents";

    private final DocumentService service;

    public DocumentController(final DocumentService service) {
        this.service = service;
    }

    @RequestMapping(value = "/")
    public ResponseEntity findAll() {
        List responseList = service.findAll();
        if (responseList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);

    }

    @RequestMapping(value = "/{indexes}/{text}", method = RequestMethod.GET)
    public ResponseEntity search(@PathVariable final String indexes, @PathVariable final String text) {
        // guava preconditions
        List responseList = service.search(indexes.split(","), text);
        if (responseList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity search(@RequestParam(value = "index", required = false, defaultValue = "") final String[] index,
                                 @RequestParam(value = "text") final String text) {
        List responseList = service.search(index, text);
        if (responseList.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(responseList, HttpStatus.OK);

    }
}
