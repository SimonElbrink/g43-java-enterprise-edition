package se.lexicon.spring_bootrestful_api.service;

import se.lexicon.spring_bootrestful_api.model.dto.UserDto;

public interface UserService {
    UserDto register(UserDto dto);
}
