package com.wang.mall.service.impl;

import com.wang.mall.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void set(String key, Object admin, long time) {
        redisTemplate.opsForValue().set(key,admin,time,TimeUnit.SECONDS);
    }


}
