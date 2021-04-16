package com.wang.mall.security.config;

import com.wang.mall.security.util.JwtTokenUtil;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        ExpressionUrlAuthorizationConfigurer<HttpSecurity>.ExpressionInterceptUrlRegistry registry = httpSecurity.authorizeRequests();
        for (String url : ignoreUrl().getUrls()) {
            registry.antMatchers(url).permitAll();
        }
//        httpSecurity.cors();
        registry.antMatchers(HttpMethod.OPTIONS)
                .permitAll();
        registry.and()
                .authorizeRequests()
                .anyRequest()
                .authenticated()
                .and()
                //csrf开启之后只会放行GET|HEAD|TRACE|OPTIONS这4类方法，post方法会报403错误，所以需要禁用csrf
                .csrf()
                .disable()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtTokenUtil jwtTokenUtil() {
        return new JwtTokenUtil();
    }

    @Bean
    public IgnoreUrlConfig ignoreUrl() {
        return new IgnoreUrlConfig();
    }
}