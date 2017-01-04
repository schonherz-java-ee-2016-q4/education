package hu.schonherz.training.springmvcexample.data.dao;

import java.util.Collection;

public interface GenericCrudDAO<E, ID> {
    Collection<E> findAll();
    E findById(ID id);
    E save(E entity);
    boolean remove(ID id);
}
