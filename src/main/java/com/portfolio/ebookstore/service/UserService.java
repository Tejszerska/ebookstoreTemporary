package com.portfolio.ebookstore.service;

import com.portfolio.ebookstore.components.Mappers;
import com.portfolio.ebookstore.entities.User;
import com.portfolio.ebookstore.model.Address;
import com.portfolio.ebookstore.model.dto.UserDto;
import com.portfolio.ebookstore.model.enums.Role;
import com.portfolio.ebookstore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final Mappers mapper;

    public void addUser(UserDto userDto) {
        User user = new User(userDto.getEmail(), userDto.getPassword(),
                new Address(userDto.getName(), userDto.getSurname(), userDto.getCity(), userDto.getStreet(), userDto.getZipCode()),
                Role.USER);
        userRepository.save(user);
    }

    public UserDto getUserDtoById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            return userOptional.map(mapper::mapUserToDto).get();
        } else {
            log.error("User by id " + userId + " doesn't exist");
            return null;
        }
    }

    public List<UserDto> getUserDtos() {
        return userRepository.findAll().stream().map(mapper::mapUserToDto).collect(Collectors.toList());
    }
}
