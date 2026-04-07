package com.green.namuwiki.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public RestClient restClient() {
        return RestClient.create();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173", "https://namuwiki-a40.pages.dev")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    // addResourceHandlers 메서드를 이용해서 스프링 서버에서 외부 파일을 접근하는 설정을 추가
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
//            url에 http:localhost:8080/upload/** 이라고 입력하면
                .addResourceHandler("/upload/**")
//            D:/01-STUDY/dev/upload/** 이쪽을 참고하겠다
                .addResourceLocations("file:///D:/01-STUDY/dev/upload/");
    }
}
