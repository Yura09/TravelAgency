package com.softserve.app.service.impl;

import com.softserve.app.dao.RoomDao;
import com.softserve.app.entity.Room;
import com.softserve.app.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Service
public class RoomServiceImpl implements RoomService {
    private RoomDao roomDao;

    @Autowired
    @Qualifier("roomDao")
    public void setRoomDao(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    @Transactional
    public void addRoom(Room room) {
        roomDao.addRoom(room);
    }

    @Override
    @Transactional
    public void updateRoom(Room room) {
        roomDao.updateRoom(room);
    }

    @Override
    @Transactional
    public void removeRoom(Long id) {
        roomDao.removeRoom(id);
    }

    @Override
    @Transactional
    public Room getRoomById(Long id) {
        return roomDao.getRoomById(id);
    }

    @Override
    @Transactional
    public List<Room> listRooms() {
        return roomDao.listRooms();
    }

    @Override
    @Transactional
    public List<Room> availableRoomsForPeriodInHotel(Long hotelId, Date checkIn, Date checkOut) {
        return roomDao.availableRoomsForPeriodInHotel(hotelId, checkIn, checkOut);
    }
}
