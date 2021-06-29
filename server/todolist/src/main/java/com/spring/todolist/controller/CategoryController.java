package com.spring.todolist.controller;


import com.spring.todolist.security.CurrentUser;
import com.spring.todolist.security.UserPrincipal;
import com.spring.todolist.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import com.spring.todolist.model.Category;


@RestController
@RequestMapping("/category")
public class CategoryController {

    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/save")
    ResponseEntity<Object> save(@CurrentUser UserPrincipal userPrincipal, @RequestBody Category category) {
        return categoryService.save(userPrincipal, category);
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<Object> all(@CurrentUser UserPrincipal userPrincipal) {
        return categoryService.getAll(userPrincipal);
    }

    @DeleteMapping("/del/{id}")
    @PreAuthorize("hasRole('USER')")
    ResponseEntity<Object> delete(@CurrentUser UserPrincipal userPrincipal, @PathVariable String id) {
        return categoryService.delete(userPrincipal, id);
    }

}
