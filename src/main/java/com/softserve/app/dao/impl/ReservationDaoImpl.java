package com.softserve.app.dao.impl;

import com.softserve.app.dao.ReservationDao;
import com.softserve.app.entity.Reservation;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ReservationDaoImpl implements ReservationDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addReservation(Reservation reservation) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(reservation);
    }

    @Override
    public void updateReservation(Reservation reservation) {
        Session session = sessionFactory.getCurrentSession();
        session.update(reservation);
    }

    @Override
    public void removeReservation(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Reservation reservation = session.load(Reservation.class, id);
        if (reservation != null) {
            session.delete(reservation);
        }
    }

    @Override
    public Reservation getReservationById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Reservation reservation = session.load(Reservation.class, id);
        if (reservation == null) {
            throw new NoSuchElementException("reservation with " + id + " doesn't exist");
        }

        return reservation;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Reservation> listReservations() {
        Session session = sessionFactory.getCurrentSession();
        return (List<Reservation>) session.createQuery("from com.softserve.app.entity.Reservation").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Reservation> getReservationsByHotelName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return (List<Reservation>) session.createQuery("from Reservation r where r.room.hotel.name=:name").setParameter("name",name).list();
    }


}

