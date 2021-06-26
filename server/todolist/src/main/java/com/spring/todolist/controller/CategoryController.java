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
import com.spring.todolist.repository.CategoryRepository;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryRepository categoryRepository;
    private UserRepository userRepository;
    private UserService userService;

    @Autowired
    public CategoryController(UserService userService, UserRepository userRepository, CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/save")
    String save(@CurrentUser UserPrincipal userPrincipal, @RequestBody Category category) {

        User user = userService.getUserById(userPrincipal.getId());
        user.getCategories().add(category);
        this.userRepository.save(user);
        return category.getId();
    }

    //get ALL current user categories
    @GetMapping("/all")
    @PreAuthorize("hasRole('USER')")
    Iterable<Category> all(@CurrentUser UserPrincipal userPrincipal) {
        User user = userService.getUserById(userPrincipal.getId());
        return user.getCategories();
    }


//    @DeleteMapping("/del/{id}")
//    void delete(@PathVariable String id) {
//
//        Category delCategory = categoryRepository.getOne(id);
//
//        for (Task task: delCategory.getTasks()) {
//            for (Category category: task.getCategories()) {
//                if (delCategory.getId().equals(category.getId())){
//                    task.getCategories().remove(category);
//                }
//            }
//        }
//        categoryRepository.delete(delCategory);
//    }

//    @GetMapping("/all")
//    Iterable<Category> all() {
//        return categoryRepository.findAll();
//    }



}
