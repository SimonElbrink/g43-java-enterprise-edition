package se.lexicon.jpaworkshoplibrary.data;

import se.lexicon.jpaworkshoplibrary.entity.Book;

import java.util.Collection;

public interface BookDao {

    Book create(Book book);

    Book findById(int id);

    Collection<Book> findAll();

    Book update(Book book);

    void delete(int id);

}
