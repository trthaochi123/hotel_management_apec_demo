package com.sprboot.crud.service;

import com.sprboot.crud.dto.RefreshTokenDTO;
import com.sprboot.crud.dto.SignInDTO;
import com.sprboot.crud.dto.SignUpDTO;
import com.sprboot.crud.entity.OurUserEntity;
import com.sprboot.crud.entity.RoomTypeEntity;
import com.sprboot.crud.model.OurUser;
import com.sprboot.crud.repository.OurUserRepository;
import com.sprboot.crud.service.impl.RoomTypeImpl;
import org.modelmapper.ModelMapper;
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
    private ModelMapper mapper;
    @Autowired
    private OurUserRepository ourUserRepo;
    @Autowired
    private JWTUtils jwtUtils;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;


    // dữ liệu từ server về client đóng gói vào ReqRes để gửi sang controller
    public SignUpDTO signUp(SignUpDTO Rq){
        SignUpDTO resp = new SignUpDTO();
        try {
            // tao doi tuong va gán gia trị vào các thuộc tính tương ứng
            // bằng phương thức set của OurUserEntity
            OurUserEntity ourUsers = new OurUserEntity();
            ourUsers.setEmail(Rq.getEmail());
            ourUsers.setPassword(passwordEncoder.encode(Rq.getPassword()));
            ourUsers.setRole(Rq.getRole());

            // lưu nguoi dung mơi vào CSDL thông qua ourUserRepo
            OurUserEntity ourUserResult = ourUserRepo.save(ourUsers);

            // kiem tra việc thêm mới người dùng có thành công ko
            if (ourUserResult != null && ourUserResult.getId()>0) {
                // entity to Dto
                OurUser userDto = mapper.map(ourUserResult, OurUser.class);
                resp.setOurUsers(userDto);
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

    public SignInDTO signIn(SignInDTO Rq){
        SignInDTO response = new SignInDTO();

        try {
            // xác thực thông tin đăng nhập thông qua authenticationManager
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(Rq.getEmail(),Rq.getPassword()));

            // truy vấn thông tin từ CSDL dựa trên email
            var user = ourUserRepo.findByEmail(Rq.getEmail()).orElseThrow();

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
    public RefreshTokenDTO refreshToken(RefreshTokenDTO Rq){
        // tao doi tuong ReqRes
        RefreshTokenDTO response = new RefreshTokenDTO();

        try {
            // Trích xuất email từ token cũ, truy vấn user dựa theo email
            String ourEmail = jwtUtils.extractUsername(Rq.getToken());
            OurUserEntity ourUserEntity = (OurUserEntity) ourUserRepo.findByEmail(ourEmail).orElseThrow();

            // Kiểm tra token có hợp lệ không
            if (jwtUtils.isTokenValid(Rq.getToken(), ourUserEntity)) {
                // Nếu hợp lệ, tạo token mới và trả về thông tin
                var jwt = jwtUtils.generateToken(ourUserEntity);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(Rq.getToken());
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully Refreshed Token");
            } else {
                // Nếu không hợp lệ, trả về thông báo lỗi
                response.setStatusCode(401);
                response.setMessage("Invalid or expired token");
            }
        } catch (Exception e) {
            // Bắt các ngoại lệ (ví dụ như không tìm thấy user) và trả về mã lỗi
            response.setStatusCode(500);
            response.setMessage("Server error: " + e.getMessage());
        }

        return response;
    }
}