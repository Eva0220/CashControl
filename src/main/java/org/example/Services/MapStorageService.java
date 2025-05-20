package org.example.Services;

import org.example.Entities.IEntity;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Component
public class MapStorageService<T extends IEntity> implements IStorageService<T> {
    private final Map<UUID, T> items = new HashMap<>();

    @Override
    public void save(T object) {
        items.put(object.getId(), object);
    }

    @Override
    public T findById(UUID id) {
        return items.get(id);
    }

    @Override
    public void delete(UUID id) {
        items.remove(id);
    }

    @Override
    public List<T> findAllBy(Predicate<T> predicate) {
        return items.values().stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
