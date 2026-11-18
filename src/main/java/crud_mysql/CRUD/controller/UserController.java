package crud_mysql.CRUD.controller;

import crud_mysql.CRUD.entity.UserEntity;
import crud_mysql.CRUD.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public ArrayList<UserEntity> getUsers() {
        return this.userService.getusers();
    }

    @PostMapping("/guardar")
    public UserEntity saveUser(@RequestBody UserEntity userEntity) {
        return this.userService.saveUser(userEntity);
    }

    @GetMapping("/{id}")
    public Optional<UserEntity> getUserById(@PathVariable("id") Long id) {
        return this.userService.getById(id);
    }

    @PutMapping("/{id}")
    public UserEntity updateUserById(@RequestBody UserEntity userEntity, @PathVariable("id") Long id) {
        return this.userService.updateById(userEntity, id);
    }

    //    @DeleteMapping("/{id}")
//    public String deleteById(@PathVariable("id") Long id){
//        boolean ok = this.userService.deleteUser(id);
//        if(ok){
//            return "el usuario con el "+ id + " ha sido eliminado";
//        }else {
//            return "Error, No exite el usuario con el id " + id;
//        }
//    }
    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable("id") Long id) {
        boolean ok = this.userService.deleteUser(id);
        if (ok) {
            return "User with id " + id + " deleted";
        } else {
            return "Error: No existe ningún usuario con el id " + id;
        }
    }

}
