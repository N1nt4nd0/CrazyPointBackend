package ru.feodorkek.dev.crazypoint.config.data;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;

@Configuration
public class MongoDbConfig {

    @Bean
    public MongoTransactionManager transactionManager(final MongoDatabaseFactory factory) {
        return new MongoTransactionManager(factory);
    }

}
