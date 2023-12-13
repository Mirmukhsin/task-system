package tz.service;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import tz.dto.Task;
import tz.security.CurrentUser;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ExecutorService {
    private final JdbcTemplate jdbcTemplate;
    private final CurrentUser user;

    public List<Task> getExecutorTasks(Integer limit){
        var sql = "select * from tasks where taskExecutor = ? order by id limit 3 offset ?";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Task.class),user.getUser().getUsername(),limit);
    }
    public Task getById(Integer id){
        var sql = "select * from tasks where id = ?";
        return jdbcTemplate.queryForObject(sql,BeanPropertyRowMapper.newInstance(Task.class),id);
    }
    public void changeStatus(Integer id,String status){
        var sql = "update tasks set status = ? where id = ?";
        jdbcTemplate.update(sql,status,id);
    }
}
