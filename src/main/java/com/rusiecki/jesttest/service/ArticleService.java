package com.rusiecki.jesttest.service;

import com.rusiecki.jesttest.model.Article;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleService implements SimpleCrudService<Article> {

    @Autowired
    private JestClient client;

    @Override
    public List<Article> findAll() {
        String query = "{\n" +
                "    \"query\": {\n" +
                "        \"match_all\" : {\n" +
                "        }\n" +
                "    }\n" +
                "}";
        Search search = (Search) new Search.Builder(query)
                // multiple index or types can be added.
                .addIndex("articles")
                .build();
        JestResult result = null;
        try {
            result = client.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return result.getSourceAsObjectList(Article.class);
    }

    @Override
    public Article findById(long id) {
        return null;
    }

    @Override
    public void save(Object object) {
        Index index = new Index.Builder(object).index("articles").build();
        try {
            client.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
