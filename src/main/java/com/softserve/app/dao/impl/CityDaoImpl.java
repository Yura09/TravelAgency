package com.softserve.app.dao.impl;

import com.softserve.app.dao.CityDao;
import com.softserve.app.entity.City;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class CityDaoImpl implements CityDao {
    private static final Logger logger = LoggerFactory.getLogger(CityDaoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addCity(City city) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(city);
        logger.info("City successfully saved: " + city);
    }

    @Override
    public void updateCity(City city) {
        Session session = sessionFactory.getCurrentSession();
        session.update(city);
        logger.info("City successfully update: " + city);
    }

    @Override
    public void removeCity(Long id) {
        Session session = sessionFactory.getCurrentSession();
        City city = session.load(City.class, id);
        if (city != null) {
            session.delete(city);
            logger.info("City successfully removed: " + city);
        }
    }

    @Override
    public City getCityById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        City city = session.load(City.class, id);
        if (city == null) {
            throw new NoSuchElementException("city with " + id + " doesn't exist");
        }
        logger.info("City successfully loaded " + city);
        return city;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<City> listCities() {
        Session session = sessionFactory.getCurrentSession();
        return (List<City>) session.createQuery("from com.softserve.app.entity.City").list();
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<City> getCitiesByCountryId(Long id) {
        Session session = sessionFactory.getCurrentSession();
        return (List<City>) session.createQuery("from com.softserve.app.entity.City where country_id=:id").setParameter("id", id).list();

    }

    @Override
    @SuppressWarnings("unchecked")
    public List<City> getCitiesByCountryName(String name) {
        Session session = sessionFactory.getCurrentSession();
        return (List<City>) session.createQuery("select city from City city join city.country country where country.name=:name").setParameter("name", name).list();
    }

   /* @Override
    public List<City> getCitiesByCountry(Country country) {

    }*/
}
