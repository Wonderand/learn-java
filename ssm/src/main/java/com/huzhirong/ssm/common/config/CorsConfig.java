package com.huzhirong.ssm.common.config;

import com.huzhirong.ssm.common.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器
        registry.addInterceptor(new JWTInterceptor())
                // 拦截所有请求
                .addPathPatterns("/**")
                // 放行的请求
                .excludePathPatterns("/admin/login");
                //.excludePathPatterns("/user/**");
    }
}