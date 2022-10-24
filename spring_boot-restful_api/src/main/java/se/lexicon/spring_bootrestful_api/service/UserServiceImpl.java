package se.lexicon.spring_bootrestful_api.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.lexicon.spring_bootrestful_api.exception.ResourceDuplicateException;
import se.lexicon.spring_bootrestful_api.exception.ResourceNotFoundException;
import se.lexicon.spring_bootrestful_api.model.dto.CustomUserDto;
import se.lexicon.spring_bootrestful_api.model.dto.RoleDto;
import se.lexicon.spring_bootrestful_api.model.dto.UserDto;
import se.lexicon.spring_bootrestful_api.model.entity.User;
import se.lexicon.spring_bootrestful_api.repository.RoleRepository;
import se.lexicon.spring_bootrestful_api.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto register(UserDto dto) throws ResourceNotFoundException {
        //TODO
        //Check if the input data is
        if (dto == null) throw new IllegalArgumentException("User DTO Was Null");
        if (dto.getUsername() == null) throw new IllegalArgumentException("Username can not be Null");
        if (dto.getPassword() == null) throw new IllegalArgumentException("Password can not be Null");
        if (dto.isExpired()) throw new IllegalArgumentException("Expired must be a false or null");
        if (dto.getRoles() == null || dto.getRoles().size() == 0) throw new IllegalArgumentException("No Roles found");
        //Check if the username is a duplicate
        if (userRepository.existsByUsername(dto.getUsername()))
            throw new ResourceDuplicateException("Username is already in use");
        //Check if Roles are valid Roles
        for (RoleDto roleDto : dto.getRoles()) {
            roleRepository.findById(roleDto.getId()).orElseThrow(() -> new ResourceNotFoundException("Role ID Was not Valid"));
        }
        //Convert Dto To An Entity UserDto -> User + RoleDto -> Role
        User convertedToEntity = modelMapper.map(dto, User.class);
//        System.out.println("convertedToEntity = " + convertedToEntity);
        //Save Entity to Database
        User createdUser = userRepository.save(convertedToEntity);
//        System.out.println("createdUser = " + createdUser);
        //Convert Entity to Dto Role -> RoleDto + User -> UserDto
        UserDto convertedToDto = modelMapper.map(createdUser, UserDto.class);
        //Return Dto
        return convertedToDto;
    }

    @Override
    public CustomUserDto findByUsername(String username) throws ResourceNotFoundException {

        //TODO: find user by username
        return null;
    }

    @Override
    public void disableUserByUsername(String username) throws ResourceNotFoundException {
        // TODO: implement disable user by username
    }

    @Override
    public void enableUserByUsername(String username) throws ResourceNotFoundException {
        // TODO: implement disable user by username
    }
}
