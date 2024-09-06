package com.sprboot.crud.service;

import com.sprboot.crud.dto.RoomTypeDTO;
import com.sprboot.crud.dto.UpdateRoomTypeDTO;
import com.sprboot.crud.entity.RoomTypeEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface RoomType {
    List<RoomTypeDTO> getAllRoomTypes();
    RoomTypeDTO getRoomType(@PathVariable int id);
    RoomTypeDTO createRoomType(@RequestBody RoomTypeDTO roomType);
    RoomTypeDTO updateRoomType(@RequestBody UpdateRoomTypeDTO updateRoomTypeDTO);
    void removeRoomType(@PathVariable int id);
}
