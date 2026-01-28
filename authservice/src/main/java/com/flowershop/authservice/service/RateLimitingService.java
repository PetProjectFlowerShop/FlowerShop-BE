package com.flowershop.authservice.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RateLimitingService {
    private final Cache<String, Integer> attemptsCache = Caffeine.newBuilder()
        .expireAfterWrite(15, TimeUnit.MINUTES)
        .build();
    private static final int MAX_ATTEMPTS = 5;

    public void recordFailedAttempts(String key) {
        int attempts = attemptsCache.get(key, k -> 0);
        attemptsCache.put(key, attempts + 1);

    }

    public boolean isBlocked(String key) {
        Integer attempts = attemptsCache.getIfPresent(key);
        return attempts != null && attempts >= MAX_ATTEMPTS;
    }
    public void resetAttempts(String key){
        attemptsCache.invalidate(key);
    }

}
