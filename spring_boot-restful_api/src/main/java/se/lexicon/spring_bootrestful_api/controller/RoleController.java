package se.lexicon.spring_bootrestful_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<List<Role>> findAll(){

        System.out.println("###   Get Roles has been executed!   ###");

        List<Role> listOfRoles = repository.findAll();

        return ResponseEntity.ok(listOfRoles);
    }

    //GET http://localhost:8080/api/v1/roles/2
    //GET http://localhost:8080/api/v1/roles/99
    @GetMapping("api/v1/roles/{id}")
    public ResponseEntity<Role> findByRoleId(@PathVariable("id") Integer id){
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
    public ResponseEntity<Role> findByRoleName(@RequestParam("name") String name){
        Optional<Role> foundByName = repository.findByName(name);

//        if (foundByName.isPresent()){
//            return ResponseEntity.ok(foundByName.get());
//        }else {
//            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//        }
        return foundByName.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());
    }


}
