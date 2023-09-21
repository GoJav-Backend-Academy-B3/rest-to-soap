package com.phincon.wls.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean("config")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
