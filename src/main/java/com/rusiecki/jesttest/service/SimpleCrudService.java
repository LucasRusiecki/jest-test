package com.rusiecki.jesttest.service;

import com.rusiecki.jesttest.model.BaseDto;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestResult;
import io.searchbox.core.Delete;
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

    private static final String TYPE = "_doc";
    @Autowired
    private JestClient client;

    abstract String getIndex();
    abstract Class<T> getClazz();

    public List<T> findAll() {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(getIndex())
                .addType(TYPE)
                .build();
        JestResult result;
        try {
            result = client.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        return result.getSourceAsObjectList(getClazz());
    }

    public T findById(String id) {
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.idsQuery().addIds(id));
        Search search = new Search.Builder(searchSourceBuilder.toString())
                .addIndex(getIndex())
                .addType(TYPE)
                .build();
        JestResult result = null;
        try {
            result = client.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result != null ? result.getSourceAsObject(getClazz()) : null;
    }

    public void save(Object object) {
        Index index = new Index.Builder(object).index(getIndex()).type(TYPE).build();
        try {
            client.execute(index);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void delete(String id) {
        Delete delete = new Delete.Builder(id).index(getIndex()).type(TYPE).build();
        try {
            client.execute(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteAll() {
        DeleteIndex delete = new DeleteIndex.Builder(getIndex()).build();
        try {
            client.execute(delete);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
