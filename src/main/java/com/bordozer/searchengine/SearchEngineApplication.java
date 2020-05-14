package com.bordozer.searchengine;

import com.bordozer.commons.config.Java8DateTimeConfiguration;
import com.bordozer.commons.web.RequestIdFilter;
import com.bordozer.searchengine.config.AopConfiguration;
import com.bordozer.searchengine.config.SwaggerConfig;
import com.bordozer.searchengine.config.WatchRequestIdFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.annotation.Order;

@SuppressWarnings("checkstyle:HideUtilityClassConstructor")
@Configuration
@SpringBootApplication
@Import({Java8DateTimeConfiguration.class, SwaggerConfig.class, AopConfiguration.class})
public class SearchEngineApplication {

    public static void main(final String[] args) {
        SpringApplication.run(SearchEngineApplication.class, args);
    }

    @Bean
    @Order(1)
    public FilterRegistrationBean<RequestIdFilter> requestIdFilter() {
        final FilterRegistrationBean<RequestIdFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new RequestIdFilter());
        return registrationBean;
    }

    @Bean
    @Order(2)
    public FilterRegistrationBean<WatchRequestIdFilter> requestWatcherFilter() {
        final FilterRegistrationBean<WatchRequestIdFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new WatchRequestIdFilter());
        return registrationBean;
    }
}
