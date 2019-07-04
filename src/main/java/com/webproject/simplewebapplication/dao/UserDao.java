package com.webproject.simplewebapplication.dao;

import com.webproject.simplewebapplication.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;
public interface UserDao {

    Boolean save(User user);

    User getById(int employee_id);

    List<User> findAll();

    Integer update(User user);

    Integer delete(int employee_id);
}
