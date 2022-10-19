package se.lexicon.spring_bootrestful_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.spring_bootrestful_api.dto.RoleFormDto;
import se.lexicon.spring_bootrestful_api.entity.Role;
import se.lexicon.spring_bootrestful_api.repository.RoleRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class RoleController {

    private final RoleRepository repository;


    @Autowired
    public RoleController(RoleRepository repository) {
        this.repository = repository;
    }

    //GET http://localhost:8080/api/v1/roles/
    @GetMapping("/api/v1/roles")
    public ResponseEntity<List<Role>> findAll() {

        System.out.println("###   Get Roles has been executed!   ###");

        List<Role> listOfRoles = repository.findAll();

//        return ResponseEntity.status(HttpStatus.OK).body(listOfRoles);
//        return ResponseEntity.status(200).body(listOfRoles);
        return ResponseEntity.ok(listOfRoles);
    }

    //GET http://localhost:8080/api/v1/roles/2
    //GET http://localhost:8080/api/v1/roles/99
    @GetMapping("api/v1/roles/{id}")
    public ResponseEntity<Role> findByRoleId(@PathVariable("id") Integer id) {
        Optional<Role> foundById = repository.findById(id);

//        if (foundById.isPresent()){
//            return ResponseEntity.ok(foundById.get());
//        }else {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        }

        return foundById.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());

    }

    //GET http://localhost:8080/api/v1/roles/search?name=ADMIN
    @GetMapping("api/v1/roles/search")
    public ResponseEntity<Role> findByRoleName(@RequestParam("name") String name) {
        Optional<Role> foundByName = repository.findByName(name);

//        if (foundByName.isPresent()){
//            return ResponseEntity.ok(foundByName.get());
//        }else {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        }
        return foundByName.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }

    /*
        PUT http://localhost:8080/api/v1/roles
        Content-Type: application/json

        {
            "name": "super-admin"
        }
    */
    @PostMapping("/api/v1/roles")
    public ResponseEntity<Role> create(@RequestBody RoleFormDto roleForm) {
        System.out.println("###### In Create method");

        Role role = new Role(roleForm.getId(), roleForm.getName());

        Role savedRole = repository.save(role);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRole);
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
    public ResponseEntity<Void> update(@PathVariable("id") Integer id, @RequestBody RoleFormDto roleForm) {
        System.out.println("###### Delete Method ######");
        System.out.println("id = " + id);
        System.out.println("roleForm = " + roleForm);

        if (id.equals(roleForm.getId())) {
            Role role = new Role(roleForm.getId(), roleForm.getName());
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
