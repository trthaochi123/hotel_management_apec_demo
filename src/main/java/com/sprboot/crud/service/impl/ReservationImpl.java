package com.sprboot.crud.service.impl;

import com.sprboot.crud.entity.ReservationEntity;
import com.sprboot.crud.repository.ReservationRepository;
import com.sprboot.crud.service.Reservation;
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
//annotation chỉ dc mark vào các class thường
public class ReservationImpl implements Reservation {
    @Autowired
    ReservationRepository repo;

    @Override
    public List<ReservationEntity> getAllReservation() {
        List<ReservationEntity> reservations = repo.findAll();
        return reservations;
    }

    @Override
    public ReservationEntity getReservationById(@PathVariable int id) {
        ReservationEntity reservation = repo.findById(id).get();
        return reservation;
    }

    @Override
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createReservation(@RequestBody ReservationEntity reservation) {
        repo.save(reservation);
    }

    @Override
    public ReservationEntity updateReservation(@PathVariable int id, ReservationEntity reservation) {
        ReservationEntity updateReservation = repo.findById(id).get();
        updateReservation.setCode(reservation.getCode());
        updateReservation.setCreateDate(reservation.getCreateDate());
        updateReservation.setGuestEmail(reservation.getGuestEmail());
        updateReservation.setGuestIdNo(reservation.getGuestIdNo());
        updateReservation.setGuestName(reservation.getGuestName());
        updateReservation.setGuestPhone(reservation.getGuestPhone());
        updateReservation.setPrice(reservation.getPrice());
        updateReservation.setStatus(reservation.getStatus());
        updateReservation.setRoom(reservation.getRoom());
        updateReservation.setRoomType(reservation.getRoomType());
        return repo.save(updateReservation);
    }

    @Override
    public void removeReservation(@PathVariable int id) {
        ReservationEntity reservation = repo.findById(id).get();
        repo.delete(reservation);
    }


}
