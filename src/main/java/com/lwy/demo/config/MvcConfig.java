package com.lwy.demo.config;


import com.lwy.demo.interceptor.Axiosinterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {



    @Bean
    public Axiosinterceptor Axiosinterceptor(){
        return new Axiosinterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(Axiosinterceptor()).addPathPatterns("/*");
    }
}
