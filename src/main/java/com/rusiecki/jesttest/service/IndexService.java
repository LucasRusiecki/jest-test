package com.rusiecki.jesttest.service;

import com.rusiecki.jesttest.model.IndexSettings;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Cat;
import io.searchbox.indices.CreateIndex;
import org.elasticsearch.common.settings.Settings;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class IndexService {

    private final JestClient client;

    public IndexService(final JestClient client) {
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

    public void createIndex(IndexSettings settings) {
        Settings.Builder settingsBuilder = Settings.builder();
        settingsBuilder.put("number_of_shards", settings.getNumberOfShards());
        settingsBuilder.put("number_of_replicas", settings.getNumberOfReplicas());
        try {
            client.execute(new CreateIndex.Builder(settings.getName()).settings(settingsBuilder.build().getAsMap()).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean deleteAll() {
        return false;
    }

    // getting index details, dropping and creating an index
}
