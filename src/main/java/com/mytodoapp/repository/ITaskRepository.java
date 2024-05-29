package com.mytodoapp.repository;

import com.mytodoapp.model.Task;

import java.util.List;
import java.util.Optional;

public interface ITaskRepository {

    public Task saveTask(Task task);

    public Task updateTask(int id,Task task);

    public Optional<Task> getTaskById(int id);

    public Optional<Task> getTaskByTitle(String name);

    public List<Task> getAllTasks();

    public void deleteTask(int id);

}
