package com.softserve.app.dao.impl;

import com.softserve.app.dao.HotelDao;
import com.softserve.app.entity.Hotel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class HotelDaoImpl implements HotelDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addHotel(Hotel hotel) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(hotel);
    }

    @Override
    public void updateHotel(Hotel hotel) {
        Session session = sessionFactory.getCurrentSession();
        session.update(hotel);
    }

    @Override
    public void removeHotel(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Hotel hotel = session.load(Hotel.class, id);
        if (hotel != null) {
            session.delete(hotel);
        }
    }
//select c from Account c join fetch c.instances where c.id = :id
    @Override
    public Hotel getHotelById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Hotel hotel= (Hotel) session.createQuery("from Hotel").list().get(0);
        if (hotel == null) {
            throw new NoSuchElementException("hotel with " + id + " doesn't exist");
        }
        return hotel;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Hotel> listHotels() {
        Session session = sessionFactory.getCurrentSession();
        return (List<Hotel>) session.createQuery("from Hotel").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Hotel> getHotelsByCityId(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (List<Hotel>) session.createQuery("from Hotel hotel where hotel.city.id=:id").setParameter("id",id).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Hotel> getHotelsByCityName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return (List<Hotel>) session.createQuery("select hotel from Hotel hotel join hotel.city city where city.name=:name").setParameter("name", name).list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Hotel> getHotelsByCountryName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return (List<Hotel>) session.createQuery("select hotel from Hotel hotel join hotel.city city join city.country country where country.name=:name").setParameter("name", name).list();
    }
}
