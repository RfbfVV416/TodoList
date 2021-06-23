package com.spring.todolist.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.spring.todolist.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {
}
