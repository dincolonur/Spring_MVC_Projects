package com.epam.impl;

import com.epam.dao.PhoneNumberDao;
import com.epam.model.PhoneNumber;
import com.epam.service.PhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class PhoneImpl implements PhoneService {

  @Autowired PhoneNumberDao phoneNumberDao;

  @Override
  public boolean savePhone(String phoneStr) {
    return phoneNumberDao.insertPhone(phoneStr);
  }

  @Override
  public Set<PhoneNumber> getAll() {
    return phoneNumberDao.getAll();
  }
}
