package com.onur.impl;

import com.onur.dao.UserDao;
import com.onur.model.User;
import com.onur.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class UserImpl implements UserService {

  @Autowired UserDao userDao;

  @Override
  public boolean saveUser(String userStr) {
    return userDao.insertUser(userStr);
  }

  @Override
  public Set<User> getAll() {
    return userDao.getAll();
  }

  @Override
  public User getUserByUsername(String username) {
    return userDao.getUserByUsername(username);
  }
}
