package com.sprboot.crud.entity;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="id")
@Table(name = "room_types")

public class RoomTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "SIZE")
    private int size;
    @Column(name = "NUM_OF_BED")
    private int numOfBed;
    @Column(name = "MAX_ADULTS")
    private int maxAdults;
    @Column(name = "MAX_CHILD")
    private int maxChild;


//    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
//    private List<RoomEntity> rooms;

//    @OneToMany(mappedBy = "roomType", cascade = CascadeType.ALL)
//    private List<ReservationEntity> reservations;

    public RoomTypeEntity() {

    }

    public RoomTypeEntity(String code, String name, String description, int size, int numOfBed, int maxAdults, int maxChild, List<RoomEntity> rooms, List<ReservationEntity> reservations) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.size = size;
        this.numOfBed = numOfBed;
        this.maxAdults = maxAdults;
        this.maxChild = maxChild;
//        this.rooms = rooms;
//        this.reservations = reservations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumOfBed() {
        return numOfBed;
    }

    public void setNumOfBed(int numOfBed) {
        this.numOfBed = numOfBed;
    }

    public int getMaxAdults() {
        return maxAdults;
    }

    public void setMaxAdults(int maxAdults) {
        this.maxAdults = maxAdults;
    }

    public int getMaxChild() {
        return maxChild;
    }

    public void setMaxChild(int maxChild) {
        this.maxChild = maxChild;
    }

//    public List<RoomEntity> getRooms() {
//        return rooms;
//    }

//    public void setRooms(List<RoomEntity> rooms) {
//        this.rooms = rooms;
//    }

//    public List<ReservationEntity> getReservations() {
//        return reservations;
//    }

//    public void setReservations(List<ReservationEntity> reservations) {
//        this.reservations = reservations;
//    }

    @Override
    public String toString() {
        return "RoomTypeEntity{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", size=" + size +
                ", numOfBed=" + numOfBed +
                ", maxAdults=" + maxAdults +
                ", maxChild=" + maxChild +
//                ", rooms=" + rooms +
//                ", reservations=" + reservations +
                '}';
    }
}
