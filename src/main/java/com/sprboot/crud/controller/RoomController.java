package com.sprboot.crud.controller;

import com.sprboot.crud.entity.Room;
import com.sprboot.crud.entity.RoomType;
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
    public List<Room> getAllRoom() {
        List<Room> rooms = repo.findAll();
        return rooms;
    }

    @GetMapping("/api/guest/rooms/{id}")
    public Room getRoom(@PathVariable int id) {
        Room room = repo.findById(id).get();
        return room;
    }

    //Post: dùng @RequestBody de gui thong tin data duoi dang JSON/XML
    @PostMapping("/api/admin/rooms/add")
    public void createRoom(@RequestBody Room rooms) {
        repo.save(rooms);
    }


    //Put
    @PutMapping("/api/admin/rooms/update/{id}")
    public Room updateRoom(@PathVariable int id, @RequestBody Room room) {
        Room updateRoom = repo.findById(id).get(); // tìm đối tượng  Room hiện tại
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
        Room room = repo.findById(id).get();
        repo.delete(room);
    }
}
