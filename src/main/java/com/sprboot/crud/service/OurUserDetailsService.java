package com.sprboot.crud.service;

import com.sprboot.crud.repository.OurUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
// xử lý logic để truy vấn thông tin người dùng trong DB trong quá trình xác thực đăng nhập
public class OurUserDetailsService implements UserDetailsService {
    @Autowired
    private OurUserRepository ourUserRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return (UserDetails) ourUserRepo.findByEmail(username).orElseThrow();
        // Nếu user tồn tại, phương thức trả về đối tượng UserDetails, đại diện cho thông tin của người dùng
    }
}
