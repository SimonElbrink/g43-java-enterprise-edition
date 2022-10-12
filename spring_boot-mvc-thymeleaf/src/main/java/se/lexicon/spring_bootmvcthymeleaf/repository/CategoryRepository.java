package se.lexicon.spring_bootmvcthymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.spring_bootmvcthymeleaf.model.entity.Category;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Optional<Category> findByName(String name);


}
