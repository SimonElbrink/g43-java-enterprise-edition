package se.lexicon.spring_bootrestful_api.service;

import se.lexicon.spring_bootrestful_api.model.dto.RoleDto;

import java.util.List;

public interface RoleService {

    RoleDto create (RoleDto Form);
    void update(RoleDto form, Integer id);
    void deleteById(Integer id);
    List<RoleDto> findAll();
    RoleDto findById(Integer id);
    RoleDto findByName(String name);


}
