package com.onur.dao;

import com.onur.model.Company;
import com.onur.model.PhoneNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class PhoneNumberDao {

  JdbcTemplate jdbcTemplate;

  @Autowired CompanyDao phoneCompanyDao;

  @Autowired
  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public PhoneNumber getPhoneByNumber(String number) {
    String sql = "SELECT * FROM phone_numbers WHERE phoneNumber='" + number + "'";
    PhoneNumber resultPhoneNumber =
        jdbcTemplate.queryForObject(
            sql,
            new RowMapper<PhoneNumber>() {

              @Override
              public PhoneNumber mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer phoneId = rs.getInt("phoneId");
                String number = rs.getString("phoneNumber");
                Integer phoneCompanyId = rs.getInt("phoneCompanyId");

                Company phoneCompany = null;
                if (phoneCompanyDao.getPhoneCompanyById(phoneCompanyId) != null) {
                  phoneCompany = phoneCompanyDao.getPhoneCompanyById(phoneCompanyId);
                }

                PhoneNumber phoneNumber = new PhoneNumber();
                phoneNumber.setPhoneId(phoneId);
                phoneNumber.setPhoneNumber(number);
                phoneNumber.setPhoneCompany(phoneCompany);
                return phoneNumber;
              }
            });
    return resultPhoneNumber;
  }

  public boolean insertPhone(String phoneStr) {
    int control = -1;
    String[] phoneFields = phoneStr.split(",");
    Integer phoneId = Integer.parseInt(phoneFields[0]);
    String phoneNumber = phoneFields[1];
    Integer phoneCompanyId = Integer.parseInt(phoneFields[2]);

    control =
        jdbcTemplate.update(
            "INSERT INTO phone_numbers VALUES(?,?,?)", phoneId, phoneNumber, phoneCompanyId);

    if (control != -1) {
      return true;
    }
    return false;
  }

  public Set<PhoneNumber> getAll() {
    String sql = "SELECT * FROM phone_numbers";
    List<PhoneNumber> phoneList =
        jdbcTemplate.query(
            sql,
            new RowMapper<PhoneNumber>() {
              @Override
              public PhoneNumber mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer phoneId = rs.getInt("phoneId");
                String number = rs.getString("phoneNumber");
                Integer phoneCompanyId = rs.getInt("phoneCompanyId");

                Company phoneCompany = null;
                if (phoneCompanyDao.getPhoneCompanyById(phoneCompanyId) != null) {
                  phoneCompany = phoneCompanyDao.getPhoneCompanyById(phoneCompanyId);
                }

                PhoneNumber phoneNumber = new PhoneNumber();
                phoneNumber.setPhoneId(phoneId);
                phoneNumber.setPhoneNumber(number);
                phoneNumber.setPhoneCompany(phoneCompany);
                return phoneNumber;
              }
            });
    Set<PhoneNumber> phoneNumberSet = phoneList.stream().collect(Collectors.toSet());
    return phoneNumberSet;
  }
}
