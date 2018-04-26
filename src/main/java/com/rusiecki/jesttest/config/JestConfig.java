package com.rusiecki.jesttest.config;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JestConfig {

    @Value("${elasticsearch.url}")
    private String elasticSearchUrl;

    @Bean
    public JestClient jestClient() {
        String connectionUrl = elasticSearchUrl;
        JestClientFactory factory = new JestClientFactory();
        factory.setHttpClientConfig(new HttpClientConfig
                .Builder(connectionUrl)
                .multiThreaded(true)
                .build());
        return factory.getObject();
    }
}
