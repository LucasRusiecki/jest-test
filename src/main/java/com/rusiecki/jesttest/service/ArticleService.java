package com.rusiecki.jesttest.service;

import com.rusiecki.jesttest.model.Article;
import io.searchbox.client.JestClient;
import org.springframework.stereotype.Component;

@Component
public class ArticleService extends SimpleCrudService<Article> {

    static final String INDEX = "articles";

    public ArticleService(final JestClient client) {
        super(client, INDEX, Article.class);
    }
}
