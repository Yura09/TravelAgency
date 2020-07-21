package com.softserve.app.dao.impl;

import com.softserve.app.dao.CountryDao;
import com.softserve.app.entity.Country;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class CountryDaoImpl implements CountryDao {
    private static final Logger logger = LoggerFactory.getLogger(CountryDaoImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addCountry(Country country) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(country);
        logger.info("Country successfully saved: " + country);
    }

    @Override
    public void updateCountry(Country country) {
        Session session = sessionFactory.getCurrentSession();
        session.update(country);
        logger.info("Country successfully update: " + country);
    }

    @Override
    public void removeCountry(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Country country = session.load(Country.class, id);
        if (country != null) {
            session.delete(country);
            logger.info("Country successfully removed: " + country);
        }
    }

    @Override
    public Country getCountryById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        Country country = session.load(Country.class, id);
        if (country == null) {
            throw new NoSuchElementException("country with " + id + " doesn't exist");
        }
        logger.info("Country successfully loaded " + country);
        return country;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Country> listCountries() {
        Session session = sessionFactory.getCurrentSession();
        return (List<Country>) session.createQuery("from com.softserve.app.entity.Country").list();
    }
}
