package com.rusiecki.jesttest.controller;

import com.rusiecki.jesttest.model.Article;
import com.rusiecki.jesttest.model.DocumentBuilder;
import com.rusiecki.jesttest.model.Post;
import lombok.Getter;

import java.util.Arrays;
import java.util.function.Supplier;

@Getter
public enum SimpleRestPath {
    ARTICLE(ArticleCrudController.BASE_PATH, Article.class, Article::newArticle),
    POST(PostCrudController.BASE_PATH, Post.class, Post::newPost);

    private final String path;
    private final Class clazz;
    private final Supplier<DocumentBuilder> builder;

    SimpleRestPath(final String path, Class clazz, final Supplier<DocumentBuilder> builder) {
        this.path = path;
        this.clazz = clazz;
        this.builder = builder;
    }

    public static SimpleRestPath getByPath(final String path){
        return Arrays.stream(values()).filter(simpleRestPath -> simpleRestPath.path.equals(path)).findFirst().orElse(null);
    }

    public static SimpleRestPath getByClass(final Class clazz){
        return Arrays.stream(values()).filter(simpleRestPath -> simpleRestPath.clazz.equals(clazz)).findFirst().orElse(null);
    }
}
