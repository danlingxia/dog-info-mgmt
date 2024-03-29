package com.doggiehome.doginfomgmt.config;

import com.doggiehome.doginfomgmt.interceptor.ManagerInterceptor;
import com.doggiehome.doginfomgmt.interceptor.SuperManagerInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@Order(1)
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    ManagerInterceptor managerInterceptor;

    @Autowired
    SuperManagerInterceptor superManagerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(managerInterceptor).addPathPatterns("/dog-manage/*").addPathPatterns("/cage/*")
                .addPathPatterns("/manager/managerLogout");

        registry.addInterceptor(superManagerInterceptor).addPathPatterns("/manager/newManager");

//        registry.addInterceptor(userInterceptor).addPathPatterns("/*").excludePathPatterns("/dog/*", "/manager/managerLogin");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
