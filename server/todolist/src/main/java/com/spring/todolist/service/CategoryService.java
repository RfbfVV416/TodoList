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
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**save user's new category*/
    public ResponseEntity<Object> save(@CurrentUser UserPrincipal userPrincipal, @RequestBody Category category){
        category.setOwner(userPrincipal.getId());
        categoryRepository.save(category);
        return ResponseEntity.status(HttpStatus.OK).body(category);
    }

    /**get all categories for user*/
    public ResponseEntity<Object> getAll(@CurrentUser UserPrincipal userPrincipal){
        return ResponseEntity.status(HttpStatus.OK).body(categoryRepository.findByOwner(userPrincipal.getId()));
    }

    /**delete user's category*/
    @Transactional
    public ResponseEntity<Object> delete(@CurrentUser UserPrincipal userPrincipal, @PathVariable String id) {
        Optional<Category> category = categoryRepository.findByIdAndOwner(id, userPrincipal.getId());
        if (category.isPresent()){
            categoryRepository.deleteByIdAndOwner(id, userPrincipal.getId());
            return ResponseEntity.status(HttpStatus.OK).body("Category was deleted successfully");
        }
        else return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Specified Category not found");
    }
}
