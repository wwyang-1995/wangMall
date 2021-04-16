package com.wang.mall.mapper;

import com.wang.mall.model.UserAdmin;
import com.wang.mall.model.UserAdminExample;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserAdminMapper {
    List<UserAdmin> selectByExample(UserAdminExample example);

    List<UserAdmin> test1(UserAdminExample example);
}
