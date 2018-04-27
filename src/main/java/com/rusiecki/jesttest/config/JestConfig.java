package com.rusiecki.jesttest.config;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JestConfig {

    private final String elasticSearchUrl;

    public JestConfig(@Value("${elasticsearch.url}") final String elasticSearchUrl) {
        this.elasticSearchUrl = elasticSearchUrl;
    }

    @Bean
    public JestClient jestClient() {
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(elasticSearchUrl)
                .multiThreaded(true)
                .build());
        return factory.getObject();
    }
}
