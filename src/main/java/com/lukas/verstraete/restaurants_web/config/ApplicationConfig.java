package com.lukas.verstraete.restaurants_web.config;

import com.lukas.verstraete.restaurants_domain.service.RestaurantFacade;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.lukas.verstraete.restaurants_web.web") 
@EnableWebMvc
public class ApplicationConfig {
    
    @Bean
    public RestaurantFacade services()
    {
        return new RestaurantFacade();
    }
}