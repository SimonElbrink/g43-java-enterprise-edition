package se.lexicon.spring_bootrestful_api.model.dto;

import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "password")
public class UserDto {

    @NotBlank
    @Size(min = 2, max = 26, message = "Must between 2 and 26 character.")
    private String username; //martinchilling

    @NotBlank
    @Size(min = 8, max = 60, message = "Must be more the 8 character.")
//    @Pattern("Password restrictions")
    private String password; //12345
    private boolean expired; //false

    @NonNull
    @Valid // Activate Validation in RoleDto (IF you want to have it validated)
    private List<RoleDto> roles; //[ {"id": 1,"name": "GUEST"} , {"id": 3,"name": "ADMIN"} ];

}
