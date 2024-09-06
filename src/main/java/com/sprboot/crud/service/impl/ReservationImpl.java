package com.sprboot.crud.service.impl;

import com.sprboot.crud.dto.ReservationDTO;
import com.sprboot.crud.dto.UpdateReservationDTO;
import com.sprboot.crud.entity.ReservationEntity;
import com.sprboot.crud.entity.RoomEntity;
import com.sprboot.crud.entity.RoomTypeEntity;
import com.sprboot.crud.repository.ReservationRepository;
import com.sprboot.crud.repository.RoomRepository;
import com.sprboot.crud.repository.RoomTypeRepository;
import com.sprboot.crud.service.Reservation;
import org.modelmapper.ModelMapper;
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
import java.util.stream.Collectors;

@Service
//annotation chỉ dc mark vào các class thường
public class ReservationImpl implements Reservation {
    @Autowired
    ReservationRepository reservationRepo;

    @Autowired
    RoomTypeRepository roomTypeRepo;

    @Autowired
    RoomRepository roomRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ReservationDTO> getAllReservation() {
        List<ReservationEntity> reservationResult = reservationRepo.findAll();
        return reservationResult.stream()
                .map(reservationEntity -> modelMapper.map(reservationEntity, ReservationDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO getReservationById(@PathVariable int id) {
        ReservationEntity reservationResult = reservationRepo.findById(id).get();
        return modelMapper.map(reservationResult, ReservationDTO.class);
    }

    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    public ReservationDTO createReservation(@RequestBody ReservationDTO reservationDTO) {
        ReservationEntity reservationEntity = modelMapper.map(reservationDTO, ReservationEntity.class);
        ReservationEntity savedReservationEntity = reservationRepo.save(reservationEntity);
        return modelMapper.map(savedReservationEntity, ReservationDTO.class);
    }

    @Override
    public ReservationDTO updateReservation(@RequestBody UpdateReservationDTO updateReservationDTO) {
        ReservationEntity updateReservation = reservationRepo.findById(updateReservationDTO.getId())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));
        updateReservation.setCode(updateReservationDTO.getCode());
        updateReservation.setCreateDate(updateReservationDTO.getCreateDate());
        updateReservation.setGuestEmail(updateReservationDTO.getGuestEmail());
        updateReservation.setGuestIdNo(updateReservationDTO.getGuestIdNo());
        updateReservation.setGuestName(updateReservationDTO.getGuestName());
        updateReservation.setGuestPhone(updateReservationDTO.getGuestPhone());
        updateReservation.setPrice(updateReservationDTO.getPrice());
        updateReservation.setStatus(updateReservationDTO.getStatus());
        // Tìm RoomTypeEntity từ CSDL dựa trên roomTypeId từ DTO
        RoomTypeEntity roomTypeEntity = roomTypeRepo.findById(updateReservationDTO.getRoomType().getId())
                .orElseThrow(() -> new RuntimeException("Room Type not found"));
        updateReservation.setRoomType(roomTypeEntity);

        // Tìm RoomEntity từ CSDL dựa trên roomId từ DTO
        RoomEntity roomEntity = roomRepo.findById(updateReservationDTO.getRoom().getId())
                .orElseThrow(() -> new RuntimeException("Room not found"));
        updateReservation.setRoom(roomEntity);

        // Lưu ReservationEntity vào cơ sở dữ liệu
        ReservationEntity updatedReservationEntity = reservationRepo.save(updateReservation);

        // Trả về ReservationDTO sau khi chuyển đổi từ ReservationEntity
        return modelMapper.map(updatedReservationEntity, ReservationDTO.class);
    }

    @Override
    public void removeReservation(@PathVariable int id) {
        ReservationEntity reservation = reservationRepo.findById(id).get();
        reservationRepo.delete(reservation);
    }


}
