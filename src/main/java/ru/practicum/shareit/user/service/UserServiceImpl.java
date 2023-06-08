package ru.practicum.shareit.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.practicum.shareit.exception.UserNotFoundException;
import ru.practicum.shareit.user.dto.UserRequestDto;
import ru.practicum.shareit.user.mapper.UserMapper;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserRequestDto createNewUser(UserRequestDto userRequestDto) {
        User user = UserMapper.userRequestDtoToUser(userRequestDto);
        return UserMapper.userToUserRequestDto(userRepository.save(user));
    }

    @Override
    public List<UserRequestDto> getAllUsers() {
        return UserMapper.userToUserRequestDto(userRepository.findAll());
    }

    @Override
    public UserRequestDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(()
                -> new UserNotFoundException("Пользователь с id: '" + id + "' не найден"));
        return UserMapper.userToUserRequestDto(user);
    }

    @Override
    public UserRequestDto updateUserById(UserRequestDto userRequestDto, Long id) {
        String email = userRequestDto.getEmail();
        String name = userRequestDto.getName();
        User user = userRepository.findById(id).orElseThrow(()
                -> new UserNotFoundException("Пользователь с id: '" + id + "' не найден"));

        if (email != null) {
            user.setEmail(email);
        }
        if (name != null) {
            user.setName(name);
        }

        return UserMapper.userToUserRequestDto(userRepository.save(user));
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}