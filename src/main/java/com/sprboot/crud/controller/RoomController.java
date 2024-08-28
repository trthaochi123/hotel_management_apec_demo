package com.sprboot.crud.controller;

import com.sprboot.crud.entity.RoomEntity;
import com.sprboot.crud.repository.RoomRepository;
import com.sprboot.crud.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomController {
    @Autowired
    RoomRepository repo;
    RoomTypeRepository repoRoomTypes;

    @GetMapping("/api/guest/rooms")
    public List<RoomEntity> getAllRoom() {
        List<RoomEntity> rooms = repo.findAll();
        return rooms;
    }

    @GetMapping("/api/guest/rooms/{id}")
    public RoomEntity getRoom(@PathVariable int id) {
        RoomEntity room = repo.findById(id).get();
        return room;
    }

    //Post: dùng @RequestBody de gui thong tin data duoi dang JSON/XML
    @PostMapping("/api/admin/rooms/add")
    public void createRoom(@RequestBody RoomEntity rooms) {
        repo.save(rooms);
    }


    //Put
    @PutMapping("/api/admin/rooms/update/{id}")
    public RoomEntity updateRoom(@PathVariable int id, @RequestBody RoomEntity room) {
        RoomEntity updateRoom = repo.findById(id).get(); // tìm đối tượng  RoomEntity hiện tại
        // lay doi tuong từ @RequestBody, gan vao setCode() cua doi tuong updateRoom
        updateRoom.setCode(room.getCode());
        updateRoom.setName(room.getName());
        updateRoom.setDescription(room.getDescription());
        updateRoom.setFloor(room.getFloor());
        updateRoom.setRoomType(room.getRoomType());
        // được lưu trở lại vào cơ sở dữ liệu
        return repo.save(updateRoom);
    }

    //Delete
    @DeleteMapping("/api/admin/rooms/delete/{id}")
    public void removeRoom(@PathVariable int id) {
        RoomEntity room = repo.findById(id).get();
        repo.delete(room);
    }
}
