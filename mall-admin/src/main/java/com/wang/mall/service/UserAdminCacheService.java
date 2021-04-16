package com.wang.mall.service;

import com.wang.mall.model.UmsResource;
import com.wang.mall.model.UserAdmin;

import java.util.List;

public interface UserAdminCacheService {
    //获取后台缓存用户
    UserAdmin getAdmin(String username);

    /*设置后台缓存用户*/
    void setAdmin(UserAdmin admin);

    /*获取后台缓存用户资源列表*/
    List<UmsResource> getResourceList(long id);

    /*设置后台缓存用户资源列表*/
    void setRourseList(long id, List<UmsResource> resourceList);
}
