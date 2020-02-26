package com.onur.service;

import com.onur.model.User;

import java.util.Set;

public interface UserService {

  public boolean saveUser(String userStr);

  public Set<User> getAll();
}
