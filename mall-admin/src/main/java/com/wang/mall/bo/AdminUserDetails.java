package com.wang.mall.bo;

import com.wang.mall.model.UmsResource;
import com.wang.mall.model.UserAdmin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*SpringSecurity需要的用户详情*/
public class AdminUserDetails implements UserDetails {
    private UserAdmin userAdmin;
    private List<UmsResource> resourcesList;

    public AdminUserDetails(UserAdmin userAdmin,List<UmsResource> resourcesList) {
        this.resourcesList = resourcesList;
        this.userAdmin = userAdmin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的角色
        return resourcesList.stream()
                .map(role ->new SimpleGrantedAuthority(role.getId()+":"+role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return userAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return userAdmin.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userAdmin.getStatus().equals(1);
    }
}
