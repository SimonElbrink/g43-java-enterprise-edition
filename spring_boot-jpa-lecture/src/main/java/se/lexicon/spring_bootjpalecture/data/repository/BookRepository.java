package se.lexicon.spring_bootjpalecture.data.repository;

import org.springframework.data.repository.CrudRepository;
import se.lexicon.spring_bootjpalecture.entity.Book;

public interface BookRepository extends CrudRepository<Book,Integer> {
}
