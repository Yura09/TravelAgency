package com.softserve.app.dao;

import com.softserve.app.entity.RoomType;

import java.util.List;

public interface RoomTypeDao {
    void addRoomType(RoomType roomType);

    void updateRoomType(RoomType roomType);

    void removeRoomType(Long id);

    RoomType getRoomTypeById(Long id);

    List<RoomType> listRoomTypes();
}
