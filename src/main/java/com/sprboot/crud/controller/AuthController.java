package com.sprboot.crud.controller;

import com.sprboot.crud.dto.RefreshTokenDTO;
import com.sprboot.crud.dto.SignInDTO;
import com.sprboot.crud.dto.SignUpDTO;
import com.sprboot.crud.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    private AuthService authService;

    // nhận dữ liệu từ phía client và truyền vào ReqRes
    // nhận lại ReqRes từ service và trả về client
    @PostMapping("/auth/signup")
    public SignUpDTO signUp(@RequestBody SignUpDTO signUpRequest){
        // Gọi AuthService để xử lý logic xác thực
        return authService.signUp(signUpRequest);
    }
    @PostMapping("/auth/signin")
    public SignInDTO signIn(@RequestBody SignInDTO signInRequest){
        return authService.signIn(signInRequest);
    }
    @PostMapping("/auth/refresh")
    public RefreshTokenDTO refreshToken(@RequestBody RefreshTokenDTO refreshTokenRequest){
        return authService.refreshToken(refreshTokenRequest);
    }

}
