package com.onur.model;

public class Company {

  private Integer companyId;
  private String companyName;
  private String companyAddress;
  private Double changeOperatorCost;

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

  public Double getChangeOperatorCost() {
    return changeOperatorCost;
  }

  public void setChangeOperatorCost(Double changeOperatorCost) {
    this.changeOperatorCost = changeOperatorCost;
  }

  @Override
  public String toString() {
    return companyName;
  }
}
