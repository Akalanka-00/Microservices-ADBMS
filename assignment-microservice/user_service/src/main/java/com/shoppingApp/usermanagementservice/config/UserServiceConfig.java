package com.shoppingApp.usermanagementservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class UserServiceConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
