package com.spring.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.spring.todolist.model.Category;
import com.spring.todolist.model.Task;
import com.spring.todolist.model.User;
import com.spring.todolist.repository.TaskRepository;


@RestController
@RequestMapping("/task")
public class TaskController {
    TaskRepository taskRepository;

    @Autowired
    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @PostMapping("/save")
    Task save(@RequestBody Task task) {
        return taskRepository.save(task);
    }

    @DeleteMapping("/del/{id}")
    void delete(@PathVariable String id) {
        Task delTask = taskRepository.getOne(id);

        for (Category category: delTask.getCategories()) {
            for (Task task: category.getTasks()) {
                if (delTask.getId().equals(task.getId())){
                    category.getTasks().remove(task);
                }
            }
        }
        taskRepository.delete(delTask);
    }

    @GetMapping("/all")
    Iterable<Task> all() {
        return taskRepository.findAll();
    }

    @PutMapping("/update")
    Task update(@RequestBody Task task) {
        return taskRepository.save(task);
    }





}
