package com.sprboot.crud.dto;

import com.sprboot.crud.entity.RoomTypeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomDTO {
    private int id;

    private String code;

    private String name;

    private String description;

    private String floor;

    private RoomTypeDTO roomType;
}
