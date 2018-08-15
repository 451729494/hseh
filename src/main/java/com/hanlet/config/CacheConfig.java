package com.hanlet.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.core.RedisTemplate;
/**
 * 
 * @author xm
 * 2018年6月1日
 */
@Configuration
public class CacheConfig extends CachingConfigurerSupport{  
	
	//定义一个全局的记录器，通过LoggerFactory获取
    private final static Logger logger = LoggerFactory.getLogger(CacheConfig.class); 
	
	@Bean
    public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
		logger.debug("-------------init redis cache-------------");
    	//RedisCache需要一个RedisCacheWriter来实现读写Redis
        CacheManager cacheManager = new RedisCacheManager(redisTemplate);
        logger.debug("-------------redis cache ready------------");
        return cacheManager;
    }
}