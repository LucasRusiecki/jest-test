package com.rusiecki.jesttest.service;

import com.rusiecki.jesttest.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostService extends SimpleCrudService<Post> {

    public static final String INDEX = "posts";

    public PostService() {
        super(INDEX, Post.class);
    }
}
