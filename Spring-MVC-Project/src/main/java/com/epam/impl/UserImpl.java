package com.epam.impl;

import com.epam.dao.UserDao;
import com.epam.model.User;
import com.epam.service.UserService;
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
}
