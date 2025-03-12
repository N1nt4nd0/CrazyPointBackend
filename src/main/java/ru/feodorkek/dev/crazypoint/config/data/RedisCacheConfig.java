package ru.feodorkek.dev.crazypoint.config.data;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import ru.feodorkek.dev.crazypoint.config.properties.CacheProperties;
import ru.feodorkek.dev.crazypoint.init.PostConstructProvider;

@Configuration
@RequiredArgsConstructor
public class RedisCacheConfig implements PostConstructProvider {

    private final RedisConnectionFactory connectionFactory;
    private final CacheProperties cacheProperties;

    public void clearAllCache() {
        connectionFactory.getConnection().commands().flushDb();
    }

    public void checkConnection() {
        try {
            connectionFactory.getConnection().commands().ping();
        } catch (final Exception exception) {
            throw new IllegalStateException("Can't connect to redis", exception);
        }
    }

    @Bean
    public CacheManagerCustomizer<RedisCacheManager> redisCacheManagerCustomizer() {
        return cacheManager -> cacheManager.setTransactionAware(true);
    }

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .serializeValuesWith(RedisSerializationContext.SerializationPair
                        .fromSerializer(RedisSerializer.json()))
                .disableCachingNullValues();
    }

    @Bean
    public RedisCacheManager cacheManager(final RedisCacheConfiguration cacheConfiguration) {
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(cacheConfiguration)
                .transactionAware()
                .build();
    }

    @Override
    public String postConstructProviderName() {
        return "Redis Cache Config";
    }

    @Override
    public void postConstruct() {
        checkConnection();
        if (cacheProperties.isClearCacheOnStart()) {
            clearAllCache();
        }
    }

}