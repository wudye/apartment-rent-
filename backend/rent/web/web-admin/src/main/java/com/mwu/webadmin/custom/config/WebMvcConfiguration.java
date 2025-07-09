package com.mwu.webadmin.custom.config;

import com.mwu.webadmin.custom.convert.StringToBaseEnumConverterFactory;
import com.mwu.webadmin.custom.inceptor.AuthenticationInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    @Autowired
    private StringToBaseEnumConverterFactory stringToBaseEnumConverterFactory;
    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addFormatters(org.springframework.format.FormatterRegistry registry) {
        registry.addConverterFactory(stringToBaseEnumConverterFactory);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry
                .addInterceptor(this.authenticationInterceptor)
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/login/**");
    }

}
