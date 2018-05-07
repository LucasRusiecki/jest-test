package com.rusiecki.jesttest.service;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Cat;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class IndexService {

    private final JestClient client;

    public IndexService(JestClient client) {
        this.client = client;
    }

    public String allIndexesInfo() {
        Cat.IndicesBuilder indicesBuilder = new Cat.IndicesBuilder();
        JestResult result;
        try {
            result = client.execute(indicesBuilder.build());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return result.getJsonString();
    }

    // getting index details, dropping and creating an index
}
