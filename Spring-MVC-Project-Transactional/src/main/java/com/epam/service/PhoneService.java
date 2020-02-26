package com.epam.service;

import com.epam.model.PhoneNumber;

import java.util.Set;

public interface PhoneService {

  public boolean savePhone(String phoneStr);

  public Set<PhoneNumber> getAll();
}
