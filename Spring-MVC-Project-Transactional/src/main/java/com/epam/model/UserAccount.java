package com.epam.model;

public class UserAccount {

  private Integer accountId;
  private String accountName;
  private String userName;
  private Integer phoneCompanyId;
  private Double amount;

  public String getAccountName() {
    return accountName;
  }

  public void setAccountName(String accountName) {
    this.accountName = accountName;
  }

  public Integer getAccountId() {
    return accountId;
  }

  public void setAccountId(Integer accountId) {
    this.accountId = accountId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public Integer getPhoneCompanyId() {
    return phoneCompanyId;
  }

  public void setPhoneCompanyId(Integer phoneCompanyId) {
    this.phoneCompanyId = phoneCompanyId;
  }

  public Double getAmount() {
    return amount;
  }

  public void setAmount(Double amount) {
    this.amount = amount;
  }

  @Override
  public String toString() {
    return "UserAccount{"
        + "accountId="
        + accountId
        + ", accountName='"
        + accountName
        + '\''
        + ", userName='"
        + userName
        + '\''
        + ", phoneCompanyId="
        + phoneCompanyId
        + ", amount="
        + amount
        + '}';
  }
}
