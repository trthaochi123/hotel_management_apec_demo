package com.sprboot.crud.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RoomTypeDTO {
    private int id;

    private String code;

    private String name;

    private String description;

    private int size;

    private int numOfBed;

    private int maxAdults;

    private int maxChild;
}
