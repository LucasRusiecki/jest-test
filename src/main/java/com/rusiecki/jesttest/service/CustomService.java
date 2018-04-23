package com.rusiecki.jesttest.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.rusiecki.jesttest.model.Article;
import com.rusiecki.jesttest.model.BaseDto;
import com.rusiecki.jesttest.model.Post;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Search;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CustomService {
    @Autowired
    private JestClient client;

    public List findAll() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(ArticleService.INDEX)
                .addIndex(PostService.INDEX)
                .addType(SimpleCrudService.TYPE)
                .build();
        JestResult result;
        try {
            result = client.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        List<BaseDto> resultList = new ArrayList<>();
        JsonArray jsonArray = result.getJsonObject().get("hits").getAsJsonObject().get("hits").getAsJsonArray();
        for (JsonElement jsonElement : jsonArray) {
            String index = jsonElement.getAsJsonObject().get("_index").getAsString();
            if (index.equals(ArticleService.INDEX)) {
                resultList.add(createObject(jsonElement, Article.class));
            } else if (index.equals(PostService.INDEX)) {
                resultList.add(createObject(jsonElement, Post.class));
            }
        }
        return resultList;
    }

    private <T extends BaseDto> T createObject(JsonElement jsonElement, Class<T> clazz) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String source = jsonElement.getAsJsonObject().get("_source").toString();
            T object = mapper.readValue(source, clazz);
            object.setId(jsonElement.getAsJsonObject().get("_id").getAsString());
            return object;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
