package com.epam.controller;

import com.epam.model.User;
import com.epam.rest.UserPdfViewResolver;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class RestfullController {

  @Autowired private UserService userService;

  @Autowired private UserPdfViewResolver userPdfViewResolver;

  @RequestMapping(value = "/users", method = RequestMethod.GET, produces = { "application/json", "application/pdf" })
  public @ResponseBody List<User> getAllUsers() {
    Set<User> userSet = userService.getAll();
    List<User> userList = new ArrayList<>(userSet);
    Collections.sort(userList, new SortbyUserId());
    return userList;
  }

  @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = { "application/json", "application/pdf" })
  public @ResponseBody User getUser(@PathVariable("id") Long userId) {
    return userService.getUserById(userId);
  }


  @RequestMapping(value = "/users/add", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.CREATED)
  public @ResponseBody User createUser(@RequestBody User user) {
    userService.insertUser(user);
    return user;
  }

  @RequestMapping(value = "/users/edit", method = RequestMethod.POST)
  public @ResponseBody User editUser(@RequestBody User user) {
    userService.updateUser(user);
    return user;
  }

  @RequestMapping(value = "/users/delete/{id}", method = RequestMethod.PUT)
  public @ResponseBody User deleteUser(@PathVariable("id") Long userId) {
    User user = userService.getUserById(userId);
    userService.deleteUser(user);
    return user;
  }

  private class SortbyUserId implements Comparator<User> {
    @Override
    public int compare(User user1, User user2) {
      return user1.getUserId() - user2.getUserId();
    }
  }
}
