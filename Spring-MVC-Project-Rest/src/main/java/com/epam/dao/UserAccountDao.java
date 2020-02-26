package com.epam.dao;

import com.epam.model.Company;
import com.epam.model.PhoneNumber;
import com.epam.model.User;
import com.epam.model.UserAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class UserAccountDao {

  JdbcTemplate jdbcTemplate;

  @Autowired UserDao userDao;

  @Autowired PhoneNumberDao phoneNumberDao;

  @Autowired CompanyDao companyDao;

  @Autowired
  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public boolean updatePhoneCompany(
      String userName, String remainingBalance, Integer newPhoneCompanyId) {
    int control = -1;
    control =
        jdbcTemplate.update(
            "UPDATE user_accounts SET phoneCompanyId=?, amount=? WHERE username = ?",
            newPhoneCompanyId,
            remainingBalance,
            userName);
    if (control != -1) {
      return true;
    }
    return false;
  }

  public UserAccount getUserAccountByUsername(String username) {
    try {
      String sql = "SELECT * FROM user_accounts where username='" + username + "'";
      UserAccount resultUserAccount =
          jdbcTemplate.queryForObject(
              sql,
              new RowMapper<UserAccount>() {
                @Override
                public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
                  Integer accountId = rs.getInt("accountId");
                  String accountName = rs.getString("accountName");
                  String username = rs.getString("username");
                  Integer phoneCompanyId = rs.getInt("phoneCompanyId");
                  String amount = rs.getString("amount");

                  UserAccount userAccount = new UserAccount();
                  userAccount.setAccountId(accountId);
                  userAccount.setAccountName(accountName);
                  userAccount.setUserName(username);
                  userAccount.setPhoneCompanyId(phoneCompanyId);
                  userAccount.setAmount(Double.parseDouble(amount));

                  System.out.println(userAccount);
                  return userAccount;
                }
              });
      return resultUserAccount;
    } catch (Exception e) {
      return null;
    }
  }

  public Set<UserAccount> getAll() {
    Set<UserAccount> userAccountSet = new HashSet<>();
    try {
      String sql = "SELECT * FROM user_accounts";
      List<UserAccount> userAccountList =
          jdbcTemplate.query(
              sql,
              new RowMapper<UserAccount>() {
                @Override
                public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
                  Integer accountId = rs.getInt("accountId");
                  String accountName = rs.getString("accountName");
                  String username = rs.getString("username");
                  Integer phoneCompanyId = rs.getInt("phoneCompanyId");
                  String amount = rs.getString("amount");

                  UserAccount userAccount = new UserAccount();
                  userAccount.setAccountId(accountId);
                  userAccount.setAccountName(accountName);
                  userAccount.setUserName(username);
                  userAccount.setPhoneCompanyId(phoneCompanyId);
                  userAccount.setAmount(Double.parseDouble(amount));

                  System.out.println(userAccount);
                  return userAccount;
                }
              });
      userAccountSet = userAccountList.stream().collect(Collectors.toSet());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return userAccountSet;
  }

  public boolean insertUserAccount(UserAccount userAccount) {
    int control = -1;
    int id = userAccount.getAccountId();
    String accountName = userAccount.getAccountName();
    String username = userAccount.getUserName();
    int phoneCompanyId = userAccount.getPhoneCompanyId();
    String amount = Double.toString(userAccount.getAmount());
    control =
        jdbcTemplate.update(
            "INSERT INTO user_accounts VALUES(?,?,?,?,?)",
            id,
            accountName,
            username,
            phoneCompanyId,
            amount);

    if (control != -1) {
      return true;
    }
    return false;
  }

  public boolean removeUserAccount(Integer id) {
    int control = -1;
    control = jdbcTemplate.update("DELETE FROM user_accounts WHERE accountId = ?", id);
    if (control != -1) {
      return true;
    }
    return false;
  }

  public UserAccount getUserAccountById(Integer id) {
    try {
      String sql = "SELECT * FROM user_accounts where accountId=" + id + "";
      UserAccount resultUserAccount =
          jdbcTemplate.queryForObject(
              sql,
              new RowMapper<UserAccount>() {
                @Override
                public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
                  Integer accountId = rs.getInt("accountId");
                  String accountName = rs.getString("accountName");
                  String username = rs.getString("username");
                  Integer phoneCompanyId = rs.getInt("phoneCompanyId");
                  String amount = rs.getString("amount");

                  UserAccount userAccount = new UserAccount();
                  userAccount.setAccountId(accountId);
                  userAccount.setAccountName(accountName);
                  userAccount.setUserName(username);
                  userAccount.setPhoneCompanyId(phoneCompanyId);
                  userAccount.setAmount(Double.parseDouble(amount));

                  System.out.println(userAccount);
                  return userAccount;
                }
              });
      return resultUserAccount;
    } catch (Exception e) {
      return null;
    }
  }
}
