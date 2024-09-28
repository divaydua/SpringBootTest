package com.webtutorial.week2test.configs;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfig {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }
}
