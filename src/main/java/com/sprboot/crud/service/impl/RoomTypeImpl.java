package com.sprboot.crud.service.impl;

import com.sprboot.crud.dto.RoomTypeDTO;
import com.sprboot.crud.dto.UpdateRoomTypeDTO;
import com.sprboot.crud.entity.RoomTypeEntity;
import com.sprboot.crud.repository.OurUserRepository;
import com.sprboot.crud.repository.RoomTypeRepository;
import com.sprboot.crud.service.RoomType;
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
public class RoomTypeImpl implements RoomType {
    @Autowired
    RoomTypeRepository repoRoomType;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    OurUserRepository ourUserRepository;

    @Override
    public List<RoomTypeDTO> getAllRoomTypes() {
        List<RoomTypeEntity> roomTypesResult = repoRoomType.findAll();
        return roomTypesResult.stream()
                .map(roomTypeEntity -> modelMapper.map(roomTypeEntity, RoomTypeDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public RoomTypeDTO getRoomType(@PathVariable int id) {
        RoomTypeEntity roomTypeResult = repoRoomType.findById(id).get();
        return modelMapper.map(roomTypeResult, RoomTypeDTO.class);
    }

    @ResponseStatus(code = HttpStatus.CREATED)
    @Override
    public RoomTypeDTO createRoomType(@RequestBody RoomTypeDTO roomTypeDTO) {
        RoomTypeEntity roomTypeEntity = modelMapper.map(roomTypeDTO, RoomTypeEntity.class);
        RoomTypeEntity savedRoomTypeEntity = repoRoomType.save(roomTypeEntity);
        return modelMapper.map(savedRoomTypeEntity, RoomTypeDTO.class);
    }

    @Override
    public RoomTypeDTO updateRoomType(@RequestBody UpdateRoomTypeDTO updateRoomTypeDTO) {
        RoomTypeEntity roomTypeEntity = repoRoomType.findById(updateRoomTypeDTO.getId())
                .orElseThrow(() -> new RuntimeException("RoomType not found"));
        roomTypeEntity.setCode(updateRoomTypeDTO.getCode());
        roomTypeEntity.setName(updateRoomTypeDTO.getName());
        roomTypeEntity.setDescription(updateRoomTypeDTO.getDescription());
        roomTypeEntity.setSize(updateRoomTypeDTO.getSize());
        roomTypeEntity.setNumOfBed(updateRoomTypeDTO.getNumOfBed());
        roomTypeEntity.setMaxAdults(updateRoomTypeDTO.getMaxAdults());
        roomTypeEntity.setMaxChild(updateRoomTypeDTO.getMaxChild());
        RoomTypeEntity updatedRoomTypeEntity = repoRoomType.save(roomTypeEntity);
        return modelMapper.map(updatedRoomTypeEntity, RoomTypeDTO.class);
    }


    @Override
    public void removeRoomType(@PathVariable int id) {
        RoomTypeEntity roomType = repoRoomType.findById(id).get();
        repoRoomType.delete(roomType);
    }

}
