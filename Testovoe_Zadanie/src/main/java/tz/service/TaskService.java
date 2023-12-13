package tz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import tz.dto.AuthUser;
import tz.dto.Task;
import tz.security.CurrentUser;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TaskService {
    private final JdbcTemplate jdbcTemplate;
    private final CurrentUser currentUser;

    public void create(Task task) {
        var sql = "insert into tasks (title,description,status,priority,taskAuthor,taskExecutor) values (?,?,?,?,?,?)";
        jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(), currentUser.getUser().getUsername(), task.getTaskExecutor());
    }

    public void update(Task task) {
        var sql = "update tasks set title = ? , description = ? , status = ? , priority = ? , taskExecutor = ? where id = ?";
        jdbcTemplate.update(sql, task.getTitle(), task.getDescription(), task.getStatus(), task.getPriority(), task.getTaskExecutor(), task.getId());
    }

    public void delete(Integer id) {
        var sql = "delete from tasks where id = ?";
        jdbcTemplate.update(sql, id);
    }

    public Task get(Integer id) {
        var sql = "select * from tasks where id = ?";
        return jdbcTemplate.queryForObject(sql, BeanPropertyRowMapper.newInstance(Task.class), id);
    }

    public List<Task> getAll(Integer limit) {
        var sql = "select * from tasks order by id limit 3 offset ?";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Task.class), limit);
    }

    public List<Task> getAllByUser(Integer limit) {
        var sql = "select * from tasks where taskAuthor = ? order by id limit 3 offset ?";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Task.class), currentUser.getUser().getUsername(),limit);
    }
}
