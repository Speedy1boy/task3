package ru.vladislav.javanaumen.service;

import ru.vladislav.javanaumen.entity.User;

public interface UserService {
    User getUser(String username);

    void addUser(User user);
}
