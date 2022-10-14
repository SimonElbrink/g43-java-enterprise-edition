package se.lexicon.spring_bootmvcthymeleaf.converter;

import java.util.Collection;

public interface Converter<E, V, F> {

    V toView(E entity);


    E toEntity(F Form);

    Collection<V> toViews(Collection<E> entities);

    Collection<E> toEntities(Collection<F> form);


}
