package com.soeasyeasy.security.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * 支持用户名密码 和 手机号验证码 的统一认证令牌
 */
@SuppressWarnings("all")
public class CustomAuthenticationToken extends AbstractAuthenticationToken {

    private final Object principal;
    private final Object credentials;
    private final LoginType type;

    // 认证前使用（携带原始凭证）
    public CustomAuthenticationToken(Object principal, Object credentials, LoginType type) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.type = type;
        setAuthenticated(false);
    }

    // 认证后使用
    public CustomAuthenticationToken(Object principal, Object credentials,
                                     Collection<? extends GrantedAuthority> authorities, LoginType type) {
        super(authorities);
        this.principal = principal;
        this.credentials = credentials;
        this.type = type;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public LoginType getType() {
        return this.type;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) {
        if (isAuthenticated) {
            throw new IllegalArgumentException("请使用带权限的构造函数设置已认证状态");
        }
        super.setAuthenticated(false);
    }
}