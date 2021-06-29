package com.spring.todolist.controller;


import com.spring.todolist.exception.ResourceNotFoundException;
import com.spring.todolist.model.User;
import com.spring.todolist.repository.UserRepository;
import com.spring.todolist.security.CurrentUser;
import com.spring.todolist.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins="*")
@RestController
@RequiredArgsConstructor
public class UserController{

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }


//    @GetMapping("/user/all")
//    Iterable<User> all() {
//        return userRepository.findAll();
//    }

//    @GetMapping("/user/{id}")
//    User userById(@PathVariable String id) {
//        return userRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
//                HttpStatus.NOT_FOUND));
//    }
//
//    @PostMapping("/user/save")
//    User save(@RequestBody User user) {
//        return userRepository.save(user);
//    }
//
//    @DeleteMapping("/user/del/{id}")
//    void delete(@PathVariable String id) {
//        userRepository.delete(userRepository.getOne(id));
//    }
//
//    @PutMapping("/user/update")
//    User update(@RequestBody User user){ return userRepository.save(user);}

}
