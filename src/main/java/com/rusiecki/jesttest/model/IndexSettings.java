package com.rusiecki.jesttest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class IndexSettings {
    private String name;
    private int numberOfShards;
    private int numberOfReplicas;

    public IndexSettings(
            @JsonProperty("name") String name,
            @JsonProperty("numberOfShards") int numberOfShards,
            @JsonProperty("numberOfReplicas") int numberOfReplicas
    ) {
        this.name = name;
        this.numberOfShards = numberOfShards;
        this.numberOfReplicas = numberOfReplicas;
    }
}
