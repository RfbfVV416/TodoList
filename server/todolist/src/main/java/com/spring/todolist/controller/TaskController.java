package com.spring.todolist.controller;

import com.spring.todolist.repository.UserRepository;
import com.spring.todolist.security.CurrentUser;
import com.spring.todolist.security.UserPrincipal;
import com.spring.todolist.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.spring.todolist.model.Category;
import com.spring.todolist.model.Task;
import com.spring.todolist.model.User;
import com.spring.todolist.repository.TaskRepository;


@RestController
@RequestMapping("/task")
public class TaskController {
    private TaskRepository taskRepository;
    private UserRepository userRepository;
    private UserService userService;


    @Autowired
    public TaskController(UserService userService, UserRepository userRepository, TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    //save current user task
    @PostMapping("/save")
    @PreAuthorize("hasRole('USER')")
    String save(@CurrentUser UserPrincipal userPrincipal, @RequestBody Task task) {

        User user = userService.getUserById(userPrincipal.getId());
        user.getTasks().add(task);
        userRepository.save(user);
        return task.getId();
    }

    //get ALL current user tasks
    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    Iterable<Task> getAll(@CurrentUser UserPrincipal userPrincipal) {
        User user = userService.getUserById(userPrincipal.getId());
        return user.getTasks();
    }

    //get one current user current task
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    Task getOne(@CurrentUser UserPrincipal userPrincipal, @PathVariable String id) {
        User user = userService.getUserById(userPrincipal.getId());
        return taskRepository.findByOwner(user.getId());
    }




//    @DeleteMapping("/del/{id}")
//    void delete(@PathVariable String id) {
//        Task delTask = taskRepository.getOne(id);
//
//        for (Category category: delTask.getCategories()) {
//            for (Task task: category.getTasks()) {
//                if (delTask.getId().equals(task.getId())){
//                    category.getTasks().remove(task);
//                }
//            }
//        }
//        taskRepository.delete(delTask);
//    }

//    @GetMapping("/all")
//    Iterable<Task> all() {
//        return taskRepository.findAll();
//    }

//    @PutMapping("/update")
//    Task update(@RequestBody Task task) {
//        return taskRepository.save(task);
//    }





}
