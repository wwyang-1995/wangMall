package com.wang.mall.service.impl;

import com.wang.mall.model.UmsResource;
import com.wang.mall.model.UserAdmin;
import com.wang.mall.service.RedisService;
import com.wang.mall.service.UserAdminCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserAdminCacheServiceImpl implements UserAdminCacheService {
    @Value("${redis.database}")
    private String REDIS_DATABASE;
    @Value("${redis.key.resourceList}")
    private String REDIS_KEY_RESOURCE_LIST;
    @Value("${redis.expire.common}")
    private long REDIS_EXPIRE;
    @Autowired
    private RedisService redisService;

    public UserAdmin getAdmin(String username) {
        return (UserAdmin) redisService.get(username);
    }

    @Override
    public void setAdmin(UserAdmin admin) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + admin.getUserName();
        redisService.set(key,admin,REDIS_EXPIRE);
    }

    @Override
    public List<UmsResource> getResourceList(long id) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + id;
        return (List<UmsResource>) redisService.get(key);
    }

    @Override
    public void setRourseList(long id, List<UmsResource> resourceList) {
        String key = REDIS_DATABASE + ":" + REDIS_KEY_RESOURCE_LIST + ":" + id;
        redisService.set(key,resourceList,REDIS_EXPIRE);
    }
}
