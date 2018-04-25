package com.rusiecki.jesttest.service;

import com.rusiecki.jesttest.model.BaseDto;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
import io.searchbox.core.DocumentResult;
import io.searchbox.core.Index;
import io.searchbox.core.Search;
import io.searchbox.indices.DeleteIndex;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class SimpleCrudService<T extends BaseDto> {

    public static final String TYPE = "_doc";
    @Autowired
    private JestClient client;

    private final String index;
    private final Class<T> clazz;

    public SimpleCrudService(String index, Class<T> clazz) {
        this.index = index;
        this.clazz = clazz;
    }

    public List<T> findAll() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(this.index)
                .addType(TYPE)
                .build();
        JestResult result;
        try {
            result = client.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return result.getSourceAsObjectList(this.clazz);
    }

    public T findById(String id) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.idsQuery().addIds(id));
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(this.index)
                .addType(TYPE)
                .build();
        JestResult result = null;
        try {
            result = client.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result != null ? result.getSourceAsObject(this.clazz) : null;
    }

    public boolean save(T object) {
        Index index = new Index.Builder(object).index(this.index).type(TYPE).build();
        try {
            JestResult result = client.execute(index);
            object.setId(((DocumentResult) result).getId());
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean delete(String id) {
        Delete delete = new Delete.Builder(id).index(this.index).type(TYPE).build();
        try {
            return client.execute(delete).isSucceeded();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteAll() {
        DeleteIndex delete = new DeleteIndex.Builder(this.index).build();
        try {
            return client.execute(delete).isSucceeded();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
