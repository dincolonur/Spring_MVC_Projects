package com.onur.retrieve;

import com.onur.model.User;
import com.onur.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class UserRetrieve {

  @Autowired UserService userService;

  public Set<User> getAllUsers() {
    return userService.getAll();
  }
}
