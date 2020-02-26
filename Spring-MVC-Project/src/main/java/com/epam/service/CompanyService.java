package com.epam.service;

import com.epam.model.Company;

import java.util.Set;

public interface CompanyService {

  public boolean saveCompany(String companyStr);

  public Set<Company> getAll();
}
