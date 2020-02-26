package com.epam.model;

public class PhoneNumber implements Comparable<PhoneNumber> {

  private Integer phoneId;
  private String phoneNumber;
  private Company phoneCompany;

  public Integer getPhoneId() {
    return phoneId;
  }

  public void setPhoneId(Integer phoneId) {
    this.phoneId = phoneId;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Company getPhoneCompany() {
    return phoneCompany;
  }

  public void setPhoneCompany(Company phoneCompany) {
    this.phoneCompany = phoneCompany;
  }

  @Override
  public int compareTo(PhoneNumber other) {
    if (other == null) {
      return 1;
    }
    int result = phoneNumber.compareTo(other.getPhoneNumber());
    return result;
  }

  @Override
  public String toString() {
    return "phoneNumber='" + phoneNumber + '\'' + " - phoneCompany=" + phoneCompany;
  }
}
