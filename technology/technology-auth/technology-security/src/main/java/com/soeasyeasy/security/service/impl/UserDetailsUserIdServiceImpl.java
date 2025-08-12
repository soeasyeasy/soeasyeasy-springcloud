package com.soeasyeasy.security.service.impl;

import com.soeasyeasy.common.entity.BaseUser;
import com.soeasyeasy.security.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author hc
 */
@Primary
@Service
public class UserDetailsUserIdServiceImpl implements UserDetailsService {

    @Resource
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        BaseUser userByUsername = userService.getUserById(id);
        if (userByUsername == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<GrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("user:page"));
        return new UserDetailsImpl(userByUsername, authorities);
    }

}