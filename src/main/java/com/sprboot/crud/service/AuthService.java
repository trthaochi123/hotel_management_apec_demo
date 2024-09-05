package com.sprboot.crud.service;

import com.sprboot.crud.dto.ReqRes;
import com.sprboot.crud.entity.OurUserEntity;
import com.sprboot.crud.entity.RoomTypeEntity;
import com.sprboot.crud.repository.OurUserRepository;
import com.sprboot.crud.service.impl.RoomTypeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
// Bao gồm các phương thức để xử lý logic nghiệp vụ cho xác thực người dùng
public class AuthService {

    @Autowired
    private OurUserRepository ourUserRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;


    // dữ liệu từ server về client đóng gói vào ReqRes để gửi sang controller
    public ReqRes signUp(ReqRes registrationRequest){
        ReqRes resp = new ReqRes();
        try {
            // tao doi tuong va gán gia trị vào các thuộc tính tương ứng
            // bằng phương thức set của OurUserEntity
            OurUserEntity ourUsers = new OurUserEntity();
            ourUsers.setEmail(registrationRequest.getEmail());
            ourUsers.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
            ourUsers.setRole(registrationRequest.getRole());

            // lưu nguoi dung mơi vào CSDL thông qua ourUserRepo
            OurUserEntity ourUserResult = ourUserRepo.save(ourUsers);

            // kiem tra việc thêm mới người dùng có thành công ko
            if (ourUserResult != null && ourUserResult.getId()>0) {
                resp.setOurUsers(ourUserResult);
                resp.setMessage("User Saved Successfully");
                resp.setStatusCode(200);
            }
        }catch (Exception e){
            // nếu lỗi trả về lỗi 500 cùng message
            resp.setStatusCode(500);
            resp.setError(e.getMessage());
        }

        // trả về đối tượng ReqRes chứa thông tin về quá trình signup
        return resp;
    }

    public ReqRes signIn(ReqRes signinRequest){
        ReqRes response = new ReqRes();

        try {
            // xác thực thông tin đăng nhập thông qua authenticationManager
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signinRequest.getEmail(),signinRequest.getPassword()));

            // truy vấn thông tin từ CSDL dựa trên email
            var user = ourUserRepo.findByEmail(signinRequest.getEmail()).orElseThrow();

            System.out.println("USER IS: "+ user);

            // tạo token và refresh token cho user sau khi xác thực thành công
            var jwt = jwtUtils.generateToken((UserDetails) user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);

            // set các giá trị để trả về sau khi đăng nhập thành công
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hr");
            response.setMessage("Da dang nhap thanh cong");
        }catch (Exception e){
            response.setStatusCode(300);
            response.setError(e.getMessage());
        }
        return response;
    }


    // làm mới token khi hết hạn
    public ReqRes refreshToken(ReqRes refreshTokenReqiest){
        // tao doi tuong ReqRes
        ReqRes response = new ReqRes();

        // trích xuất email từ token cũ, truy vấn user dựa theo email
        String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
        OurUserEntity ourUserEntity = (OurUserEntity) ourUserRepo.findByEmail(ourEmail).orElseThrow();

        // kiểm tra token có hợp lệ ko, nếu hợp lệ tạo 1 token mới và trả về noti
        if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), ourUserEntity)) {
            var jwt = jwtUtils.generateToken(ourUserEntity);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRefreshToken(refreshTokenReqiest.getToken());
            response.setExpirationTime("24Hr");
            response.setMessage("Successfully Refreshed Token");
        }
        response.setStatusCode(500);
        return response;
    }
}