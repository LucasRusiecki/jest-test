package com.rusiecki.jesttest.service;

import com.rusiecki.jesttest.model.Article;
import org.springframework.stereotype.Component;

@Component
public class ArticleService extends SimpleCrudService<Article> {

    public static final String INDEX = "articles";

    public ArticleService() {
        super(INDEX, Article.class);
    }
}
