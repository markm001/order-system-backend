package com.ccat.ordersys.config;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@EnableCaching
@Configuration
public class CacheConfig {
    @Bean
    public CacheManager cacheManagerCaffeine() {
        CaffeineCacheManager cacheManager = new CaffeineCacheManager("itemsByTag");
        cacheManager.setCaffeine(
                Caffeine.newBuilder().expireAfterAccess(Duration.ofMinutes(1)));

        return cacheManager;
    }
}
