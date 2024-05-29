package com.mytodoapp.service;

import com.mytodoapp.dto.TaskDTO;

import java.util.List;

public interface ITaskService {

    public TaskDTO saveTask(TaskDTO taskDto);

    public TaskDTO getTask(int id);

    public TaskDTO getTaskByName(String name);

    public void deleteTask(int id);

    public List<TaskDTO> getAllTasks();

    public TaskDTO updateTask(int id,TaskDTO taskDto);
}
