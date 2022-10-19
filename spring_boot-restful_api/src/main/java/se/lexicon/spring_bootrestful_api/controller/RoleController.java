package se.lexicon.spring_bootrestful_api.controller;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.spring_bootrestful_api.exception.ResourceNotFoundException;
import se.lexicon.spring_bootrestful_api.model.dto.RoleDto;
import se.lexicon.spring_bootrestful_api.model.entity.Role;
import se.lexicon.spring_bootrestful_api.repository.RoleRepository;

import java.util.List;

@RestController
public class RoleController {

    private final RoleRepository repository;
    private final ModelMapper modelMapper;

    @Autowired
    public RoleController(RoleRepository repository, ModelMapper modelMapper) {
        this.repository = repository;
        this.modelMapper = modelMapper;
    }

    //GET http://localhost:8080/api/v1/roles/
    @GetMapping("/api/v1/roles")
    public ResponseEntity<List<RoleDto>> findAll() {
        System.out.println("###   Get Roles has been executed!   ###");

        List<Role> listOfRoles = repository.findAll();
        List<RoleDto> listOfDto = modelMapper.map(
                listOfRoles,
                new TypeToken<List<RoleDto>>() {
                }.getType()
        );

//        return ResponseEntity.status(HttpStatus.OK).body(listOfDto);
//        return ResponseEntity.status(200).body(listOfDto);
        return ResponseEntity.ok(listOfDto);
    }

    //GET http://localhost:8080/api/v1/roles/2
    //GET http://localhost:8080/api/v1/roles/99
    @GetMapping("api/v1/roles/{id}")
    public ResponseEntity<RoleDto> findByRoleId(@PathVariable("id") Integer id) {

        if (id == null) throw new IllegalArgumentException("Id was null");

        Role foundById = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Role data not Found")
        );

        return ResponseEntity.ok(modelMapper.map(foundById, RoleDto.class));
    }

    //GET http://localhost:8080/api/v1/roles/search?name=ADMIN
    @GetMapping("api/v1/roles/search")
    public ResponseEntity<RoleDto> findByRoleName(@RequestParam("name") String name) {
        if (name == null) throw new IllegalArgumentException("name was null");

        Role foundByName = repository.findByName(name).orElseThrow(
                () -> new ResourceNotFoundException("Role data not found")
        );

        return ResponseEntity.ok(modelMapper.map(foundByName, RoleDto.class));
    }

    /*
        PUT http://localhost:8080/api/v1/roles
        Content-Type: application/json

        {
            "name": "super-admin"
        }
    */
    @PostMapping("/api/v1/roles")
    public ResponseEntity<RoleDto> create(@RequestBody RoleDto roleForm) {
        System.out.println("###### In Create method");

        Role toEntity = modelMapper.map(roleForm, Role.class);
        Role savedRole = repository.save(toEntity);
        RoleDto toDto = modelMapper.map(savedRole, RoleDto.class);

        return ResponseEntity.status(HttpStatus.CREATED).body(toDto);
    }

    /*
        PUT http://localhost:8080/api/v1/roles/4
        Content-Type: application/json

        {
            "id": 4,
            "name": "SUPER-ADMIN"
        }
     */
    @PutMapping("api/v1/roles/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody RoleDto roleForm) {
        System.out.println("###### Delete Method ######");
        System.out.println("id = " + id);
        System.out.println("roleForm = " + roleForm);

        if (id.equals(roleForm.getId())) {
            Role role = modelMapper.map(roleForm, Role.class);
            repository.save(role);

            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(418).build();
        }
    }

    //DELETE http://localhost:8080/api/v1/roles/4
    @DeleteMapping("api/v1/roles/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        System.out.println("id = " + id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
