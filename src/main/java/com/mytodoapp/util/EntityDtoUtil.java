package com.mytodoapp.util;

import com.mytodoapp.dto.TaskDTO;
import com.mytodoapp.model.Task;

public class EntityDtoUtil {
    public static Task convertToTaskEntity(TaskDTO taskDto) {
        Task task = new Task();
        task.setId(taskDto.id());
        task.setTitle(taskDto.title());
        task.setDescription(taskDto.description());
        task.setCompleted(taskDto.isCompleted());
        return task;
    }

    public static TaskDTO convertToTaskDTO(Task task) {
        return new TaskDTO(task.getId(), task.getTitle(), task.getDescription(),task.isCompleted());
    }
}
