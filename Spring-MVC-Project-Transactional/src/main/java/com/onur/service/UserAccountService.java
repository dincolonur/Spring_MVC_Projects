package com.onur.service;

import com.onur.model.UserAccount;

import java.util.Set;

public interface UserAccountService {

  public boolean updatePhoneCompany(String userName, Integer newPhoneCompanyId);

  public Set<UserAccount> getAll();

  public UserAccount getUserAccountByUsername(String username);

  public boolean insertUserAccount(UserAccount userAccount);

  public boolean removeUserAccount(Integer id);

  public UserAccount getUserAccountById(Integer id);
}
