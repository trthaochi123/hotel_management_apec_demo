package com.sprboot.crud.service;

import com.sprboot.crud.entity.RoomTypeEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RoomType {
    List<RoomTypeEntity> getAllRoomTypes();
    RoomTypeEntity getRoomType(@PathVariable int id);
    void createRoomType(@RequestBody RoomTypeEntity roomType);
    RoomTypeEntity updateRoomType(@PathVariable int id, @RequestBody RoomTypeEntity roomType);
    void removeRoomType(@PathVariable int id);
}
