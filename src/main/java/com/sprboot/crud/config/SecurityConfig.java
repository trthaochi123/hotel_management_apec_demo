package com.sprboot.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                // các phương thức cấu hình bảo mật này yêu cầu xử lý ngoại lệ can throws Exception
                // cấu hình quyền truy cập dựa trên URL
                .authorizeHttpRequests((api) -> api
                                .requestMatchers("/api/guest/**").hasAnyRole("GUEST", "ADMIN")
                                .requestMatchers("api/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated() //tất cả các request cần phải xác thực.
                )
                //
                .csrf(AbstractHttpConfigurer::disable)
                //yêu cầu cung cấp tên đăng nhập và mật khẩu thông qua tiêu đề HTTP (header)
                .httpBasic(Customizer.withDefaults())
                // ko luu thong tin dang nhap vao session --> moi lan truy cap can phai xac thuc
                .sessionManagement(
                        httpSecuritySessionManagementConfigurer ->
                                httpSecuritySessionManagementConfigurer
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        List<UserDetails> users = new ArrayList<>();
        users.add(createUser("guest", passwordEncoder().encode("guest123"), "GUEST" ));
        users.add(createUser("admin", passwordEncoder().encode("admin123"), "ADMIN" ));

        return new InMemoryUserDetailsManager(users);
    }

    // tạo hàm createUser truyền tham số vào UserDetailsService (cung cap thong tin cho spring security)
    private UserDetails createUser(String username, String password, String userRole) {
        return User
                .withUsername(username)
                .password(password)
                .roles(userRole)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}
