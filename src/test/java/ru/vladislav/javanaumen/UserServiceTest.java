package ru.vladislav.javanaumen;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.vladislav.javanaumen.entity.Role;
import ru.vladislav.javanaumen.entity.User;
import ru.vladislav.javanaumen.repository.UserRepository;
import ru.vladislav.javanaumen.service.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserServiceImpl userService;

    @BeforeEach
    void set() {
        userRepository = Mockito.mock(UserRepository.class);
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    void testAddUser() {
        var user = new User();
        user.setUsername("testUser");
        user.setPassword("123");

        when(userRepository.findByUsername("testUser")).thenReturn(null);
        when(passwordEncoder.encode("123")).thenReturn("encodedPassword");

        userService.addUser(user);

        assertEquals(Role.USER, user.getRole());
        assertEquals("encodedPassword", user.getPassword());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void testAddUserExists() {
        var existingUser = new User();
        existingUser.setUsername("testUser");

        when(userRepository.findByUsername("testUser")).thenReturn(existingUser);

        var newUser = new User();
        newUser.setUsername("testUser");
        newUser.setPassword("123");

        var exception = assertThrows(RuntimeException.class, () -> userService.addUser(newUser));

        assertEquals("Пользователь существует", exception.getMessage());

        verify(userRepository, never()).save(any());
    }

    @Test
    void testGetUser() {
        var user = new User();
        user.setUsername("testUser");

        when(userRepository.findByUsername("testUser")).thenReturn(user);

        var result = userService.getUser("testUser");

        assertNotNull(result);
        assertEquals("testUser", result.getUsername());
    }
}
