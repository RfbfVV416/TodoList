package com.spring.todolist.service;


import com.spring.todolist.model.Category;
import com.spring.todolist.repository.CategoryRepository;
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
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**save user's new category*/
    public ResponseEntity<Object> save(@CurrentUser UserPrincipal userPrincipal, @RequestBody Category category){
        category.setOwner(userPrincipal.getId());
        Category savedCategory = categoryRepository.save(category);
        if(categoryRepository.findById(savedCategory.getId()).isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(category);
        }
        else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save Category");
    }

    /**get all current user's categories*/
    public ResponseEntity<Object> getAll(@CurrentUser UserPrincipal userPrincipal){
        return ResponseEntity.status(HttpStatus.OK).body(categoryRepository.findByOwner(userPrincipal.getId()));
    }

    /**delete user's category*/
    @Transactional
    public ResponseEntity<Object> delete(@CurrentUser UserPrincipal userPrincipal, @PathVariable String id) {
        if (categoryRepository.findByIdAndOwner(id, userPrincipal.getId()).isPresent()){
            if (categoryRepository.getOne(id).getTasks().size() == 0){
                categoryRepository.deleteByIdAndOwner(id, userPrincipal.getId());
                if (categoryRepository.findByIdAndOwner(id, userPrincipal.getId()).isPresent()){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to Delete the specified Category");
                }
                else return ResponseEntity.status(HttpStatus.OK).body("Category was deleted successfully");
            }
            else return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to delete,  Please delete all tasks associated with this category");
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Specified Category not found");
    }

}
