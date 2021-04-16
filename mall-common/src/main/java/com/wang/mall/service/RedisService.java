package com.wang.mall.service;

public interface RedisService {
    Object get(String username);

    void set(String key, Object admin, long redis_expire);
}
