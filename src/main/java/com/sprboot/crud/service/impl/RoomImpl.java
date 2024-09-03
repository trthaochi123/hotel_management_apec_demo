package com.sprboot.crud.service.impl;

import com.sprboot.crud.entity.RoomEntity;
import com.sprboot.crud.repository.RoomRepository;
import com.sprboot.crud.service.Room;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;

@Service
public class RoomImpl implements Room {
    @Autowired
    RoomRepository repo;
    @Override
    public List<RoomEntity> getAllRoom() {
        List<RoomEntity> rooms = repo.findAll();
        return rooms;    }

    @Override
    public RoomEntity getRoomById(@PathVariable int id) {
        RoomEntity room = repo.findById(id).get();
        return room;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @Override
    public void createRoom(@RequestBody RoomEntity rooms) {
        repo.save(rooms);
    }

    @Override
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

    @Override
    public void removeRoom(@PathVariable int id) {
        RoomEntity room = repo.findById(id).get();
        repo.delete(room);
    }
}
