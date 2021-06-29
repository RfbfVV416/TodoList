package com.spring.todolist.service;


import com.spring.todolist.model.Task;
import com.spring.todolist.repository.TaskRepository;
import com.spring.todolist.security.CurrentUser;
import com.spring.todolist.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import javax.transaction.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    /**save user's task*/
    public ResponseEntity<Object> save(@CurrentUser UserPrincipal userPrincipal, @RequestBody Task task){
        task.setOwner(userPrincipal.getId());
        Task savedTask = taskRepository.save(task);
        if (taskRepository.findById(savedTask.getId()).isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(task);
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save Task");
    }

    /**get all current user's tasks*/
    public ResponseEntity<Object> getAll(@CurrentUser UserPrincipal userPrincipal){
        return ResponseEntity.status(HttpStatus.OK).body(taskRepository.findByOwner(userPrincipal.getId()));
    }

    /**get one current user's task*/
    public ResponseEntity<Object> getOne(@CurrentUser UserPrincipal userPrincipal, @PathVariable String id){
        if (taskRepository.findByIdAndOwner(id, userPrincipal.getId()).isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(taskRepository.findByIdAndOwner(id, userPrincipal.getId()));
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Specified Task not found");
    }

    /**update user's task*/
    public ResponseEntity<Object> update(@CurrentUser UserPrincipal userPrincipal, @RequestBody Task task, @PathVariable String id){
        if (taskRepository.findByIdAndOwner(id, userPrincipal.getId()).isPresent()){
            Task newTask = taskRepository.findByIdAndOwner(id, userPrincipal.getId()).get();
            newTask.setInput(task.getInput());
            newTask.setTitle(task.getTitle());
            newTask.setStatus(task.getStatus());
            newTask.setCreationDate(task.getCreationDate());
            newTask.setLastModifiedDate(task.getLastModifiedDate());
            newTask.setCategories(task.getCategories());
            Task savedTask = taskRepository.save(newTask);
            if (taskRepository.findById(savedTask.getId()).isPresent()) {
                return ResponseEntity.status(HttpStatus.ACCEPTED).body("Task was updated successfully");
            }
            else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to update Task");
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Specified Task not found");
    }

    /**delete user's task*/
    @Transactional
    public ResponseEntity<Object> delete(@CurrentUser UserPrincipal userPrincipal, @PathVariable String id) {
        if (taskRepository.findByIdAndOwner(id, userPrincipal.getId()).isPresent()) {
            taskRepository.deleteByIdAndOwner(id, userPrincipal.getId());
            if (taskRepository.findByIdAndOwner(id, userPrincipal.getId()).isPresent()){
                return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to Delete the specified Task");
            }
            else return ResponseEntity.status(HttpStatus.OK).body("Task was deleted successfully");
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Specified Task not found");
    }









}
