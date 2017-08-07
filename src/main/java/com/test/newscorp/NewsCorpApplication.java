package com.test.newscorp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class NewsCorpApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(NewsCorpApplication.class, args);
    }
}
