package com.ccat.ordersys.controller;

import com.ccat.ordersys.model.entity.User;
import com.ccat.ordersys.model.repository.UserDao;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class UserController {

    private final UserDao userDao;
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    //Get User via Id:
    @RequestMapping("/users/{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        Optional<User> response = userDao.findById(id);
        if(response.isPresent()) {
            return ResponseEntity.ok(response.get());
        }
        else return ResponseEntity.notFound().build();
    }
}
