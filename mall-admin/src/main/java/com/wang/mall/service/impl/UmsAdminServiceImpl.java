package com.wang.mall.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.wang.mall.bo.AdminUserDetails;
import com.wang.mall.dao.UmsAdminRoleRelationDao;
import com.wang.mall.exception.Assert;
import com.wang.mall.model.UmsResource;
import com.wang.mall.model.UserAdmin;
import com.wang.mall.mapper.UserAdminMapper;
import com.wang.mall.model.UserAdminExample;
import com.wang.mall.model.UserAdminLoginLog;
import com.wang.mall.security.util.JwtTokenUtil;
import com.wang.mall.service.UmsAdminService;
import com.wang.mall.service.UserAdminCacheService;
import com.wang.mall.util.RequestUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Service
public class UmsAdminServiceImpl implements UmsAdminService {
    private static final Logger logger = LoggerFactory.getLogger(UmsAdminServiceImpl.class);
    @Autowired
    private UserAdminCacheService userAdminCacheService;
    @Autowired
    private UserAdminMapper adminMapper;
    @Autowired
    private UmsAdminRoleRelationDao adminRoleRelationDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    public String login(String userName, String password) {
        String token = null;
        try {
            UserDetails userDetails = loadUserByUserName(userName);
            if (!passwordEncoder.matches(password,userDetails.getPassword())){
                Assert.fail("密码不正确");
            }
            if (!userDetails.isEnabled()){
                Assert.fail("账号已被禁用");
            }
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            token = jwtTokenUtil.generateToken(userDetails);
            insertLoginLog(userName);
        } catch (Exception e) {
            logger.warn("登录异常",e.getMessage());
        }

        return token;
    }

    /**
     * 添加登录记录
     * @param username 用户名
     */
    private void insertLoginLog(String username) {
        UserAdmin admin = getAdminByUsername(username);
        if(admin==null) return;
        UserAdminLoginLog loginLog = new UserAdminLoginLog();
        loginLog.setAdminId(admin.getId());
        loginLog.setCreateTime(new Date());
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        loginLog.setIp(RequestUtil.getRequestIp(request));
//        loginLogMapper.insert(loginLog);
    }

    public UserAdmin getAdminByUsername(String username) {
        UserAdmin admin = userAdminCacheService.getAdmin(username);
        if(admin!=null) return  admin;
        UserAdminExample example = new UserAdminExample();
        example.createCriteria().andUsernameEqualTo(username);
        List<UserAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            userAdminCacheService.setAdmin(admin);
            return admin;
        }
        return null;
    }

    public UserDetails loadUserByUserName(String userName) {
        //获取用户信息
        UserAdmin admin = getAdminByUserName(userName);
        if (admin!=null) {
            List<UmsResource> resourceList = getResourceList(admin.getId());
            return new AdminUserDetails(admin,resourceList);
        }
        throw new UsernameNotFoundException("用户名或密码错误");
    }

    private List<UmsResource> getResourceList(long id) {
        List<UmsResource> resourceList = userAdminCacheService.getResourceList(id);
        if (CollUtil.isNotEmpty(resourceList)) {
            return resourceList;
        }
        resourceList = adminRoleRelationDao.getResourceList(id);
        if (CollUtil.isNotEmpty(resourceList)) {
//            userAdminCacheService.setRourseList(id, resourceList);
        }
        return resourceList;
    }

    private UserAdmin getAdminByUserName(String userName) {
        UserAdmin admin = userAdminCacheService.getAdmin(userName);
        if (admin!=null) return admin;
        UserAdminExample example = new UserAdminExample();
        example.createCriteria().andUsernameEqualTo(userName);
        List<UserAdmin> adminList = adminMapper.selectByExample(example);
        if (adminList != null && adminList.size() > 0) {
            admin = adminList.get(0);
            userAdminCacheService.setAdmin(admin);
            return admin;
        }
        return null;
    }
}
