package com.epam.dao;

import com.epam.model.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
public class CompanyDao {

  JdbcTemplate jdbcTemplate;

  @Autowired
  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public Company getPhoneCompanyById(Integer id) {
    String sql = "SELECT * FROM phone_companies WHERE companyId=" + id;
    Company resultPhoneCompany =
        jdbcTemplate.queryForObject(
            sql,
            new RowMapper<Company>() {

              @Override
              public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer companyId = rs.getInt("companyId");
                String companyName = rs.getString("companyName");
                String companyAddress = rs.getString("companyAddress");
                String changeOperatorCost = rs.getString("changeOperatorCost");

                Company phoneCompany = new Company();
                phoneCompany.setCompanyId(companyId);
                phoneCompany.setCompanyName(companyName);
                phoneCompany.setCompanyAddress(companyAddress);
                phoneCompany.setChangeOperatorCost(Double.parseDouble(changeOperatorCost));
                return phoneCompany;
              }
            });
    return resultPhoneCompany;
  }

  public boolean insertCompany(String companyStr) {
    int control = -1;
    String[] companyFields = companyStr.split(",");
    Integer companyId = Integer.parseInt(companyFields[0]);
    String companyName = companyFields[1];
    String companyAddress = companyFields[2];
    String changeOperatorCost = companyFields[3];

    control =
        jdbcTemplate.update(
            "INSERT INTO phone_companies VALUES(?,?,?,?)",
            companyId,
            companyName,
            companyAddress,
            changeOperatorCost);

    if (control != -1) {
      return true;
    }
    return false;
  }

  public Set<Company> getAll() {
    String sql = "SELECT * FROM phone_companies";
    List<Company> companyList =
        jdbcTemplate.query(
            sql,
            new RowMapper<Company>() {
              @Override
              public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
                Integer companyId = rs.getInt("companyId");
                String companyName = rs.getString("companyName");
                String companyAddress = rs.getString("companyAddress");

                Company phoneCompany = new Company();
                phoneCompany.setCompanyId(companyId);
                phoneCompany.setCompanyName(companyName);
                phoneCompany.setCompanyAddress(companyAddress);
                return phoneCompany;
              }
            });
    Set<Company> companySet = companyList.stream().collect(Collectors.toSet());
    return companySet;
  }
}
