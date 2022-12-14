package se.lexicon.spring_bootrestful_api.controller;

import org.hibernate.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.spring_bootrestful_api.model.dto.CustomUserDto;
import se.lexicon.spring_bootrestful_api.model.dto.UserDto;
import se.lexicon.spring_bootrestful_api.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDto> register(@Valid @RequestBody UserDto userDto) {

        System.out.println("userDto = " + userDto);

        //If any Exception - ExceptionHandler takes care of it. Hopefully..
        UserDto result = userService.register(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);

        //Manually Catching the Exception
//        UserDto result = null;
//        try{
//            result = userService.register(userDto);
//            return ResponseEntity.status(HttpStatus.CREATED).body(result);
//        }catch (ResourceNotFoundException ex){
//            ex.printStackTrace();
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<CustomUserDto> findByUsername(@PathVariable("username") String username) throws ObjectNotFoundException {
        // TODO: implement rest full api for find by username

        return null;
    }


    @PutMapping("/disable")
    public ResponseEntity<Void> disableUser(@RequestParam("username") String username) throws ObjectNotFoundException {
        // TODO: implement disable user by username api
        return null;
    }


    @PutMapping("/enable")
    public ResponseEntity<Void> enableUser(@RequestParam("username") String username) throws ObjectNotFoundException {
        // TODO: implement enable user by username api
        return null;
    }

}
