package com.sprboot.crud.service;

import com.sprboot.crud.dto.RoomDTO;
import com.sprboot.crud.dto.UpdateRoomDTO;
import com.sprboot.crud.entity.RoomEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface Room {
    List<RoomDTO> getAllRoom();
    RoomDTO getRoomById(@PathVariable int id);
    RoomDTO createRoom(@RequestBody RoomDTO rooms);
    RoomDTO updateRoom(@RequestBody UpdateRoomDTO updateRoomDTO);
    void removeRoom(@PathVariable int id);
}
