package hu.schonhertz.training.blog.dao.impl;

import java.io.Serializable;
import java.util.List;

import hu.schonhertz.training.blog.entity.BaseEntity;

public interface BaseConvertDao<E extends BaseEntity, D extends Serializable> {

    E toEntity(D dto, E entity);

    D toDto(E entity);

    Long saveEntity(E entity) throws Exception;

    void updateEntity(E entity) throws Exception;

    void deleteEntity(Long id) throws Exception;

    E findEntity(Long id) throws Exception;

    List<E> findAllEntity() throws Exception;
}
