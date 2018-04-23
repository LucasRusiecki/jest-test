package com.rusiecki.jesttest.service;

import java.util.List;

public interface SimpleCrudService<T> {
    List<T> findAll();

    T findById(String id);

    void save(Object object);

    void delete(String id);

    void deleteAll();
}
