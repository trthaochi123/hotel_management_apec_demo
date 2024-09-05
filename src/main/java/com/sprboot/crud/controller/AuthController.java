package com.sprboot.crud.controller;

import com.sprboot.crud.dto.ReqRes;
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
    public ResponseEntity<ReqRes> signUp(@RequestBody ReqRes signUpRequest){
        // Gọi AuthService để xử lý logic xác thực
        return ResponseEntity.ok(authService.signUp(signUpRequest));
    }
    @PostMapping("/auth/signin")
    public ResponseEntity<ReqRes> signIn(@RequestBody ReqRes signInRequest){
        return ResponseEntity.ok(authService.signIn(signInRequest));
    }
    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes refreshTokenRequest){
        return ResponseEntity.ok(authService.refreshToken(refreshTokenRequest));
    }
}
