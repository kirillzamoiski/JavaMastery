package com.webproject.simplewebapplication.controller;

import com.webproject.simplewebapplication.entity.User;
import com.webproject.simplewebapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping
    public List<User> getAllUsers(){
        return userService.findAll();
    }

    @GetMapping(value = "/{employee_id}")
    public ResponseEntity<?> getUser(@PathVariable("employee_id") Integer employee_id) {
        User user = userService.getById(employee_id);
        if (user == null) {
            return new ResponseEntity<String>("No User found with this "+ employee_id, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) throws SQLIntegrityConstraintViolationException {
        if (userService.getById(user.getEmployee_id()) != null) {
            return new ResponseEntity<String>("Duplicate Entry "+ user.getEmployee_id(), HttpStatus.IM_USED);
        }
        userService.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user) {
        if (userService.getById(user.getEmployee_id()) == null) {
            return new ResponseEntity<String>("Unable to update as  User id " + user.getEmployee_id() + " not found.",
                    HttpStatus.NOT_FOUND);
        }

        userService.update(user);
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{employee_id}")
    public ResponseEntity<?> deleteUser(@PathVariable("employee_id") Integer employee_id) {
        User user = userService.getById(employee_id);
        if (user == null) {
            return new ResponseEntity<String>("Unable to delete as  User id " + employee_id + " not found.", HttpStatus.NOT_FOUND);
        }
        userService.delete(employee_id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }


}
