package com.jay.boot.common.config;

import com.jay.boot.filter.CustFilter;
import com.jay.boot.common.interceptors.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMvcConfigurerAdapter已过时
 *
 * @author shao.meng
 * @since 1.0
 * Date 2019/7/28 19:45
 */
@Configuration
public class CustWebConfigurer implements WebMvcConfigurer {
    
    @Autowired
    private SessionInterceptor sessionInterceptor;
    
    @Autowired
    private CustFilter custFilter;
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加拦截路径和放行的路径
        registry.addInterceptor(sessionInterceptor).addPathPatterns("/**")
            .excludePathPatterns("/login.do", "/", "/user/login");
    }
    
    /**
     * 添加过滤器
     * @return
     */
    @Bean
    public FilterRegistrationBean registrationBean() {
        FilterRegistrationBean<CustFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(custFilter);
        registrationBean.setName("customFilter");
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(1);
        return registrationBean;
    }
}
