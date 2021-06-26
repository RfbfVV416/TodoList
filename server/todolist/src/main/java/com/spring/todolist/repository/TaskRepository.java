package com.spring.todolist.repository;

import com.spring.todolist.model.Task;
import com.spring.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

    public Task findByOwner(String id);

}
