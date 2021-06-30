package com.spring.todolist.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spring.todolist.model.Category;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
    Iterable<Category>  findByOwner(String id);
    Optional<Category> findByIdAndOwner(String id, String owner_id);
    void deleteByIdAndOwner(String id, String owner);
}
