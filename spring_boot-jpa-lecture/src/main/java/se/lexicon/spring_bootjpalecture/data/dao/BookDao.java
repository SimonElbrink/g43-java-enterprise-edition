package se.lexicon.spring_bootjpalecture.data.dao;

import se.lexicon.spring_bootjpalecture.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Book save(Book book);

    Optional<Book> findById(int id);

    List<Book> findAll();

    void remove(int bookId);
}
