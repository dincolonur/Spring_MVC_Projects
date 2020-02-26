package com.onur.service;

import com.onur.model.User;

import java.util.Set;

public interface UserService {

  public boolean saveUser(String userStr);

  public Set<User> getAll();

  public User getUserByUsername(String username);

  public User insertUser(User user);

  public boolean updateUser(User user);

  public User getUserById(Long id);

  public boolean deleteUser(User user);
}
