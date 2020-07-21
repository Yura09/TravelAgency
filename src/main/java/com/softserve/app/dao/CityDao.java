package com.softserve.app.dao;

import com.softserve.app.entity.City;

import java.util.List;

public interface CityDao {
    void addCity(City city);

    void updateCity(City city);

    void removeCity(Long id);

    City getCityById(Long id);

    List<City> listCities();

    List<City> getCitiesByCountryId(Long id);

    List<City> getCitiesByCountryName(String name);
    //List<City> getCitiesByCountry(Country country);
}
