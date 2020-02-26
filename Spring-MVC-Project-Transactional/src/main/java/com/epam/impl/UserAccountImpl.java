package com.epam.impl;

import com.epam.dao.CompanyDao;
import com.epam.dao.PhoneNumberDao;
import com.epam.dao.UserAccountDao;
import com.epam.dao.UserDao;
import com.epam.model.Company;
import com.epam.model.PhoneNumber;
import com.epam.model.User;
import com.epam.model.UserAccount;
import com.epam.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.NavigableSet;
import java.util.Set;

@Service
@Transactional
public class UserAccountImpl implements UserAccountService {

  @Autowired UserAccountDao userAccountDao;

  @Autowired CompanyDao companyDao;

  @Autowired UserDao userDao;

  @Autowired PhoneNumberDao phoneNumberDao;

  @Override
  @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
  public boolean updatePhoneCompany(String userName, Integer newPhoneCompanyId) {
    UserAccount userAccount = getUserAccountByUsername(userName);
    Integer oldPhoneCompany = userAccount.getPhoneCompanyId();

    if (oldPhoneCompany == newPhoneCompanyId) {
      return false;
    }

    Company phoneCompany = companyDao.getPhoneCompanyById(newPhoneCompanyId);
    Double amountOnAccount = userAccount.getAmount();
    Double changeOperatorCost = phoneCompany.getChangeOperatorCost();

    if (amountOnAccount >= changeOperatorCost) {
      String remainingBalance = Double.toString(amountOnAccount - changeOperatorCost);
      User user = userDao.getUserByUsername(userName);
      NavigableSet<PhoneNumber> phonenumbers = user.getPhoneNumbers();
      PhoneNumber phoneNumber = phonenumbers.first();
      if (phoneNumberDao.updatePhoneNumberCompanyId(
          phoneNumber.getPhoneNumber(), newPhoneCompanyId)) {
        return userAccountDao.updatePhoneCompany(userName, remainingBalance, newPhoneCompanyId);
      }
    }
    return false;
  }

  @Override
  public Set<UserAccount> getAll() {
    return userAccountDao.getAll();
  }

  @Override
  public UserAccount getUserAccountByUsername(String username) {
    return userAccountDao.getUserAccountByUsername(username);
  }

  @Override
  public boolean insertUserAccount(UserAccount userAccount) {
    return userAccountDao.insertUserAccount(userAccount);
  }

  @Override
  public boolean removeUserAccount(Integer id) {
    return userAccountDao.removeUserAccount(id);
  }

  @Override
  public UserAccount getUserAccountById(Integer id) {
    return userAccountDao.getUserAccountById(id);
  }
}
