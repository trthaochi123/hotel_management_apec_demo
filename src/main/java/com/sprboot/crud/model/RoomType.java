package com.sprboot.crud.model;

import jakarta.persistence.Column;

public class RoomType {
    private int id;

    private String code;

    private String name;

    private String description;

    private int size;
    private int numOfBed;
    private int maxAdults;
    private int maxChild;

}
