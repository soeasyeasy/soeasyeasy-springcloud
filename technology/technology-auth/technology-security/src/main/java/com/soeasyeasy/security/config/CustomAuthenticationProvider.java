package com.soeasyeasy.security.config;

import com.soeasyeasy.security.service.UserService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * 处理 CustomAuthenticationToken 的统一认证逻辑
 *
 * @author hc
 */
@Component
@SuppressWarnings("all")
public class CustomAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder; // 注入密码编码器

    public CustomAuthenticationProvider(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        CustomAuthenticationToken token = (CustomAuthenticationToken) authentication;
        LoginType type = token.getType();

        if (LoginType.password.equals(type)) {
            return authenticateByPassword(token);
        } else if (LoginType.mobile.equals(type)) {
            return authenticateByMobile(token);
        } else {
            throw new BadCredentialsException("不支持的登录类型");
        }
    }

    // 处理用户名密码登录
    private Authentication authenticateByPassword(CustomAuthenticationToken token) {
        String username = (String) token.getPrincipal();
        String rawPassword = (String) token.getCredentials();

        var user = userService.getUserByUsername(username);
        if (user == null) {
            throw new BadCredentialsException("用户不存在");
        }

        if (!passwordEncoder.matches(rawPassword, user.getPwd())) {
            throw new BadCredentialsException("密码错误");
        }
        var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return new CustomAuthenticationToken(user, null, authorities, LoginType.password);
    }

    // 处理手机号验证码登录
    private Authentication authenticateByMobile(CustomAuthenticationToken token) {
        String mobile = (String) token.getPrincipal();
        String code = (String) token.getCredentials();

        // 模拟验证码校验（生产环境查 Redis）
        if (code == null || !"123456".equals(code)) {
            throw new BadCredentialsException("验证码错误");
        }

        var user = userService.getUserByPhone(mobile);
        if (user == null) {
            throw new BadCredentialsException("手机号未注册");
        }

        var authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"));
        return new CustomAuthenticationToken(user, null, authorities, LoginType.mobile);
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return CustomAuthenticationToken.class.isAssignableFrom(authentication);
    }
}