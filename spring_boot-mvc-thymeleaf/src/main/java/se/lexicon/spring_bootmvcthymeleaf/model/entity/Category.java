package se.lexicon.spring_bootmvcthymeleaf.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String name;


    private LocalDate createDate = LocalDate.now();

    public Category(String name) {
        this.name = name;
    }
}
