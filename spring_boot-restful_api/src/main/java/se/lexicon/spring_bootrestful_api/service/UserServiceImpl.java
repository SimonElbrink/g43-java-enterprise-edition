package se.lexicon.spring_bootrestful_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.spring_bootrestful_api.model.dto.UserDto;
import se.lexicon.spring_bootrestful_api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto register(UserDto dto) {
        //TODO
        //Check if the input data is value
        //Check if the username is a duplicate
        //Convert Dto To An Entity UserDto -> User + RoleDto -> Role
        //Save Entity to Database
        //Convert Entity to Dto
        //Return Dto

        return null;
    }
}
