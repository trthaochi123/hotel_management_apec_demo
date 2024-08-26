package com.sprboot.crud.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property="id")
@Table(name = "reservations")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "CODE")
    private String code;
    @Column(name = "GUEST_NAME")
    private String guestName;
    @Column(name = "GUEST_ID_NO")
    private String guestIdNo;
    @Column(name = "GUEST_PHONE")
    private String guestPhone;
    @Column(name = "GUEST_EMAIL")
    private String guestEmail;
    @Column(name = "CREATED_DATE")
    private Date createDate;
    @Column(name = "PRICE")
    private double price;
    @Column(name = "STATUS")
    private int status;

    // cần chứa 2 khoá ngoại
    @ManyToOne
    @JoinColumn(name = "ROOM_ID")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "ROOM_TYPE_ID")
    private RoomType roomType;



    public Reservation() {
    }

    public Reservation(int id, String code, String guestName, String guestIdNo, String guestPhone, String guestEmail, Date createDate, double price, int status, RoomType roomType, Room room) {
        this.id = id;
        this.code = code;
        this.guestName = guestName;
        this.guestIdNo = guestIdNo;
        this.guestPhone = guestPhone;
        this.guestEmail = guestEmail;
        this.createDate = createDate;
        this.price = price;
        this.status = status;
        this.roomType = roomType;
        this.room = room;
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

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public String getGuestIdNo() {
        return guestIdNo;
    }

    public void setGuestIdNo(String guestIdNo) {
        this.guestIdNo = guestIdNo;
    }

    public String getGuestPhone() {
        return guestPhone;
    }

    public void setGuestPhone(String guestPhone) {
        this.guestPhone = guestPhone;
    }

    public String getGuestEmail() {
        return guestEmail;
    }

    public void setGuestEmail(String guestEmail) {
        this.guestEmail = guestEmail;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "ReservationRepository{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", guestName='" + guestName + '\'' +
                ", guestIdNo='" + guestIdNo + '\'' +
                ", guestPhone='" + guestPhone + '\'' +
                ", guestEmail='" + guestEmail + '\'' +
                ", createDate=" + createDate +
                ", price=" + price +
                ", status=" + status +
                ", roomType=" + roomType +
                ", room=" + room +
                '}';
    }
}
