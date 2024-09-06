package com.sprboot.crud.controller;

import com.sprboot.crud.dto.ReservationDTO;
import com.sprboot.crud.dto.UpdateReservationDTO;
import com.sprboot.crud.repository.ReservationRepository;
import com.sprboot.crud.service.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReservationController {
    @Autowired
    Reservation reservationInterface; // service interface

    @Autowired
    ReservationRepository reservationRepo;

    // get
    @GetMapping("/api/guest/reservations")
    public List<ReservationDTO> getAllReservation() {
        return reservationInterface.getAllReservation();
    }

    @GetMapping("/api/guest/reservations/{id}")
    public ReservationDTO getReservation(@PathVariable int id) {
        return reservationInterface.getReservationById(id);
    }

    // post
    @PostMapping("/api/admin/reservations/add")
    @ResponseStatus(code = HttpStatus.CREATED)
    public ReservationDTO createReservation(@RequestBody ReservationDTO reservation) {
       return reservationInterface.createReservation(reservation);
    }


    // put
    @PutMapping("/api/admin/reservations/update/{id}")
    public ReservationDTO updateReservation(@RequestBody UpdateReservationDTO updateReservationDTO) {
        return reservationInterface.updateReservation(updateReservationDTO);
    }


    // delete
    @DeleteMapping("/api/admin/reservations/delete/{id}")
    public void removeReservation(@PathVariable int id) {
        reservationInterface.removeReservation(id);
    }
}
