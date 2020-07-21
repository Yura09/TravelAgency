package com.softserve.app.service;

import com.softserve.app.entity.City;

import java.util.List;

public interface CityService {
    void addCity(City city);

    void updateCity(City city);

    void removeCity(Long id);

    City getCityById(Long id);

    List<City> listCities();

    List<City> getCitiesByCountryId(Long id);

    List<City> getCitiesByCountryName(String name);
}
