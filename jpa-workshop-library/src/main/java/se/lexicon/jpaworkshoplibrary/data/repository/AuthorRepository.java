package se.lexicon.jpaworkshoplibrary.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.jpaworkshoplibrary.entity.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
