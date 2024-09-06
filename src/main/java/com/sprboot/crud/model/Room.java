package com.sprboot.crud.model;

import com.sprboot.crud.dto.RoomTypeDTO;
import com.sprboot.crud.entity.ReservationEntity;
import com.sprboot.crud.entity.RoomTypeEntity;
import jakarta.persistence.*;

import java.util.List;

public class Room {
    private int id;

    private String code;

    private String name;

    private String description;

    private String floor;


    // cần chứa khoá ngoại ROOM_TYPE_ID
    private RoomTypeDTO roomType;

}
