package com.rusiecki.jesttest.service;

import com.rusiecki.jesttest.model.Article;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ArticleService implements SimpleCrudService<Article> {
    @Override
    public List<Article> findAll() {
        return new ArrayList<>();
    }

    @Override
    public Article findById(long id) {
        return null;
    }

    @Override
    public void save(Object object) {

    }
}
