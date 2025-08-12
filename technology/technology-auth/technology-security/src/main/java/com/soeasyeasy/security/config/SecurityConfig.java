package com.soeasyeasy.security.config;

import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author hc
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Resource
    private JwtAuthenticationFilter jwtAuthenticationFilter;


    /**
     * BCryptPasswordEncoder：基于 BCrypt 算法，具有适应性和强大的加密强度。它可以根据需求自动调整加密的复杂性。
     * NoOpPasswordEncoder：不执行加密，适用于开发和测试环境，不建议在生产环境中使用。
     * Pbkdf2PasswordEncoder、Argon2PasswordEncoder 等：这些都是基于不同算法的实现，具有不同的安全特性。
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 也可用有参构造，取值范围是 4 到 31，默认值为 10。数值越大，加密计算越复杂
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    //@Bean
    //public AuthenticationManager authenticationManager() {
    //    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    //    provider.setUserDetailsService(userDetailsUserNameServiceImpl);
    //    provider.setPasswordEncoder(passwordEncoder());
    //    DaoAuthenticationProvider phoneProvider = new DaoAuthenticationProvider();
    //    provider.setUserDetailsService(userDetailsPhoneServiceImpl);
    //    provider.setPasswordEncoder(passwordEncoder());
    //    return new ProviderManager(provider, phoneProvider);
    //}


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // 基于token，不需要csrf
                .csrf().disable()
                // 基于token，不需要session
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests((authz) -> authz
                        // 放行api
                        .requestMatchers("/login", "/login/**", "/getPicCheckCode").permitAll()
                        .requestMatchers(HttpMethod.OPTIONS).permitAll()
                        //.requestMatchers(HttpMethod.POST).permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}