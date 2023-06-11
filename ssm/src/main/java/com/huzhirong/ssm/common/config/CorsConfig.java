package com.huzhirong.ssm.common.config;

import com.huzhirong.ssm.common.interceptor.JWTInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // 配置cors跨域
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*") // 允许跨域的域名，可以用*表示允许任何域名使用
                .allowedMethods("GET","HEAD", "POST", "PUT", "DELETE", "OPTIONS") // 允许任何方法（post、get等）
                .allowCredentials(true) // 允许携带cookie
                .maxAge(3600) // 允许跨域的3600秒内，不需要再发送预检验请求，可以缓存该结果
                .allowedHeaders("*"); // 允许任何请求头
    }

    // 配置拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截器
        registry.addInterceptor(new JWTInterceptor())
                // 拦截所有请求
                .addPathPatterns("/**")
                // 放行的请求
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/static/**")
                .excludePathPatterns("/admin/info");
                //.excludePathPatterns("/user/**");
    }
}