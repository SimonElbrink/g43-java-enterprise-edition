package se.lexicon.jpaworkshoplibrary.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.lexicon.jpaworkshoplibrary.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {



}
