package com.webproject.simplewebapplication.service;

import com.webproject.simplewebapplication.entity.User;

import java.util.List;
public interface UserService {
    List<User> findAll();

    Boolean save(User user);

    User getById(int employee_id);

    Integer update(User user);

    Integer delete(int employee_id);
}
