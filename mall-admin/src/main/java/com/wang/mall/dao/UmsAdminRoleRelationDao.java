package com.wang.mall.dao;

import com.wang.mall.model.UmsResource;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/*自定义后台用户与角色管理*/
public interface UmsAdminRoleRelationDao {
    /*获取用户所有可访问资源*/
    List<UmsResource> getResourceList(@Param("adminId") long adminId);

}
