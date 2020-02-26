package com.epam.security;

import com.epam.dao.UserDao;
import com.epam.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

  @Autowired private UserDao userDao;

  @Autowired private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {

    User user = userDao.getUserByUsername(username);
    List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());

    return buildUserForAuthentication(user, authorities);
  }

  private org.springframework.security.core.userdetails.User buildUserForAuthentication(
      User user, List<GrantedAuthority> authorities) {
    return new org.springframework.security.core.userdetails.User(
        user.getUsername(), passwordEncoder.encode(user.getPassword()), authorities);
  }

  private List<GrantedAuthority> buildUserAuthority(String userRolesStr) {

    String[] userRoles = userRolesStr.split(";");
    Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

    for (String userRole : userRoles) {
      setAuths.add(new SimpleGrantedAuthority(userRole));
    }

    List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

    return Result;
  }
}
