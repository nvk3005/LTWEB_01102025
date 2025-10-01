package org.example.demo02_springsecurity.config;

import org.example.demo02_springsecurity.service.UserInfoDetailService;
import org.example.demo02_springsecurity.service.UserInfoUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public UserInfoDetailService userDetailsService() {
        return new UserInfoDetailService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // Sử dụng BCrypt trong production
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/hello").permitAll()
                        .requestMatchers("/getCustomerList").authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin()
                .and()
                .httpBasic();
        return http.build();
    }
}
