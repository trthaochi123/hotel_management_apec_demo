package com.sprboot.crud.service.impl;

import com.sprboot.crud.entity.RoomTypeEntity;
import com.sprboot.crud.repository.OurUserRepository;
import com.sprboot.crud.repository.RoomTypeRepository;
import com.sprboot.crud.service.RoomType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

@Service
public class RoomTypeImpl implements RoomType {
    @Autowired
    RoomTypeRepository repoRoomType;

    @Autowired
    OurUserRepository ourUserRepository;
    @Override
    public List<RoomTypeEntity> getAllRoomTypes() {
        List<RoomTypeEntity> roomTypes = repoRoomType.findAll();
        return roomTypes;
    }

    @Override
    public RoomTypeEntity getRoomType(@PathVariable int id) {
        RoomTypeEntity roomType = repoRoomType.findById(id).get();
        return roomType;
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @Override
    public void createRoomType(@RequestBody RoomTypeEntity roomType) {
        repoRoomType.save(roomType);
    }

    @Override
    public RoomTypeEntity updateRoomType(@PathVariable int id, @RequestBody RoomTypeEntity roomType) {
        RoomTypeEntity updateRoomType = repoRoomType.findById(id).get();
        updateRoomType.setCode(roomType.getCode());
        updateRoomType.setName(roomType.getName());
        updateRoomType.setDescription(roomType.getDescription());
        updateRoomType.setSize(roomType.getSize());
        updateRoomType.setNumOfBed(roomType.getNumOfBed());
        updateRoomType.setMaxAdults(roomType.getMaxAdults());
        updateRoomType.setMaxChild(roomType.getMaxChild());
        return repoRoomType.save(updateRoomType);    }

    @Override
    public void removeRoomType(@PathVariable int id) {
        RoomTypeEntity roomType = repoRoomType.findById(id).get();
        repoRoomType.delete(roomType);
    }

}
