package com.epam.model;

public class Company {

  private Integer companyId;
  private String companyName;
  private String companyAddress;

  public Integer getCompanyId() {
    return companyId;
  }

  public void setCompanyId(Integer companyId) {
    this.companyId = companyId;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String comapanyName) {
    this.companyName = comapanyName;
  }

  public String getCompanyAddress() {
    return companyAddress;
  }

  public void setCompanyAddress(String companyAddress) {
    this.companyAddress = companyAddress;
  }

  @Override
  public String toString() {
    return companyName;
  }
}
