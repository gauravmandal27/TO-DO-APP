package com.mytodoapp.repository;

import com.mytodoapp.dto.TaskDTO;
import com.mytodoapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface TaskRepository extends JpaRepository<Task,Integer> {
    @Query("from Task where title like :title%")
    List<Task> findByTitle(@Param("title") String title);

    @Query("from Task where isCompleted= :completed")
    List<Task> findCompletedTasks(boolean completed);
}
