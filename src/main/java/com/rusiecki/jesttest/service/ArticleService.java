package com.rusiecki.jesttest.service;

import com.rusiecki.jesttest.model.Article;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleService {
    public List<Article> findAll() {
        return new ArrayList<>();
    }

    public Article findById(long id) {
        return null;
    }
}
