package com.epam.dao;

import com.epam.model.PhoneNumber;
import com.epam.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Repository("userDao")
public class UserDao {

  JdbcTemplate jdbcTemplate;

  @Autowired PhoneNumberDao phoneNumberDao;

  @Autowired
  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public boolean insertUser(String userStr) {
    int control = -1;
    String[] userFields = userStr.split(",");
    Integer userId = Integer.parseInt(userFields[0]);
    String firstName = userFields[1];
    String username = userFields[2];
    String lastName = userFields[3];
    String phoneNumbers = userFields[4];
    String userAddress = userFields[5];
    String password = userFields[6];
    String roles = userFields[7];

    control =
        jdbcTemplate.update(
            "INSERT INTO users VALUES(?,?,?,?,?,?,?,?)",
            userId,
            username,
            firstName,
            lastName,
            phoneNumbers,
            userAddress,
            password,
            roles);

    if (control != -1) {
      return true;
    }
    return false;
  }

  public Set<User> getAll() {
    Set<User> userSet = new HashSet<>();
    try {
      String sql = "SELECT * FROM users";
      List<User> userList =
          jdbcTemplate.query(
              sql,
              new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                  Integer userId = rs.getInt("userId");
                  String firstName = rs.getString("firstName");
                  String lastName = rs.getString("lastName");
                  String address = rs.getString("address");
                  String phoneNumbers = rs.getString("phoneNumbers");

                  NavigableSet<PhoneNumber> phoneNumberSet = new TreeSet<>();

                  if (!phoneNumbers.equalsIgnoreCase("")) {
                    String[] arr = phoneNumbers.split(";");
                    if (arr.length > 0) {
                      for (String phoneNumberStr : arr) {
                        if (phoneNumberDao.getPhoneByNumber(phoneNumberStr) != null) {
                          PhoneNumber phoneNumber = phoneNumberDao.getPhoneByNumber(phoneNumberStr);
                          phoneNumberSet.add(phoneNumber);
                        }
                      }
                    }
                  }
                  User user = new User();
                  user.setUserId(userId);
                  user.setFirstName(firstName);
                  user.setLastName(lastName);
                  user.setUserAddress(address);
                  user.setPhoneNumbers(phoneNumberSet);
                  System.out.println(user);
                  return user;
                }
              });
      userSet = userList.stream().collect(Collectors.toSet());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return userSet;
  }

  public User getUserByUsername(String username) {
    try {
      String sql = "SELECT * FROM users where username='" + username + "'";
      User resultUser =
          jdbcTemplate.queryForObject(
              sql,
              new RowMapper<User>() {
                @Override
                public User mapRow(ResultSet rs, int rowNum) throws SQLException {
                  Integer userId = rs.getInt("userId");
                  String username = rs.getString("username");
                  String firstName = rs.getString("firstName");
                  String lastName = rs.getString("lastName");
                  String address = rs.getString("address");
                  String phoneNumbers = rs.getString("phoneNumbers");
                  String password = rs.getString("password");
                  String roles = rs.getString("roles");

                  NavigableSet<PhoneNumber> phoneNumberSet = new TreeSet<>();

                  if (!phoneNumbers.equalsIgnoreCase("")) {
                    String[] arr = phoneNumbers.split(";");
                    if (arr.length > 0) {
                      for (String phoneNumberStr : arr) {
                        if (phoneNumberDao.getPhoneByNumber(phoneNumberStr) != null) {
                          PhoneNumber phoneNumber = phoneNumberDao.getPhoneByNumber(phoneNumberStr);
                          phoneNumberSet.add(phoneNumber);
                        }
                      }
                    }
                  }
                  User user = new User();
                  user.setUserId(userId);
                  user.setUsername(username);
                  user.setFirstName(firstName);
                  user.setLastName(lastName);
                  user.setUserAddress(address);
                  user.setPhoneNumbers(phoneNumberSet);
                  user.setPassword(password);
                  user.setRoles(roles);
                  System.out.println(user);
                  return user;
                }
              });
      return resultUser;
    } catch (Exception e) {
      return null;
    }
  }
}
