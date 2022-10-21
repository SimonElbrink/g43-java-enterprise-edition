package se.lexicon.spring_bootrestful_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.lexicon.spring_bootrestful_api.model.dto.RoleDto;
import se.lexicon.spring_bootrestful_api.service.RoleService;

import java.util.List;

@RestController
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {

        this.roleService = roleService;
    }

    //GET http://localhost:8080/api/v1/roles/
    @GetMapping("/api/v1/roles")
    public ResponseEntity<List<RoleDto>> findAll() {
        System.out.println("###   Get Roles has been executed!   ###");
        return ResponseEntity.ok(roleService.findAll());

//        return ResponseEntity.status(HttpStatus.OK).body(listOfDto);
//        return ResponseEntity.status(200).body(listOfDto);
    }

    //GET http://localhost:8080/api/v1/roles/2
    //GET http://localhost:8080/api/v1/roles/99
    @GetMapping("api/v1/roles/{id}")
    public ResponseEntity<RoleDto> findByRoleId(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(roleService.findById(id));
    }

    //GET http://localhost:8080/api/v1/roles/search?name=ADMIN
    @GetMapping("api/v1/roles/search")
    public ResponseEntity<RoleDto> findByRoleName(@RequestParam("name") String name) {
        return ResponseEntity.ok(roleService.findByName(name));
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
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.create(roleForm));
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

        roleService.update(roleForm, id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

    //DELETE http://localhost:8080/api/v1/roles/4
    @DeleteMapping("api/v1/roles/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id) {
        System.out.println("id = " + id);
        roleService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}