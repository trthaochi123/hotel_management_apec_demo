package com.sprboot.crud.service.impl;

import com.sprboot.crud.dto.RoomDTO;
import com.sprboot.crud.dto.UpdateRoomDTO;
import com.sprboot.crud.entity.RoomEntity;
import com.sprboot.crud.entity.RoomTypeEntity;
import com.sprboot.crud.repository.OurUserRepository;
import com.sprboot.crud.repository.RoomRepository;
import com.sprboot.crud.repository.RoomTypeRepository;
import com.sprboot.crud.service.Room;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomImpl implements Room {

    @Autowired
    ModelMapper modelMapper;
    @Autowired
    RoomRepository repo;

    @Autowired
    RoomTypeRepository roomTypeRepository;

    @Autowired
    OurUserRepository ourUserRepository;

    @Override
    public List<RoomDTO> getAllRoom() {
        List<RoomEntity> roomsResult = repo.findAll();
        return roomsResult.stream()
                .map(roomEntity -> modelMapper.map(roomEntity, RoomDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomDTO getRoomById(@PathVariable int id) {
        RoomEntity roomResult = repo.findById(id).get();
        return modelMapper.map(roomResult, RoomDTO.class);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @Override
    public RoomDTO createRoom(@RequestBody RoomDTO roomDTO) {
        RoomEntity roomEntity = modelMapper.map(roomDTO, RoomEntity.class);
        RoomEntity savedRoomEntity = repo.save(roomEntity);
        return modelMapper.map(savedRoomEntity, RoomDTO.class);
    }

    @Override
    public RoomDTO updateRoom(@RequestBody UpdateRoomDTO updateRoomDTO) {
        // Tìm đối tượng RoomEntity hiện tại
        RoomEntity updateRoom = repo.findById(updateRoomDTO.getId())
                .orElseThrow(() -> new RuntimeException("Room not found"));
        updateRoom.setCode(updateRoomDTO.getCode());
        updateRoom.setName(updateRoomDTO.getName());
        updateRoom.setDescription(updateRoomDTO.getDescription());
        updateRoom.setFloor(updateRoomDTO.getFloor());
        RoomTypeEntity roomTypeEntity = roomTypeRepository.findById(updateRoomDTO.getRoomType().getId())
                .orElseThrow(() -> new RuntimeException("Room Type not found"));
        updateRoom.setRoomType(roomTypeEntity);
        RoomEntity updatedRoomEntity = repo.save(updateRoom);
        return modelMapper.map(updatedRoomEntity, RoomDTO.class);
    }


    @Override
    public void removeRoom(@PathVariable int id) {
        RoomEntity room = repo.findById(id).get();
        repo.delete(room);
    }
}
