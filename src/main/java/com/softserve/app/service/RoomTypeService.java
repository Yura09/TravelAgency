package com.softserve.app.service;

import com.softserve.app.entity.RoomType;

import java.util.List;

public interface RoomTypeService {
    void addRoomType(RoomType roomType);

    void updateRoomType(RoomType roomType);

    void removeRoomType(Long id);

    RoomType getRoomTypeById(Long id);

    List<RoomType> listRoomTypes();
}
