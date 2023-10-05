package com.phincon.wls.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean("config")
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
        //((SimpleClientHttpRequestFactory)restTemplate.getRequestFactory()).setReadTimeout(15000);
        //((SimpleClientHttpRequestFactory)restTemplate.getRequestFactory()).setConnectTimeout(5000);
        return restTemplate;
    }
}
