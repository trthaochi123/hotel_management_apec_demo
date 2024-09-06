package com.sprboot.crud.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sprboot.crud.model.OurUser;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SignUpDTO {
    private int statusCode;
    private String error;
    private String message;
    private String email;
    private String role;
    private String password;
    private OurUser ourUsers;
}
