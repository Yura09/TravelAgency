package com.softserve.app.service.impl;

import com.softserve.app.dao.RoomTypeDao;
import com.softserve.app.entity.RoomType;
import com.softserve.app.service.RoomTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    private RoomTypeDao roomTypeDao;

    @Autowired
    public void setRoomTypeDao(RoomTypeDao roomTypeDao) {
        this.roomTypeDao = roomTypeDao;
    }

    @Override
    @Transactional
    public void addRoomType(RoomType roomType) {
        roomTypeDao.addRoomType(roomType);
    }

    @Override
    @Transactional
    public void updateRoomType(RoomType roomType) {
        roomTypeDao.updateRoomType(roomType);
    }

    @Override
    @Transactional
    public void removeRoomType(Long id) {
        roomTypeDao.removeRoomType(id);
    }

    @Override
    @Transactional
    public RoomType getRoomTypeById(Long id) {
        return roomTypeDao.getRoomTypeById(id);
    }

    @Override
    @Transactional
    public List<RoomType> listRoomTypes() {
        return roomTypeDao.listRoomTypes();
    }
}
