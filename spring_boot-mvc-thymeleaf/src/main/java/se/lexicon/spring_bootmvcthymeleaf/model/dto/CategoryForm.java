package se.lexicon.spring_bootmvcthymeleaf.model.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryForm {

    @NotBlank(message = "category name must not be empty")
    @Size(min = 2, max = 40, message = "category name must be between 2 and 40 characters")
    private String name;
}
