package com.jt.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jt.common.cache.RedisManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.validation.ObjectError;


@Configuration
public class RedisConfig {

    @Bean
    public RedisManager redisManager(RedisTemplate<String,Object> redisTemplate){

        RedisManager redisManager = new RedisManager();
        redisManager.setRedisTemplate(redisTemplate);
        return redisManager;

    }

    @Bean
    public RedisTemplate<String,Object> redisTemplate(RedisConnectionFactory factory){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(factory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        redisTemplate.setKeySerializer(stringRedisSerializer);

        return redisTemplate;


    }





}
