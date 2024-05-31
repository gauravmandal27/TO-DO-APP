package com.mytodoapp.service;

import com.mytodoapp.dto.TaskDTO;

import java.util.List;

public interface TaskService {

    public TaskDTO saveTask(TaskDTO taskDto);

    public TaskDTO getTask(int id);

    public TaskDTO getTaskById(int id);

    public List<TaskDTO> getTaskByTitle(String title);

    public void deleteTask(int id);

    public List<TaskDTO> getAllTasks();

    public List<TaskDTO> getCompletedTasks(boolean completed);

    public TaskDTO updateTask(int id, TaskDTO task);
}
