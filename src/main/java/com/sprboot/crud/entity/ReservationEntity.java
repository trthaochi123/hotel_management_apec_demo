package com.sprboot.crud.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="id")
@Table(name = "reservations")

public class ReservationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "code")
    private String code;
    @Column(name = "guest_name")
    private String guestName;
    @Column(name = "guest_id_no")
    private String guestIdNo;
    @Column(name = "guest_phone")
    private String guestPhone;
    @Column(name = "guest_email")
    private String guestEmail;
    @Column(name = "created_date")
    private Date createDate;
    @Column(name = "price")
    private double price;
    @Column(name = "status")
    private int status;

    // cần chứa 2 khoá ngoại
    @ManyToOne
    @JoinColumn(name = "room_id")
    private RoomEntity room;

    @ManyToOne
    @JoinColumn(name = "room_type_id")
    private RoomTypeEntity roomType;


}
