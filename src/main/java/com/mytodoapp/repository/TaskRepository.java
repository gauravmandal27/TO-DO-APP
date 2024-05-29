package com.mytodoapp.repository;

import com.mytodoapp.exception.RecordNotFoundException;
import com.mytodoapp.model.Task;
import com.mytodoapp.util.TaskRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TaskRepository implements ITaskRepository{
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public TaskRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Task saveTask(Task task) {
        int rowCount = jdbcTemplate.update("insert into tasks(title,task_description,completed) values(?,?,?)",
                task.getTitle(), task.getDescription(), task.isCompleted());
        if (rowCount > 0) {
            try {
                task = jdbcTemplate.queryForObject("select * from tasks order by id desc limit 1", new TaskRowMapper());
                return task;
            }
            catch (DataAccessException ex){
                throw new RecordNotFoundException("Record Not Found ");
            }
        }
        return null;
    }

    public Task updateTask(int id,Task task) {
        int rowCount = jdbcTemplate.update("update tasks set task_name=?,email=?,location=? where id=?",task.getTitle(), task.getDescription(), task.isCompleted(),id);
        if (rowCount > 0) {
            return getTaskById(task.getId()).get();
        }
        return null;
    }

    public Optional<Task> getTaskById(int id) {
        try {
            Task task = jdbcTemplate.queryForObject("select * from tasks where id=" + id, new TaskRowMapper());
            return Optional.of(task);
        }
        catch (DataAccessException ex){
            throw new RecordNotFoundException("Task with id : "+id+" Not Found");
        }
    }

    public Optional<Task> getTaskByTitle(String name) {
        Task task =jdbcTemplate.queryForObject("select * from tasks where title='"+name+"'", new TaskRowMapper());
        if (task==null){
            throw new RecordNotFoundException("Task with name : "+name+" Not Found");
        }
        return Optional.of(task);
    }

    public List<Task> getAllTasks() {
        return jdbcTemplate.query("select * from tasks", new TaskRowMapper());
    }

    public void deleteTask(int id) {
        jdbcTemplate.update("delete from tasks where id=?",id);
    }
}


