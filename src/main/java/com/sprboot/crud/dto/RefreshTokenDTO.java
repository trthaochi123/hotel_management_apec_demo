package com.sprboot.crud.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sprboot.crud.model.OurUser;
import lombok.Data;

import java.util.List;

@Data
// huỷ tuần tự hoá khi thu thap phan hoi tu nguoi dung va chuyen doi thanh object
@JsonIgnoreProperties(ignoreUnknown = true) // bo qua truong nguoi dung ko nhap
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RefreshTokenDTO {
    private int statusCode;
    private String error;
    private String message;
    private String token;
    private String refreshToken;
    private String expirationTime;
    private String name;
    private String email;
    private String role;
    private String password;
    private OurUser ourUsers;
}
