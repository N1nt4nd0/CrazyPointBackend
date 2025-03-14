package ru.feodorkek.dev.crazypoint.config.data;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import ru.feodorkek.dev.crazypoint.config.properties.CacheProperties;
import ru.feodorkek.dev.crazypoint.init.PostConstructProvider;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class RedisCacheConfig implements PostConstructProvider {

    private final RedisConnectionFactory connectionFactory;
    private final CacheProperties cacheProperties;

    public void clearAllCache() {
        try {
            log.info("Start clearing all caches");
            connectionFactory.getConnection().commands().flushDb();
            log.info("Cache cleared successfully");
        } catch (final Exception exception) {
            log.error("Fail on clearing cache", exception);
        }
    }

    @Bean
    public RedisCacheManager cacheManager() {
        return RedisCacheManager.builder(connectionFactory)
                .cacheDefaults(RedisCacheConfiguration.defaultCacheConfig()
                        .serializeValuesWith(RedisSerializationContext.SerializationPair
                                .fromSerializer(RedisSerializer.json()))
                        .disableCachingNullValues())
                .build();
    }

    @Override
    public String postConstructProviderName() {
        return "Redis Cache Config";
    }

    @Override
    public void postConstruct() {
        try {
            connectionFactory.getConnection().commands().ping();
        } catch (final Exception exception) {
            throw new IllegalStateException("Can't connect to redis", exception);
        }
        if (cacheProperties.isClearCacheOnStart()) {
            clearAllCache();
        }
    }

}