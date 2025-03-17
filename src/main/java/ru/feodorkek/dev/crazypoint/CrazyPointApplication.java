package ru.feodorkek.dev.crazypoint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableCaching
@EnableScheduling
@EnableWebSecurity
@SpringBootApplication
public class CrazyPointApplication {

    public static void main(final String[] args) {
        SpringApplication.run(CrazyPointApplication.class, args);
    }

}