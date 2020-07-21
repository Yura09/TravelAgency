package com.softserve.app.dao;

import com.softserve.app.entity.Country;

import java.util.List;

public interface CountryDao {
    void addCountry(Country country);

    void updateCountry(Country country);

    void removeCountry(Long id);

    Country getCountryById(Long id);

    List<Country> listCountries();
}

