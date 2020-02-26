package com.onur.service;

import com.onur.model.PhoneNumber;

import java.util.Set;

public interface PhoneService {

  public boolean savePhone(String phoneStr);

  public Set<PhoneNumber> getAll();
}
