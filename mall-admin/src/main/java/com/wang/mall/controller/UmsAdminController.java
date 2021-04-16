package com.wang.mall.controller;

import com.wang.mall.api.CommonResult;
import com.wang.mall.api.ResultCode;
import com.wang.mall.dto.UmsAdminLoginParam;
import com.wang.mall.model.UserAdmin;
import com.wang.mall.service.UmsAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
public class UmsAdminController {
    @Value("${jwt.tokenHead}")
    private String tokenHeader;

    @Autowired
    private UmsAdminService adminService;

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public CommonResult login(@Valid @RequestBody UmsAdminLoginParam umsAdminLoginParam) {
       String token = adminService.login(umsAdminLoginParam.getUsername(),umsAdminLoginParam.getPassword());
       if(token == null) {
           return CommonResult.validateFailed("用户名或密码错误");
       }
       Map<String,String> tokenMap = new HashMap<>();
       tokenMap.put("token",token);
       tokenMap.put("tokenHead",tokenHeader);
       return CommonResult.success(tokenMap);
    }
}
