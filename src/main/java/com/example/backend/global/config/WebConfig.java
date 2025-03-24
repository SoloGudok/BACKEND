package com.example.backend.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:3000", "http://localhost", "http://192.168.0.169") // React 주소
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowCredentials(true);

                // 이미지 경로에 대해서도 CORS 허용
                registry.addMapping("/subscription_img/**")
                        .allowedOrigins("http://localhost:3000", "http://localhost", "http://192.168.0.169") // React 주소
                        .allowedMethods("GET", "OPTIONS")
                        .allowCredentials(true);
            }
        };
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
