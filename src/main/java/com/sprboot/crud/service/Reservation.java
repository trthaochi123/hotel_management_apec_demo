package com.sprboot.crud.service;

import com.sprboot.crud.entity.ReservationEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface Reservation {
    List<ReservationEntity> getAllReservation();
    ReservationEntity getReservationById(@PathVariable int id);
    void createReservation(@RequestBody ReservationEntity reservation);
    ReservationEntity updateReservation(@PathVariable int id, @RequestBody ReservationEntity reservation);
    void removeReservation(@PathVariable int id);
}
