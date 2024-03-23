package org.example.mmall.config;

import lombok.Data;
import lombok.Getter;
import org.example.mmall.common.interceptor.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
@Data
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login")
                .excludePathPatterns(
                        "/",
                        "/swagger-ui/**",
                        "/swagger-ui.html",
                        "/swagger-ui/")
//                        "/swagger-ui/*.html",
                .excludePathPatterns(
                        "/druid/**",
                        "/actuator/**",
                        "/swagger-resources/**",
                        "/v3/api-docs/**",
                        "/favicon.ico");
//                        "/**/*.html",
//                        "/**/*.css",
//                        "/**/*.js",
//                        "/**/api-docs/**");
    }

    /***
     * 配置静态资源访问拦截
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
//        super.addResourceHandlers(registry);
    }

    @Bean
    public JwtInterceptor jwtInterceptor(){
        return new JwtInterceptor();
    }
}
