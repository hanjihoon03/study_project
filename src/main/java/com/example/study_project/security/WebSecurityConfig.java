package com.example.study_project.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests((authorizeHttpRequests) ->
                authorizeHttpRequests
                        .requestMatchers(PathRequest.toStaticResources()
                                .atCommonLocations()).permitAll() //리소스 접근 허용
                        .anyRequest().authenticated()); //리소스 이외 접근 인증 필요

        http.formLogin((formLogin) -> formLogin
                //로그인 view 반환
                .loginPage("/login")
                //로그인 처리할 post api
                .loginProcessingUrl("/api/login")
                //로그인 성공 후 이동할 url
                .defaultSuccessUrl("/")
                //로그인 실패시 url
                .failureUrl("/api/login?error")
                .permitAll());

        return http.build();

    }
}
