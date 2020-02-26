package com.epam.service;

import com.epam.model.User;

import java.util.Set;

public interface UserService {

  public boolean saveUser(String userStr);

  public Set<User> getAll();

  public User getUserByUsername(String username);
}
