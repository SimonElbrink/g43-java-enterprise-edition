package se.lexicon.spring_bootrestful_api.model.dto;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "password")
public class UserDto {

    private String username; //martinchilling
    private String password; //12345
    private boolean expired; //false
    private List<String> roles; //[ {"id": 1,"name": "GUEST"} , {"id": 3,"name": "ADMIN"} ];

}
