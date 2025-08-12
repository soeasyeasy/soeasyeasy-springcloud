package com.soeasyeasy.security.service.impl;

import com.soeasyeasy.common.entity.BaseUser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * @author hc
 */
@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {

    // 这个方法是 @Data注解 会自动帮我们生成，用来获取 loadUserByUsername 中最后我们返回的创建UserDetailsImpl对象时传入的User。
    // 如果你的字段包含 username和password 的话可以用强制类型转换, 把 UserDetailsImpl 转换成 User。如果不能强制类型转换的话就需要用到这个方法了
    private BaseUser user;
    private List<GrantedAuthority> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return user.getPwd();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {  // 检查账户是否 没过期。
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {   // 检查账户是否 没有被锁定。
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {  //检查凭据（密码）是否 没过期。
        return true;
    }

    @Override
    public boolean isEnabled() {    // 检查账户是否启用。
        return true;
    }

}