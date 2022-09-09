package se.lexicon.spring_bootjpalecture.dao;

import se.lexicon.spring_bootjpalecture.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Book save(Book book);

    Optional<Book> findById(int id);

    List<Book> findAll();

    void remove(Book book);

    Book update(Book book);
}
