package com.dingding.purchase.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("faces/**")
                .addResourceLocations("file:E:/Apache Tomcat/webapps/shop/images/faces/");
        registry.addResourceHandler("items/**")
                .addResourceLocations("file:E:/Apache Tomcat/webapps/shop/images/items/");
    }

}