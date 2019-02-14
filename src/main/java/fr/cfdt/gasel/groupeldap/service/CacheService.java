package fr.cfdt.gasel.groupeldap.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class CacheService {

    @Autowired
    CacheManager cacheManager;

    public void evictCacheByValue(String value) {
        cacheManager.getCache(value).clear();
    }
}
