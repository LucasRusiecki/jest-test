package com.rusiecki.jesttest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
class Link {
    private String url;
    private String name;
    private String type;

    public Link(@JsonProperty("url") String url, @JsonProperty("name") String name, @JsonProperty("type") String type) {
        this.url = url;
        this.name = name;
        this.type = type;
    }
}
