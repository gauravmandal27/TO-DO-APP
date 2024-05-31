package com.mytodoapp.controller;

import com.mytodoapp.dto.TaskDTO;
import com.mytodoapp.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    @Autowired
    private TaskService taskService;


    @GetMapping
    public List<TaskDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/completed-task/{completed}")
    public List<TaskDTO> getCompletedTasks(@PathVariable("completed") boolean completed) {
        return taskService.getCompletedTasks(completed);
    }


    @PostMapping
    public ResponseEntity<TaskDTO> saveTask(@RequestBody TaskDTO taskDto) {
        TaskDTO savedTask = taskService.saveTask(taskDto);
        HttpStatus status;
        if (savedTask != null) {
            status = HttpStatus.CREATED;
        } else {
            status = HttpStatus.BAD_REQUEST;
        }
        return ResponseEntity.status(status).body(savedTask);
    }

    @GetMapping("/{id}")
    public TaskDTO getTaskById(@PathVariable("id") int taskId) {
        return taskService.getTaskById(taskId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTaskById(@PathVariable("id") int taskId) {
        taskService.deleteTask(taskId);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public TaskDTO updateTask(@PathVariable int id, @RequestBody TaskDTO taskDto) {
        return taskService.updateTask(id, taskDto);
    }

    @GetMapping("/search")
    public List<TaskDTO> searchTasks(@RequestParam("title") String title) {
        return taskService.getTaskByTitle(title);
    }
}
//
//    @Autowired
//    private ITaskService taskService;
//
//    @GetMapping
//    public List<TaskDTO> getAllTasks(){
//        return taskService.getAllTasks();
//    }
////
////    @GetMapping("/completed-tasks")
////    public List<TaskDTO> getAllTasks(){
////        return taskService.getAllTasks();
////    }
//
//
//    @PostMapping
//    public ResponseEntity<TaskDTO> saveTask(@RequestBody @Valid TaskDTO TaskDTO){
//        TaskDTO savedTask = taskService.saveTask(TaskDTO);
//        HttpStatus status;
//        if(savedTask!=null){
//            status = HttpStatus.CREATED;
//        }
//        else {
//            status = HttpStatus.BAD_REQUEST;
//        }
//        return ResponseEntity.status(status).body(savedTask);
//    }
//
//    @GetMapping("/{id}")
//    public TaskDTO getTaskById(@PathVariable("id") int TaskId){
//        return taskService.getTask(TaskId);
//    }
//
//    @DeleteMapping("/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteTaskById(@PathVariable("id") int TaskId){
//        taskService.deleteTask(TaskId);
//    }
//
//    @PutMapping("/{id}")
//    @ResponseStatus(HttpStatus.CREATED)
//    public TaskDTO updateTask(@PathVariable int id, @RequestBody TaskDTO TaskDTO){
//        return taskService.updateTask(id,TaskDTO);
//    }