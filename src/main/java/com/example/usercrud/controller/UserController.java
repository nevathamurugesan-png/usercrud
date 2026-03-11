package com.example.usercrud.controller;

import com.example.usercrud.dto.UserDTO;
import com.example.usercrud.entity.User;
import com.example.usercrud.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public User createUser(@RequestBody UserDTO dto) {
        return userService.createUser(dto);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                           @RequestBody UserDTO dto) {
        return userService.updateUser(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/page")
    public Page<User> getUsersWithPagination(Pageable pageable) {
        return userService.getUsersWithPagination(pageable);
    }

    @GetMapping("/sort")
    public List<User> getUsersSorted(@RequestParam String field,
                                     @RequestParam String direction) {

        return userService.getUsersSorted(field, direction);
    }
}