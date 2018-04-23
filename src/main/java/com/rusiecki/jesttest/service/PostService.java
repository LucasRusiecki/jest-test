package com.rusiecki.jesttest.service;

import com.rusiecki.jesttest.model.Post;
import org.springframework.stereotype.Component;

@Component
public class PostService extends SimpleCrudService<Post> {

    private static final String INDEX = "posts";

    @Override
    String getIndex() {
        return INDEX;
    }

    @Override
    Class<Post> getClazz() {
        return Post.class;
    }
}
