package com.rusiecki.jesttest.model;

import lombok.Data;

import java.util.List;

@Data
public class Article {
    private String title;
    private String body;
//    private List<Link> links;
    private List<String> tags;

}
