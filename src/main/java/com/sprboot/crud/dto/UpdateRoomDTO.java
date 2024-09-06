package com.sprboot.crud.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateRoomDTO {
    private int id;

    private String code;

    private String name;

    private String description;

    private String floor;

    private RoomTypeDTO roomType;
}
