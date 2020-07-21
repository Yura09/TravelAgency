package com.softserve.app.service.impl;

import com.softserve.app.dao.ReservationDao;
import com.softserve.app.entity.Reservation;
import com.softserve.app.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReservationServiceImpl implements ReservationService {
    private ReservationDao reservationDao;

    @Autowired
    public void setReservationDao(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    @Override
    @Transactional
    public void addReservation(Reservation reservation) {
        reservationDao.addReservation(reservation);
    }

    @Override
    @Transactional
    public void updateReservation(Reservation reservation) {
        reservationDao.updateReservation(reservation);
    }

    @Override
    @Transactional
    public void removeReservation(Long id) {
        reservationDao.removeReservation(id);
    }

    @Override
    @Transactional
    public Reservation getReservationById(Long id) {
        return reservationDao.getReservationById(id);
    }

    @Override
    @Transactional
    public List<Reservation> listReservations() {
        return reservationDao.listReservations();
    }

    @Override
    @Transactional
    public List<Reservation> getReservationsByHotelName(String name) {
        return reservationDao.getReservationsByHotelName(name);
    }
}
