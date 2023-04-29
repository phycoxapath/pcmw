package indi.gd.pcmw.config;

import indi.gd.pcmw.controller.interceptor.AdminInterceptor;
import indi.gd.pcmw.controller.interceptor.OriginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new OriginInterceptor()).addPathPatterns("/**").excludePathPatterns("/common/**");
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/admins/**").excludePathPatterns("/admins/loginValidate");
    }



}
