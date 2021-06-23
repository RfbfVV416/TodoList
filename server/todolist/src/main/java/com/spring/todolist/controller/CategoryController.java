package com.spring.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.spring.todolist.model.Category;
import com.spring.todolist.model.Task;
import com.spring.todolist.model.User;
import com.spring.todolist.repository.CategoryRepository;
import com.spring.todolist.repository.TaskRepository;

@RestController
@RequestMapping("/category")
public class CategoryController {
    CategoryRepository categoryRepository;

    @Autowired
    public CategoryController(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @PostMapping("/save")
    Category save(@RequestBody Category category) {

        return categoryRepository.save(category);
    }

    @DeleteMapping("/del/{id}")
    void delete(@PathVariable String id) {

        Category delCategory = categoryRepository.getOne(id);

        for (Task task: delCategory.getTasks()) {
            for (Category category: task.getCategories()) {
                if (delCategory.getId().equals(category.getId())){
                    task.getCategories().remove(category);
                }
            }
        }
        categoryRepository.delete(delCategory);
    }

    @GetMapping("/all")
    Iterable<Category> all() {
        return categoryRepository.findAll();
    }



}
