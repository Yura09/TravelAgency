package com.softserve.app.service.impl;

import com.softserve.app.dao.CountryDao;
import com.softserve.app.entity.Country;
import com.softserve.app.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {
    private CountryDao countryDao;
    @Autowired
    public void setCountryDao(CountryDao countryDao) {
        this.countryDao = countryDao;
    }

    @Override
    @Transactional
    public void addCountry(Country country) {
        countryDao.addCountry(country);
    }

    @Override
    @Transactional
    public void updateCountry(Country country) {
        countryDao.updateCountry(country);
    }

    @Override
    @Transactional
    public void removeCountry(Long id) {
        countryDao.removeCountry(id);
    }

    @Override
    @Transactional
    public Country getCountryById(Long id) {
        return countryDao.getCountryById(id);
    }

    @Override
    @Transactional
    public List<Country> listCountries() {
        return countryDao.listCountries();
    }
}
