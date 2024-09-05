package com.sprboot.crud.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sprboot.crud.entity.OurUserEntity;
import com.sprboot.crud.entity.ReservationEntity;
import com.sprboot.crud.entity.RoomEntity;
import com.sprboot.crud.entity.RoomTypeEntity;
import com.sprboot.crud.model.Reservation;
import com.sprboot.crud.model.Room;
import com.sprboot.crud.model.RoomType;
import lombok.Data;

import java.util.List;

@Data
// huỷ tuần tự hoá khi thu thap phan hoi tu nguoi dung va chuyen doi thanh object
@JsonIgnoreProperties(ignoreUnknown = true) // bo qua truong nguoi dung ko nhap
@JsonInclude(JsonInclude.Include.NON_NULL)

// su dung de truyen data giua service và controller
public class ReqRes {
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
    private List<RoomTypeEntity> roomTypes;
    private List<RoomEntity> rooms;
    private List<ReservationEntity> reservations;
    private OurUserEntity ourUsers;

}
