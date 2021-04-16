package com.wang.mall.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


//用户登陆参数
@Data
@EqualsAndHashCode(callSuper = false)
public class UmsAdminLoginParam {
    @NotNull
    private String username;
    @NotNull
    private String password;
}
