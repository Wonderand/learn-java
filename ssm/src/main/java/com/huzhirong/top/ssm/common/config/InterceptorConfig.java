package com.huzhirong.top.ssm.common.config;

import com.huzhirong.top.ssm.common.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器
        registry.addInterceptor(new JWTInterceptor())
                // 拦截所有请求
                .addPathPatterns("/**")
                // 放行的请求
                .excludePathPatterns("/jwt/login");
//                .excludePathPatterns("/user/**");
    }

}
