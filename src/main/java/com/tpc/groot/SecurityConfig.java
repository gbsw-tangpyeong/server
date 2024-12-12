package com.tpc.groot;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.CrossOrigin;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/api/**")
//                        .permitAll()
//                        .anyRequest()
//                        .authenticated())
//                .oauth2Login(oauth2 -> oauth2
//                        .loginPage("/api/user/login")
//                        .defaultSuccessUrl("/")) // 로그인 성공 후 리다이렉트 경로
//                .formLogin(form -> form
//                        .loginPage("/api/user/login")
//                        .defaultSuccessUrl("/"))
//                .logout(logout -> logout
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/api/user/logout")))
//                .csrf(AbstractHttpConfigurer::disable);
//
//        return http.build();
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}