package com.mytodoapp.service;

import com.mytodoapp.dto.TaskDTO;
import com.mytodoapp.exception.DuplicateRecordException;
import com.mytodoapp.exception.RecordNotFoundException;
import com.mytodoapp.model.Task;
import com.mytodoapp.repository.TaskRepository;
import static com.mytodoapp.util.EntityDtoUtil.*;

import com.mytodoapp.util.EntityDtoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class TaskServiceImpl implements TaskService {

    private static final Logger log = LoggerFactory.getLogger(TaskServiceImpl.class);
    private final TaskRepository taskRepository;

        @Autowired
        public TaskServiceImpl(TaskRepository taskRepository) {
            this.taskRepository = taskRepository;
        }

        public TaskDTO saveTask(TaskDTO task) {
            if (taskRepository.existsById(task.id())){
                throw new DuplicateRecordException("ID is already present");
            }
            Task savedTask = taskRepository.save(convertToTaskEntity(task));
            return convertToTaskDTO(savedTask);
        }

    public List<TaskDTO> getTaskByTitle(String title){
       return taskRepository.findByTitle(title).stream().map(EntityDtoUtil::convertToTaskDTO).toList();
    }

    public TaskDTO getTask(int id) {
        return taskRepository.findById(id).map(EntityDtoUtil::convertToTaskDTO)
                .orElseThrow(()->new RecordNotFoundException("Task with id " + id + " Not Present"));    }

    public TaskDTO getTaskById(int id) {
            return taskRepository.findById(id).map(EntityDtoUtil::convertToTaskDTO)
                    .orElse(null);
        }

        public List<TaskDTO> getAllTasks() {
            return taskRepository.findAll().parallelStream()
                    .map(EntityDtoUtil::convertToTaskDTO)
                    .toList();
        }

    public List<TaskDTO> getCompletedTasks(boolean completed) {
        return taskRepository.findCompletedTasks(completed).parallelStream().map(EntityDtoUtil::convertToTaskDTO).toList();
    }

    public void deleteTask(int id) {
        if (!taskRepository.existsById(id)){
            throw new RecordNotFoundException("Trainee with id " + id + " Not Present");
        }
        taskRepository.deleteById(id);
        }

        public TaskDTO updateTask(int id, TaskDTO task) {
            Task taskToUpdate = taskRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("Task with id " + id + " Not Present"));

            if (task.title() != null) taskToUpdate.setTitle(task.title());
            if (task.description() != null) taskToUpdate.setDescription(task.description());
            taskToUpdate.setCompleted(task.isCompleted());
            taskRepository.save(taskToUpdate);

            return EntityDtoUtil.convertToTaskDTO(taskToUpdate);
        }

    }