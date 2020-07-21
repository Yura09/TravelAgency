package com.softserve.app.service;

import com.softserve.app.entity.Room;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public interface RoomService {
    void addRoom(Room room);

    void updateRoom(Room room);

    void removeRoom(Long id);

    Room getRoomById(Long id);

    List<Room> listRooms();

    List<Room> availableRoomsForPeriodInHotel(Long hotelId, Date checkIn, Date checkOut);


}
