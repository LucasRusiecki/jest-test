package com.rusiecki.jesttest.controller;

import com.rusiecki.jesttest.model.Post;
import com.rusiecki.jesttest.service.PostService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(PostCrudController.BASE_PATH)
public class PostCrudController extends SimpleCrudController<Post, PostService> {

    static final String BASE_PATH = "/posts";

    protected PostCrudController(final PostService postService) {
        super(BASE_PATH, postService);
    }
}
