package TodoList.Controller;

import TodoList.Model.UserModel;
import TodoList.Repository.UserRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/user")
public class UserController {
    UserRespository userRespository;

    @Autowired
    public UserController(UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    @GetMapping("/all")
    Iterable<UserModel> all() {
        return userRespository.findAll();
    }

    @GetMapping("/{id}")
    UserModel userById(@PathVariable Long id) {
        return userRespository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    UserModel save(@RequestBody UserModel user) {
        return userRespository.save(user);
    }

    @DeleteMapping("/del/{id}")
    void delete(@PathVariable Long id) {
        userRespository.delete(userRespository.getOne(id));
    }

    @PutMapping("/update")
    UserModel update(@RequestBody UserModel user){ return userRespository.save(user);}

}
