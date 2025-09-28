package com.backoven.catdogshelter.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/* 설명. 공통 인터셉터를 설정(도메인 개별적으로 필요한 인터셉터는 해당 도메인 아래로 제작) */

@Configuration
public class WebConfiguration implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // URL: http://localhost:8080/uploads/파일명
        // 실제 폴더: /Users/dong/uploads/
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:/Users/dong/uploads/");
    }


}
