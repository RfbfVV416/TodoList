package com.spring.todolist.service;


import com.spring.todolist.mapper.TaskMapperImpl;
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
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    /**save user's task*/
    public ResponseEntity<Object> save(@CurrentUser UserPrincipal userPrincipal, @RequestBody Task task){
        task.setOwner(userPrincipal.getId());
        taskRepository.save(task);
        return ResponseEntity.status(HttpStatus.OK).body(task.getId());
    }

    /**get all tasks for user*/
    public ResponseEntity<Object> getAll(@CurrentUser UserPrincipal userPrincipal){
        return ResponseEntity.status(HttpStatus.OK).body(taskRepository.findByOwner(userPrincipal.getId()));
    }

    /**get one task for user*/
    public ResponseEntity<Object> getOne(@CurrentUser UserPrincipal userPrincipal, @PathVariable String id){
        Optional<Task> task = taskRepository.findByIdAndOwner(id, userPrincipal.getId());
        return task.<ResponseEntity<Object>>map(value -> ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(()
                -> ResponseEntity.status(HttpStatus.NOT_FOUND).body("Specified Task not found"));
    }

    /**update user's task*/
    public ResponseEntity<Object> update(@CurrentUser UserPrincipal userPrincipal, @RequestBody Task task, @PathVariable String id){
       Optional<Task> oldTask = taskRepository.findByIdAndOwner(id, userPrincipal.getId());
        if (oldTask.isPresent()){
            TaskMapperImpl taskMapper = new TaskMapperImpl();
            Task newTask = taskMapper.taskToTask(task, oldTask.get());
            taskRepository.save(newTask);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Task was updated successfully");
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Specified Task not found");
    }

    /**delete user's task*/
    @Transactional
    public ResponseEntity<Object> delete(@CurrentUser UserPrincipal userPrincipal, @PathVariable String id) {
        if (taskRepository.findByIdAndOwner(id, userPrincipal.getId()).isPresent()) {
            taskRepository.deleteByIdAndOwner(id, userPrincipal.getId());
            return ResponseEntity.status(HttpStatus.OK).body("Task was deleted successfully");
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Specified Task not found");
    }

}
