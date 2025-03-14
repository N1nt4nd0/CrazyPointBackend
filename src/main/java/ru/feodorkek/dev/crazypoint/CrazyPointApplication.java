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

    // TODO try to setting up transactions for mongo Railway
    // TODO remove social and billing infos
    // TODO add avatars Controller with avatars links

    public static void main(final String[] args) {
        SpringApplication.run(CrazyPointApplication.class, args);
    }

}