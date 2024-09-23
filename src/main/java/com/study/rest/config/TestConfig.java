package com.study.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.expression.TypedValue;

@Configuration
public class TestConfig {

    @Bean // @Configuration안에서만 가능 / 여러 개 등록 가능
    public TypedValue typedValue() {
        return new TypedValue(null);
    }

}
