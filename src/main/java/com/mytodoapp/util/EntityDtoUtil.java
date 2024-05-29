package com.mytodoapp.util;

import com.mytodoapp.dto.TaskDTO;
import com.mytodoapp.model.Task;

public class EntityDtoUtil {
    public static Task convertToTaskEntity(TaskDTO TaskDTO) {
        return new Task(TaskDTO.id(), TaskDTO.title(), TaskDTO.description(),TaskDTO.isCompleted());
    }

    public static TaskDTO convertToTaskDTO(Task task) {
        return new TaskDTO(task.getId(), task.getTitle(), task.getDescription(),task.isCompleted());
    }
}
