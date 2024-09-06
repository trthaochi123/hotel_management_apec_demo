package com.sprboot.crud.dto;

import com.sprboot.crud.entity.RoomEntity;
import com.sprboot.crud.entity.RoomTypeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateReservationDTO {
    private int id;
    private String code;
    private String guestName;
    private String guestIdNo;
    private String guestPhone;
    private String guestEmail;
    private Date createDate;
    private double price;
    private int status;

    private RoomDTO room;

    private RoomTypeDTO roomType;
}
