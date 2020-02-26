package com.onur.service;

import com.onur.model.Company;

import java.util.Set;

public interface CompanyService {

  public boolean saveCompany(String companyStr);

  public Set<Company> getAll();
}
