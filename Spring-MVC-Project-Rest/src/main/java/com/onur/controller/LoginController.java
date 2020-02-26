package com.onur.controller;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import java.util.Collection;

@Controller
public class LoginController {

  @RequestMapping(value = "/login", method = RequestMethod.GET)
  public String login(ModelMap model) {

    return "login";
  }

  @RequestMapping(value = "/accessdenied", method = RequestMethod.GET)
  public String loginerror(ModelMap model) {
    model.addAttribute("error", "true");
    return "denied";
  }

  @RequestMapping(value = "/logout", method = RequestMethod.GET)
  public String logout(ModelMap model) {
    return "logout";
  }

  @RequestMapping(value = "/list", method = RequestMethod.GET)
  public String listMenu(ModelMap map) {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Boolean superUser = false;

    if (principal instanceof UserDetails) {
      Collection<GrantedAuthority> roles =
          (Collection<GrantedAuthority>) ((UserDetails) principal).getAuthorities();
      for (GrantedAuthority role : roles) {
        if (role.getAuthority().equalsIgnoreCase("BOOKING_MANAGER")) {
          superUser = true;
        }
      }
    }
    if (superUser) {
      return "index";
    } else {
      return "indexUser";
    }
  }
}
