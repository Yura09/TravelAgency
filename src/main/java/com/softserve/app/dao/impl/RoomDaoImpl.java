package com.softserve.app.dao.impl;

import com.softserve.app.dao.RoomDao;
import com.softserve.app.entity.Reservation;
import com.softserve.app.entity.Room;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository("roomDao")
public class RoomDaoImpl implements RoomDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addRoom(Room room) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(room);
    }

    @Override
    public void updateRoom(Room room) {
        Session session = sessionFactory.getCurrentSession();
        session.update(room);
    }

    @Override
    public void removeRoom(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Room room = session.load(Room.class, id);
        if (room != null) {
            session.delete(room);
        }
    }

    @Override
    public Room getRoomById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Room room = session.load(Room.class, id);
        if (room == null) {
            throw new NoSuchElementException("room with " + id + " doesn't exist");
        }

        return room;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Room> listRooms() {
        Session session = sessionFactory.getCurrentSession();
        return (List<Room>) session.createQuery("from com.softserve.app.entity.Room").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Room> availableRoomsForPeriodInHotel(Long hotelId, Date checkIn, Date checkOut) {
        Session session = sessionFactory.getCurrentSession();

        List<Room> rooms = (List<Room>) session.createQuery("from Room room where room.hotel.id=:hotelId").setParameter("hotelId", hotelId).list();
        List<Room> roomList = new ArrayList<>();
        for (Room room : rooms) {
            if (room.getReservations().size()==0) {
                roomList.add(room);
                continue;
            }
            for (Reservation reservation : room.getReservations()) {

                if (reservation.getCheckOut().compareTo(checkIn) <= 0 || reservation.getCheckIn().compareTo(checkOut) >= 0) {
                    roomList.add(room);
                }
            }

        }
        return roomList;
     //   return rooms.stream().flatMap(r -> r.getReservations().stream()).filter(Objects::nonNull).filter(x -> (x.getCheckOut().compareTo(checkIn) <= 0 || x.getCheckIn().compareTo(checkOut) >= 0)).map(Room.class::cast).collect(Collectors.toList());

    }
}
