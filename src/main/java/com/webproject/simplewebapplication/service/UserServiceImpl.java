package com.webproject.simplewebapplication.service;

import com.webproject.simplewebapplication.dao.UserDao;
import com.webproject.simplewebapplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserDao userDao;

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public Boolean save(User user) {
        return userDao.save(user);
    }

    @Override
    public User getById(int employee_id) {
        return userDao.getById(employee_id);
    }

    @Override
    public Integer update(User user) {
        return userDao.update(user);
    }

    @Override
    public Integer delete(int employee_id) {
        return userDao.delete(employee_id);
    }
}
