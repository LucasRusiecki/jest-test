package com.rusiecki.jesttest.service;

import java.util.List;

public interface SimpleCrudService<T> {
    List<T> findAll();

    T findById(long id);

    void save(Object object);
}
