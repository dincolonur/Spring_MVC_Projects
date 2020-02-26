package com.onur.controller;

import com.onur.model.UserAccount;
import com.onur.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TrxController {

  @Autowired UserAccountService userAccountService;

  @RequestMapping(value = "/userAccounts", method = RequestMethod.GET)
  public String listPersons(Model model) {
    model.addAttribute("userAccount", new UserAccount());
    model.addAttribute("listUserAccounts", this.userAccountService.getAll());
    return "account";
  }

  // update userAccount
  @RequestMapping(value = "/userAccount/add", method = RequestMethod.POST)
  public String changeMobileOperator(@ModelAttribute("userAccount") UserAccount userAccount) {

    // existing userAccount, call update
    this.userAccountService.updatePhoneCompany(
        userAccount.getUserName(), userAccount.getPhoneCompanyId());
    return "redirect:/userAccounts";
  }

  @RequestMapping("/remove/{id}")
  public String removeUserAccount(@PathVariable("id") int id) {

    this.userAccountService.removeUserAccount(id);
    return "redirect:/userAccounts";
  }

  @RequestMapping("/edit/{id}")
  public String editUserAccount(@PathVariable("id") int id, Model model) {
    model.addAttribute("userAccount", this.userAccountService.getUserAccountById(id));
    model.addAttribute("listUserAccounts", this.userAccountService.getAll());
    return "account";
  }
}
