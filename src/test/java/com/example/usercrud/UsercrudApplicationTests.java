package com.example.usercrud;

import com.example.usercrud.dto.UserDTO;
import com.example.usercrud.entity.User;
import com.example.usercrud.repository.UserRepository;

import com.example.usercrud.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void testGetAllUsers() {

        User user = new User();
        user.setId(1L);
        user.setName("Nevatha");

        when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> result = userService.getAllUsers();

        assertEquals(1, result.size());
    }

    @Test
    void testGetUserById() {

        User user = new User();
        user.setId(1L);

        when(userRepository.findById(1L))
                .thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void testCreateUser() {

        UserDTO dto = new UserDTO();
        dto.setName("Nevatha");

        User user = new User();
        user.setName("Nevatha");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User result = userService.createUser(dto);

        assertEquals("Nevatha", result.getName());
    }
}