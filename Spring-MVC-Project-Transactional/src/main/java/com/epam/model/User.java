package com.epam.model;

import java.util.NavigableSet;
import java.util.TreeSet;

public class User {

  private Integer userId;
  private String username;
  private String firstName;
  private String lastName;
  private String userAddress;
  private NavigableSet<PhoneNumber> phoneNumbers = new TreeSet<>();
  private String password;
  private String roles;

  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getUserAddress() {
    return userAddress;
  }

  public void setUserAddress(String userAddress) {
    this.userAddress = userAddress;
  }

  public NavigableSet<PhoneNumber> getPhoneNumbers() {
    return phoneNumbers;
  }

  public void setPhoneNumbers(NavigableSet<PhoneNumber> phoneNumbers) {
    this.phoneNumbers = phoneNumbers;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getRoles() {
    return roles;
  }

  public void setRoles(String roles) {
    this.roles = roles;
  }

  @Override
  public String toString() {
    return "User{"
        + "userId="
        + userId
        + ", firstName='"
        + firstName
        + '\''
        + ", lastName='"
        + lastName
        + '\''
        + ", userAddress='"
        + userAddress
        + '\''
        + ", phoneNumbers="
        + phoneNumbers
        + '}';
  }
}
