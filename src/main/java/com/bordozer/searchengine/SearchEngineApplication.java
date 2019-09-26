package com.bordozer.searchengine;

import com.bordozer.commons.config.Java8DateTimeConfiguration;
import com.bordozer.searchengine.config.SwaggerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@SpringBootApplication
@Import({Java8DateTimeConfiguration.class, SwaggerConfig.class})
public class SearchEngineApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchEngineApplication.class, args);
    }
}
