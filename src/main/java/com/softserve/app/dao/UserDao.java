package com.softserve.app.dao;

import com.softserve.app.entity.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    void updateUser(User user);

    void removeUser(Long id);

    User getUserById(Long id);

    List<User> listUsers();
    User getUserByEmail(String email);
}
