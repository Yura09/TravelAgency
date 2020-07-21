package com.softserve.app.service;

import com.softserve.app.entity.Country;

import java.util.List;

public interface CountryService {
    void addCountry(Country country);

    void updateCountry(Country country);

    void removeCountry(Long id);

    Country getCountryById(Long id);

    List<Country> listCountries();
}
