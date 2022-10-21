package se.lexicon.spring_bootrestful_api.service;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.spring_bootrestful_api.model.dto.CustomUserDto;
import se.lexicon.spring_bootrestful_api.model.dto.UserDto;
import se.lexicon.spring_bootrestful_api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto register(UserDto dto) throws ObjectNotFoundException {
        //TODO
        //Check if the input data is value
        //Check if the username is a duplicate
        //Convert Dto To An Entity UserDto -> User + RoleDto -> Role
        //Save Entity to Database
        //Convert Entity to Dto Role -> RoleDto + User -> UserDto
        //Return Dto

        return null;
    }

    @Override
    public CustomUserDto findByUsername(String username) throws ObjectNotFoundException {

        //TODO: find user by username
        return null;
    }

    @Override
    public void disableUserByUsername(String username) throws ObjectNotFoundException {
        // TODO: implement disable user by username
    }

    @Override
    public void enableUserByUsername(String username) throws ObjectNotFoundException {
        // TODO: implement disable user by username
    }
}
