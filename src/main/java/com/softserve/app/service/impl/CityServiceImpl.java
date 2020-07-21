package com.softserve.app.service.impl;

import com.softserve.app.dao.CityDao;
import com.softserve.app.entity.City;
import com.softserve.app.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class CityServiceImpl implements CityService {
    private CityDao cityDao;

    @Autowired
    public void setCityDao(CityDao cityDao) {
        this.cityDao = cityDao;
    }

    @Override
    @Transactional
    public void addCity(City city) {
        cityDao.addCity(city);
    }

    @Override
    @Transactional
    public void updateCity(City city) {
        cityDao.updateCity(city);
    }

    @Override
    @Transactional
    public void removeCity(Long id) {
        cityDao.removeCity(id);
    }

    @Override
    @Transactional
    public City getCityById(Long id) {
        return cityDao.getCityById(id);
    }

    @Override
    @Transactional
    public List<City> listCities() {
        return cityDao.listCities();
    }

    @Override
    @Transactional
    public List<City> getCitiesByCountryId(Long id) {
        return cityDao.getCitiesByCountryId(id);
    }

    @Override
    @Transactional
    public List<City> getCitiesByCountryName(String name) {
        return cityDao.getCitiesByCountryName(name);
    }
}
