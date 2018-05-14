package com.rusiecki.jesttest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.searchbox.annotations.JestId;
import lombok.Value;

@Value
public class Post implements BaseDto {
    @JestId
    private String id;
    private String userName;
    private String body;

    public Post(@JsonProperty("id") String id, @JsonProperty("userName") String userName, @JsonProperty("body") String body) {
        this.id = id;
        this.userName = userName;
        this.body = body;
    }

    private Post(Builder builder) {
        this.id = builder.id;
        this.userName = builder.userName;
        this.body = builder.body;
    }

    public static Builder newPost() {
        return new Builder();
    }

    public static final class Builder implements DocumentBuilder<Post> {
        private String id;
        private String userName;
        private String body;

        private Builder() {
        }

        public Post build() {
            return new Post(this);
        }

        @Override
        public Builder id(String id) {
            this.id = id;
            return this;
        }

        @Override
        public DocumentBuilder copy(Post post) {
            this.id = post.id;
            this.userName = post.userName;
            this.body = post.body;
            return this;
        }
    }
}
