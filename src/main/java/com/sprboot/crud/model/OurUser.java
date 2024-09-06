package com.sprboot.crud.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
public class OurUser implements UserDetails {
    private Integer id;
    private String email;
    private String password;
    private String role;

    // trả về các quyền của user
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }


    @Override
    public String getUsername() {
        return email;
    }


    // check tai khoan da het han chua
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // check tai khoan co bi khoá ko
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }


    // Kiểm tra xem thông tin đăng nhập của người dùng (như mật khẩu) có còn hợp lệ hay không.
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // Xác định xem người dùng có được kích hoạt hay không.
    @Override
    public boolean isEnabled() {
        return true;
    }
}
