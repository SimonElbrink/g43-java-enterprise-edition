package se.lexicon.spring_bootrestful_api.model.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CustomUserDto {

    private String username;
    private List<RoleDto> roles;

}
