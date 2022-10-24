package se.lexicon.spring_bootrestful_api.model.dto;


import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleDto {

    private Integer id;

    @NotBlank
    @Size(min = 2, message = "Role must not be null")
    private String name;
}
