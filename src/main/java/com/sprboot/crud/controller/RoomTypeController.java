package com.sprboot.crud.controller;

import com.sprboot.crud.dto.RoomTypeDTO;
import com.sprboot.crud.dto.UpdateRoomTypeDTO;
import com.sprboot.crud.repository.RoomTypeRepository;
import com.sprboot.crud.service.AuthService;
import com.sprboot.crud.service.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomTypeController {
    @Autowired
    RoomType roomTypeInterface;

    @Autowired
    RoomTypeRepository roomTypeRepo;

    // localhost:8080/api/guest/roomTypes
    @GetMapping("/api/guest/roomTypes")
    public List<RoomTypeDTO> getAllRoomTypes() {
        return roomTypeInterface.getAllRoomTypes();
    }

    @GetMapping("/api/guest/roomTypes/{id}")
    public RoomTypeDTO getRoomType(@PathVariable int id) {

        return roomTypeInterface.getRoomType(id);
    }

    @PostMapping("/api/admin/roomTypes/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public RoomTypeDTO createRoomType(@RequestBody RoomTypeDTO roomType) {
        return roomTypeInterface.createRoomType(roomType);
    }


    // localhost:8080/api/admin/roomTypes/update/id
    @PutMapping("/api/admin/roomTypes/update/{id}")
    public RoomTypeDTO updateRoomType(@RequestBody UpdateRoomTypeDTO updateRoomTypeDTO) {
        return roomTypeInterface.updateRoomType(updateRoomTypeDTO);
    }

    // localhost:8080/api/admin/roomTypes/delete/12
    @DeleteMapping("/api/admin/roomTypes/delete/{id}")
    public void removeRoomType(@PathVariable int id) {
        roomTypeInterface.removeRoomType(id);
    } 
}
