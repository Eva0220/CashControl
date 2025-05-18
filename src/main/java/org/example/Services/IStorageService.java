package org.example.Services;

import java.util.List;
import java.util.UUID;
import java.util.function.Predicate;

public interface IStorageService<T> {
    void save(UUID id, T entity);
    void delete(UUID id);
    T findById(UUID id);
    List<T> findAllBy(Predicate<T> predicate);
}
