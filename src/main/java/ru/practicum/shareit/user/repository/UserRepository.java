package ru.practicum.shareit.user.repository;

import ru.practicum.shareit.user.dto.UserDTO;
import ru.practicum.shareit.user.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User createNewUser(User user);

    UserDTO updateUser(User user);

    void deleteUserById(Long id);
}