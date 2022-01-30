package com.example.webapp;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    private JdbcTemplate jdbcTemplate;

    public PostService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Post> allPosts() {
        String sql = "SELECT id, title, body FROM posts";
        RowMapper<Post> rowMapper = new BeanPropertyRowMapper<>(Post.class);
        List<Post> postList = jdbcTemplate.query(sql, rowMapper);
        return postList;
    }

    public Post postById(Long id) {
        String sql = "SELECT id, title, body FROM posts WHERE id = ?";
        RowMapper<Post> rowMapper = new BeanPropertyRowMapper<>(Post.class);
        Post p = jdbcTemplate.queryForObject(sql, rowMapper, id);

        return p;
    }
}
