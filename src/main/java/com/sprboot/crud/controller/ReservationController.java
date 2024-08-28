package com.sprboot.crud.controller;

import com.sprboot.crud.entity.ReservationEntity;
import com.sprboot.crud.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    ReservationRepository repo;

    // get
    @GetMapping("/api/guest/reservations")
    public List<ReservationEntity> getAllReservation() {
        List<ReservationEntity> reservations = repo.findAll();
        return reservations;
    }

    @GetMapping("/api/guest/reservations/{id}")
    public ReservationEntity getReservation(@PathVariable int id) {
        ReservationEntity reservation = repo.findById(id).get();
        return reservation;
    }

    // post
    @PostMapping("/api/admin/reservations/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public void createReservation(@RequestBody ReservationEntity reservation) {

        repo.save(reservation);
    }


    // put
    @PutMapping("/api/admin/reservations/update/{id}")
    public ReservationEntity updateReservation(@PathVariable int id, @RequestBody ReservationEntity reservation) {
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



    // delete
    @DeleteMapping("/api/admin/reservations/delete/{id}")
    public void removeReservation(@PathVariable int id) {
        ReservationEntity reservation = repo.findById(id).get();
        repo.delete(reservation);
    }
}
