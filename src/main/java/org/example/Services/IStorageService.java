package org.example.Services;

import org.example.Entities.IEntity;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public interface IStorageService<T extends IEntity> {
    void save(T entity);
    void delete(UUID id);
    T findById(UUID id);
    List<T> findAllBy(Predicate<T> predicate);
}
