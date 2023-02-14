package com.huagui.common.base;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huagui.common.base.util.JsonObjectConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class CommonBeanConfig {

    @Bean
    @Primary
    public ObjectMapper objectMapper() {
        return JsonObjectConverter.getObjectMapper();
    }

}
