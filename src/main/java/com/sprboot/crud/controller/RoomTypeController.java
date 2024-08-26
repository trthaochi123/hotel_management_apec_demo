package com.sprboot.crud.controller;

import com.sprboot.crud.entity.RoomType;
import com.sprboot.crud.repository.RoomTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RoomTypeController {
    @Autowired
    RoomTypeRepository repo;

    @GetMapping("/api/guest/roomTypes")
    public List<RoomType> getAllRoomTypes() {
        List<RoomType> roomTypes = repo.findAll();
        return roomTypes;
    }

    @GetMapping("/api/guest/roomTypes/{id}")
    public RoomType getRoomType(@PathVariable int id) {
        RoomType roomType = repo.findById(id).get();
        return roomType;
    }

    //@RequestBody de gui thong tin data duoi dang JSON/XML hoac form-data
    @PostMapping("/api/admin/roomTypes/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createRoomType(@RequestBody RoomType roomType) {
        repo.save(roomType);
    }


    @PutMapping("/api/admin/roomTypes/update/{id}")
    public RoomType updateRoomType(@PathVariable int id, @RequestBody RoomType roomType) {
        RoomType updateToomType = repo.findById(id).get();
        updateToomType.setCode(roomType.getCode());
        updateToomType.setName(roomType.getName());
        updateToomType.setDescription(roomType.getDescription());
        updateToomType.setSize(roomType.getSize());
        updateToomType.setNumOfBed(roomType.getNumOfBed());
        updateToomType.setMaxAdults(roomType.getMaxAdults());
        updateToomType.setMaxChild(roomType.getMaxChild());
        return repo.save(updateToomType);
    }

    @DeleteMapping("/api/admin/roomTypes/delete/{id}")
    public void removeRoomType(@PathVariable int id) {
        RoomType roomType = repo.findById(id).get();
        repo.delete(roomType);
    } 
}
