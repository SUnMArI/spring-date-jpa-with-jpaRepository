package com.example.exercies3.config;

import com.example.exercies3.model.dto.response.ApiResponse;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public ApiResponse<?> response(){
      return  new ApiResponse<>();
    }
}
