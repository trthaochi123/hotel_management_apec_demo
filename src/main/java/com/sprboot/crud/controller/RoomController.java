package com.sprboot.crud.controller;

import com.sprboot.crud.entity.RoomEntity;
import com.sprboot.crud.repository.RoomRepository;
import com.sprboot.crud.repository.RoomTypeRepository;
import com.sprboot.crud.service.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {
    @Autowired
    Room roomInterface;

    @Autowired
    RoomRepository roomRepo;

    @GetMapping("/api/guest/rooms")
    public List<RoomEntity> getAllRoom() {
        return roomInterface.getAllRoom();
    }

    @GetMapping("/api/guest/rooms/{id}")
    public RoomEntity getRoomById(@PathVariable int id) {
        return roomInterface.getRoomById(id);
    }

    //Post: dùng @RequestBody de gui thong tin data duoi dang JSON/XML
    @PostMapping("/api/admin/rooms/add")
    public void createRoom(@RequestBody RoomEntity rooms) {
        roomInterface.createRoom(rooms);
    }


    //Put
    @PutMapping("/api/admin/rooms/update/{id}")
    public RoomEntity updateRoom(@PathVariable int id, @RequestBody RoomEntity room) {
        return roomInterface.updateRoom(id, room);
    }

    //Delete
    @DeleteMapping("/api/admin/rooms/delete/{id}")
    public void removeRoom(@PathVariable int id) {
        roomInterface.removeRoom(id);
    }
}
