package com.rusiecki.jesttest.service;

import com.rusiecki.jesttest.model.Post;
import io.searchbox.client.JestClient;
import org.springframework.stereotype.Component;

@Component
public class PostService extends SimpleCrudService<Post> {

    static final String INDEX = "posts";

    public PostService(final JestClient client) {
        super(client, INDEX, Post.class);
    }
}
