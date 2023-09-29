package com.phincon.wls.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean("config")
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        ((SimpleClientHttpRequestFactory)restTemplate.getRequestFactory()).setReadTimeout(15000);
        ((SimpleClientHttpRequestFactory)restTemplate.getRequestFactory()).setConnectTimeout(5000);
        return restTemplate;
    }
}
