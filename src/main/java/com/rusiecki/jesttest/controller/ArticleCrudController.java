package com.rusiecki.jesttest.controller;

import com.rusiecki.jesttest.model.Article;
import com.rusiecki.jesttest.service.ArticleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(ArticleCrudController.BASE_PATH)
public class ArticleCrudController extends SimpleCrudController<Article, ArticleService> {

    public static final String BASE_PATH = "/articles";

    protected ArticleCrudController() {
        super(BASE_PATH);
    }
}
