package se.lexicon.jpaworkshoplibrary.data;

import se.lexicon.jpaworkshoplibrary.entity.Author;

import java.util.Collection;

public interface AuthorDao {

    Author create(Author author);

    Author findById(int id);

    Collection<Author> findAll();

    Author update(Author author);

    void delete(int id);
}
