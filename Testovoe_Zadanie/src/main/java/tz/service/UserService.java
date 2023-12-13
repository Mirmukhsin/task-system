package tz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import tz.dto.AuthUser;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserService {
    private final JdbcTemplate jdbcTemplate;
    private final PasswordEncoder passwordEncoder;

    public void create(AuthUser user) {
        var sql = "insert into authUser (username,email,password) values (?,?,?)";
        jdbcTemplate.update(sql, user.getUsername(), user.getEmail(), passwordEncoder.encode(user.getPassword()));
    }

    public void update(AuthUser user) {
        var sql = "update authUser set username = ? , role = ?";
        jdbcTemplate.update(sql, user.getUsername(), user.getRole());
    }

    public void delete(Integer id) {
        var sql = "delete from authUser where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public AuthUser get(Integer id) {
        var sql = "select * from authUser where id = ?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(AuthUser.class), id);
    }

    public Optional<AuthUser> findUserByEmail(String email) {
        var sql = "select * from authUser where email = ?";
        try {
            AuthUser user = jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(AuthUser.class), email);
            return Optional.of(user);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public List<AuthUser> getAll() {
        var sql = "select * from authUser";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(AuthUser.class));
    }
    public List<AuthUser> getAllExecutor() {
        var sql = "select * from authUser where role = 'EXECUTOR'";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(AuthUser.class));
    }
}
