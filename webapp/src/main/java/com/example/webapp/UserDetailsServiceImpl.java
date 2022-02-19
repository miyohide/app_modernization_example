package com.example.webapp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  JdbcTemplate jdbcTemplate;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      String find_user_sql = "SELECT id, password FROM users WHERE name = ?";
      Map<String, Object> find_user_result = jdbcTemplate.queryForMap(find_user_sql, username);
      String password = (String)find_user_result.get("password");
      Collection<GrantedAuthority> authorities = new ArrayList<>();
      String get_authority_sql = "SELECT authority FROM authorities WHERE user_id = ?";
      List<Map<String, Object>> authorities_result = jdbcTemplate.queryForList(get_authority_sql, (Long)find_user_result.get("id"));
      for (Map<String,Object> authority_result : authorities_result) {
        authorities.add(new SimpleGrantedAuthority((String)authority_result.get("authority")));
      }
      return new UserDetailsImpl(username, password, authorities);
    } catch (Exception e) {
      throw new UsernameNotFoundException("User not found.", e);
    }
  }
}
