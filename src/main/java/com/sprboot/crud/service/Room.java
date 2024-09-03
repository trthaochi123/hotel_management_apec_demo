package com.sprboot.crud.service;

import com.sprboot.crud.entity.RoomEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface Room {
    List<RoomEntity> getAllRoom();
    RoomEntity getRoomById(@PathVariable int id);
    void createRoom(@RequestBody RoomEntity rooms);
    RoomEntity updateRoom(@PathVariable int id, @RequestBody RoomEntity room);
    void removeRoom(@PathVariable int id);
}
