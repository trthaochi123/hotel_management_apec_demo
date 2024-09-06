package com.sprboot.crud.service;

import com.sprboot.crud.dto.ReservationDTO;
import com.sprboot.crud.dto.UpdateReservationDTO;
import com.sprboot.crud.entity.ReservationEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface Reservation {
    List<ReservationDTO> getAllReservation();
    ReservationDTO getReservationById(@PathVariable int id);
    ReservationDTO createReservation(@RequestBody ReservationDTO reservation);
    ReservationDTO updateReservation(@RequestBody UpdateReservationDTO updateReservationDTO);
    void removeReservation(@PathVariable int id);
}
