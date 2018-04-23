package com.rusiecki.jesttest.model;

import io.searchbox.annotations.JestId;
import lombok.Data;

@Data
public class Post implements BaseDto {
    @JestId
    private String id;
    private String userName;
    private String body;
}
