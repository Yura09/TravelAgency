package com.softserve.app.service;

import com.softserve.app.entity.Reservation;

import java.util.List;

public interface ReservationService {
    void addReservation(Reservation reservation);

    void updateReservation(Reservation reservation);

    void removeReservation(Long id);

    Reservation getReservationById(Long id);

    List<Reservation> listReservations();

    List<Reservation> getReservationsByHotelName(String name);
}
