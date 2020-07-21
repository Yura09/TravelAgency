package com.softserve.app.dao;

import com.softserve.app.entity.Reservation;

import java.util.List;

public interface ReservationDao {
    void addReservation(Reservation reservation);

    void updateReservation(Reservation reservation);

    void removeReservation(Long id);

    Reservation getReservationById(Long id);

    List<Reservation> listReservations();

    List<Reservation> getReservationsByHotelName(String name);
}
