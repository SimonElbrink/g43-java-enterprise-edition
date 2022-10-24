package se.lexicon.spring_bootrestful_api.service;

import se.lexicon.spring_bootrestful_api.exception.ResourceNotFoundException;
import se.lexicon.spring_bootrestful_api.model.dto.CustomUserDto;
import se.lexicon.spring_bootrestful_api.model.dto.UserDto;

public interface UserService {
    UserDto register(UserDto dto) throws ResourceNotFoundException;

    CustomUserDto findByUsername(String username) throws ResourceNotFoundException;

    void disableUserByUsername(String username) throws ResourceNotFoundException;

    void enableUserByUsername(String username) throws ResourceNotFoundException;
}
