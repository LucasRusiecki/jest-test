package com.rusiecki.jesttest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.searchbox.annotations.JestId;
import lombok.Value;

import java.util.List;

@Value
public class Article implements BaseDto {
    @JestId
    private String id;
    private String title;
    private String body;
    private List<Link> links;
    private List<String> tags;

    public Article(
            @JsonProperty("id") String id,
            @JsonProperty("title") String title,
            @JsonProperty("body") String body,
            @JsonProperty("links") List<Link> links,
            @JsonProperty("tags") List<String> tags
    ) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.links = links;
        this.tags = tags;
    }

    private Article(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.body = builder.body;
        this.links = builder.links;
        this.tags = builder.tags;
    }

    public static Builder newArticle() {
        return new Builder();
    }

    public static final class Builder implements DocumentBuilder<Article> {
        private String id;
        private String title;
        private String body;
        private List<Link> links;
        private List<String> tags;

        private Builder() {
        }

        public Article build() {
            return new Article(this);
        }

        @Override
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public DocumentBuilder copy(Article article) {
            this.id = article.id;
            this.title = article.title;
            this.body = article.body;
            this.links = article.links;
            this.tags = article.tags;
            return this;
        }
    }
}
