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

  @Override
  public User getUserByUsername(String username) {
    return userDao.getUserByUsername(username);
  }

  @Override
  public User insertUser(User user) {
    return userDao.insertUser(user);
  }

  @Override
  public boolean updateUser(User user) {
    return userDao.updateUser(user);
  }

  @Override
  public User getUserById(Long id) {
    return userDao.getUserById(id);
  }

  @Override
  public boolean deleteUser(User user) {
    return userDao.deleteUser(user);
  }
}
