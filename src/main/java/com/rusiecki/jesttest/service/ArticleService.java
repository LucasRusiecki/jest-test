package com.rusiecki.jesttest.service;

import com.rusiecki.jesttest.model.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleService extends SimpleCrudService<Article> {

    private static final String INDEX = "articles";

    @Override
    String getIndex() {
        return INDEX;
    }

    @Override
    Class<Article> getClazz() {
        return Article.class;
    }
}
