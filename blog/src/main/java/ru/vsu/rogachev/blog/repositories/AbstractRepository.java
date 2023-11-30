package ru.vsu.rogachev.blog.repositories;

import java.util.List;

public interface AbstractRepository<T, R> {
    List<T> getAll();
    void add(T object);
    T getById(R id);
    void removeById(R id);
    void update(R id, T newObject);
}