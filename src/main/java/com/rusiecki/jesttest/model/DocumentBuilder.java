package com.rusiecki.jesttest.model;

public interface DocumentBuilder<T extends BaseDto> {

    DocumentBuilder id(String id);

    DocumentBuilder copy(T document);

    T build();
}
