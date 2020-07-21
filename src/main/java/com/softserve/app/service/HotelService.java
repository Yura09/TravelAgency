package com.softserve.app.service;

import com.softserve.app.entity.Hotel;

import java.util.List;


public interface HotelService {
    void addHotel(Hotel hotel);

    void updateHotel(Hotel hotel);

    void removeHotel(Long id);

    Hotel getHotelById(Long id);

    List<Hotel> listHotels();

    List<Hotel> getHotelsByCityId(Long id);

    List<Hotel> getHotelsByCityName(String name);

    List<Hotel> getHotelsByCountryName(String name);
}
