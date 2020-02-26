package com.epam.controller;

import com.epam.model.User;
import com.epam.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Controller
public class PDFController extends AbstractController {

  @Autowired UserService userService;

  @Override
  @RequestMapping(value = "/getAllUsersPDF", method = RequestMethod.GET)
  protected ModelAndView handleRequestInternal(
      HttpServletRequest request, HttpServletResponse response) throws Exception {
    Set<User> users = userService.getAll();
    Map<String, User> userData = new HashMap<String, User>();
    for (User user : users) {
      userData.put(String.valueOf(user.getUserId()), user);
    }
    return new ModelAndView("UserSummary", "userData", userData);
  }
}
