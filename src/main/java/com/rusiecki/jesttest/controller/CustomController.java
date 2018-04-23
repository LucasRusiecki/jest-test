package com.rusiecki.jesttest.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PostCrudController.BASE_PATH)
public class CustomController {

    public static final String BASE_PATH = "/custom/";

    @RequestMapping
    public void findAll(){

    }
}
