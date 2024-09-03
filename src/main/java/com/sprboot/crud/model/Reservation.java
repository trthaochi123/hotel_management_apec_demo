package com.sprboot.crud.model;

import com.sprboot.crud.entity.RoomEntity;
import com.sprboot.crud.entity.RoomTypeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

public class Reservation {
    private int id;
    private String code;
    private String guestName;
    private String guestIdNo;
    private String guestPhone;
    private String guestEmail;
    private Date createDate;
    private double price;
    private int status;

    // cần chứa 2 khoá ngoại
    private RoomEntity room;
    private RoomTypeEntity roomType;
}
