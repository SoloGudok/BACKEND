package com.example.backend.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    // ✅ CORS 설정 (React에서 API 호출 가능하도록 허용)
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")  // API 경로에 CORS 적용
                .allowedOrigins("http://localhost:3000")  // React 개발 서버
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*");
    }

    // ✅ 정적 리소스 (React 빌드 파일 서빙)
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

    // ✅ `/cardlist`에서 React 실행
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/cardlist") // http://localhost:8090/cardlist
                .setViewName("forward:/index.html"); // React 실행

        registry.addViewController("/cards") // http://localhost:8090/cards
                .setViewName("card/cards"); // Thymeleaf 실행
    }
}
