package se.lexicon.jpaworkshoplibrary.data;

import se.lexicon.jpaworkshoplibrary.entity.Details;

import java.util.Collection;

public interface DetailsDao {

    Details create(Details details);
    Details findById(int id);
    Collection<Details> findAll();
    Details update(Details details);
    void delete(int id);
}
