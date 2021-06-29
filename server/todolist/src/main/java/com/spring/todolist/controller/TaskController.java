package com.spring.todolist.controller;


import com.spring.todolist.security.CurrentUser;
import com.spring.todolist.security.UserPrincipal;
import com.spring.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.spring.todolist.model.Task;


@RestController
@RequestMapping("/task")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<Object> save(@CurrentUser UserPrincipal userPrincipal, @RequestBody Task task) {
        return taskService.save(userPrincipal, task);
    }


    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<Object>  getAll(@CurrentUser UserPrincipal userPrincipal) {
        return taskService.getAll(userPrincipal);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<Object> getOne(@CurrentUser UserPrincipal userPrincipal, @PathVariable String id) {
        return taskService.getOne(userPrincipal, id);
    }

    @PutMapping(path="/update/{id}", produces= "application/json")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<Object> update(@CurrentUser UserPrincipal userPrincipal, @RequestBody Task task, @PathVariable String id) {
        return taskService.update(userPrincipal, task, id);
    }

    @DeleteMapping("/del/{id}")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<Object> delete(@CurrentUser UserPrincipal userPrincipal, @PathVariable String id) {
        return taskService.delete(userPrincipal, id);
    }


}
