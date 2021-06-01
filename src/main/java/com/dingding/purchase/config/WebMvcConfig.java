package com.dingding.purchase.config;

import com.dingding.purchase.intercept.intercept;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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

    @Bean
    public intercept intercept() {
        return new intercept();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(intercept())
                .addPathPatterns("/shopcart/add")
                .addPathPatterns("/shopcart/del")
                .addPathPatterns("/payment/goAlipay")
                .addPathPatterns("/center/*/*")
                .addPathPatterns("/center/userInfo")
                .addPathPatterns("/center/deliver")
                .addPathPatterns("/items/*")
                .addPathPatterns("/items/*/*")
                .addPathPatterns("/orders/*")
                .addPathPatterns("/address/*");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}