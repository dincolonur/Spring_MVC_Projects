package com.onur.impl;

import com.onur.dao.CompanyDao;
import com.onur.model.Company;
import com.onur.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Set;

@Service
public class CompanyImpl implements CompanyService {

  @Autowired CompanyDao phoneCompanyDao;

  @Override
  public boolean saveCompany(String companyStr) {
    return phoneCompanyDao.insertCompany(companyStr);
  }

  @Override
  public Set<Company> getAll() {
    return phoneCompanyDao.getAll();
  }
}
