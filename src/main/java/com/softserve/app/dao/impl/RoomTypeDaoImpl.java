package com.softserve.app.dao.impl;

import com.softserve.app.dao.RoomTypeDao;
import com.softserve.app.entity.RoomType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class RoomTypeDaoImpl implements RoomTypeDao {
    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void addRoomType(RoomType roomType) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(roomType);
    }

    @Override
    public void updateRoomType(RoomType roomType) {
        Session session = sessionFactory.getCurrentSession();
        session.update(roomType);
    }

    @Override
    public void removeRoomType(Long id) {
      Session session = sessionFactory.getCurrentSession();
        RoomType roomType = session.load(RoomType.class, id);
        if (roomType != null) {
            session.delete(roomType);
        }
    }

    @Override
    public RoomType getRoomTypeById(Long id) {
        Session session = sessionFactory.getCurrentSession();
        RoomType roomType = session.load(RoomType.class, id);
        if (roomType == null) {
            throw new NoSuchElementException("country with " + id + " doesn't exist");
        }
        return roomType;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<RoomType> listRoomTypes() {
        Session session = sessionFactory.getCurrentSession();
        return (List<RoomType>) session.createQuery("from RoomType").list();
    }
}
