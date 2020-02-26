package com.epam.retrieve;

import com.epam.model.User;
import com.epam.service.UserService;
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
