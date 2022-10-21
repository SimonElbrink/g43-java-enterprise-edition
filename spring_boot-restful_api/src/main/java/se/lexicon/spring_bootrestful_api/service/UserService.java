package se.lexicon.spring_bootrestful_api.service;

import org.hibernate.ObjectNotFoundException;
import se.lexicon.spring_bootrestful_api.model.dto.CustomUserDto;
import se.lexicon.spring_bootrestful_api.model.dto.UserDto;

public interface UserService {
    UserDto register(UserDto dto) throws ObjectNotFoundException;

    CustomUserDto findByUsername(String username) throws ObjectNotFoundException;

    void disableUserByUsername(String username) throws ObjectNotFoundException;

    void enableUserByUsername(String username) throws ObjectNotFoundException;
}
