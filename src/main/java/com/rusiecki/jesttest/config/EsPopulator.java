package com.rusiecki.jesttest.config;

import io.searchbox.client.JestClient;
import io.searchbox.indices.CreateIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class EsPopulator {

    @Autowired
    private JestClient client;

    @PostConstruct
    public void init(){
        try {
            client.execute(new CreateIndex.Builder("articles").build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
