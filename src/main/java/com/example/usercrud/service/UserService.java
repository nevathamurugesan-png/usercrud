package com.example.usercrud.service;

import com.example.usercrud.dto.UserDTO;
import com.example.usercrud.entity.User;
import com.example.usercrud.exception.UserNotFoundException;
import com.example.usercrud.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(UserDTO dto) {

        User user = new User();
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {

        return userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found with id " + id));
    }

    public User updateUser(Long id, UserDTO dto) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));

        userRepository.delete(user);
    }

    public Page<User> getUsersWithPagination(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public List<User> getUsersSorted(String field, String direction) {

        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(field).descending() :
                Sort.by(field).ascending();

        return userRepository.findAll(sort);
    }
}