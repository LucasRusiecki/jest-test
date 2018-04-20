package com.rusiecki.jesttest.model;

import io.searchbox.annotations.JestId;
import lombok.Data;

import java.util.List;

@Data
public class Article implements BaseDto {
    @JestId
    private Long id;
    private String title;
    private String body;
    //    private List<Link> links;
    private List<String> tags;

}
