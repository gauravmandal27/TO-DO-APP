package com.mytodoapp.service;

import com.mytodoapp.dto.TaskDTO;
import com.mytodoapp.model.Task;
import com.mytodoapp.repository.ITaskRepository;
import com.mytodoapp.util.EntityDtoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TaskService implements ITaskService{

    private final ITaskRepository repository;

    @Autowired
    public TaskService(ITaskRepository repository) {
        this.repository = repository;
    }

    public TaskDTO saveTask(TaskDTO TaskDTO) {
        Task Task = repository.saveTask(EntityDtoUtil.convertToTaskEntity(TaskDTO));
        return EntityDtoUtil.convertToTaskDTO(Task);
    }

    public TaskDTO getTask(int id) {
        return repository.getTaskById(id).map(EntityDtoUtil::convertToTaskDTO).orElse(null);
    }

    public TaskDTO getTaskByName(String name) {
        return repository.getTaskByTitle(name).map(EntityDtoUtil::convertToTaskDTO).orElse(null);
    }

    public void deleteTask(int id) {
        repository.deleteTask(id);
    }

    public List<TaskDTO> getAllTasks() {
        return repository.getAllTasks().parallelStream().map(EntityDtoUtil::convertToTaskDTO).toList();
    }

    public TaskDTO updateTask(int id, TaskDTO TaskDTO) {
        Task task = repository.updateTask(id,EntityDtoUtil.convertToTaskEntity(TaskDTO));
        return EntityDtoUtil.convertToTaskDTO(task);
    }
}
