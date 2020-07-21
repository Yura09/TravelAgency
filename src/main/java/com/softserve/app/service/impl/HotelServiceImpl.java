package com.softserve.app.service.impl;

import com.softserve.app.dao.HotelDao;
import com.softserve.app.entity.Hotel;
import com.softserve.app.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotelServiceImpl implements HotelService {
    private HotelDao hotelDao;

    @Autowired
    @Qualifier("hotelDaoImpl")
    public void setHotelDao(HotelDao hotelDao) {
        this.hotelDao = hotelDao;
    }

    @Override
    @Transactional
    public void addHotel(Hotel hotel) {
        hotelDao.addHotel(hotel);
    }

    @Override
    @Transactional
    public void updateHotel(Hotel hotel) {
        hotelDao.updateHotel(hotel);
    }

    @Override
    @Transactional
    public void removeHotel(Long id) {
        hotelDao.removeHotel(id);
    }

    @Override
    @Transactional
    public Hotel getHotelById(Long id) {
        return hotelDao.getHotelById(id);
    }

    @Override
    @Transactional
    public List<Hotel> listHotels() {
        return hotelDao.listHotels();
    }

    @Override
    @Transactional
    public List<Hotel> getHotelsByCityId(Long id) {
        return hotelDao.getHotelsByCityId(id);
    }

    @Override
    @Transactional
    public List<Hotel> getHotelsByCityName(String name) {
        return hotelDao.getHotelsByCityName(name);
    }

    @Override
    @Transactional
    public List<Hotel> getHotelsByCountryName(String name) {
        return hotelDao.getHotelsByCountryName(name);
    }
}
