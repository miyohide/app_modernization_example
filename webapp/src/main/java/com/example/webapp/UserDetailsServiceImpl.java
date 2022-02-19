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
      String sql = "SELECT id, password FROM users WHERE name = ?";
      Map<String, Object> map = jdbcTemplate.queryForMap(sql, username);
      String password = (String)map.get("password");
      Collection<GrantedAuthority> authorities = new ArrayList<>();
      String authority_sql = "SELECT authority FROM authorities WHERE user_id = ?";
      List<Map<String, Object>> authorities_result = jdbcTemplate.queryForList(authority_sql, (Long)map.get("id"));
      for (Map<String,Object> map2 : authorities_result) {
        authorities.add(new SimpleGrantedAuthority((String)map2.get("authority")));
      }
      return new UserDetailsImpl(username, password, authorities);
    } catch (Exception e) {
      throw new UsernameNotFoundException("User not found.", e);
    }
  }
}
