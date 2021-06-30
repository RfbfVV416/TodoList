package com.spring.todolist.repository;

import com.spring.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;


@Repository
public interface TaskRepository extends JpaRepository<Task, String> {

    Iterable<Task> findByOwner(String id);
    Optional<Task> findByIdAndOwner(String id, String owner_id);
    void deleteByIdAndOwner(String id, String owner);

}
