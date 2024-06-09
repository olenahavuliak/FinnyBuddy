package com.example.finnybuddy.core.utils;

import java.util.List;

public interface BasicCrudOperations <E, T>{
    E getById(T id);
    List<E> getAll();
    void deleteById(T id);
    E update(T id, E entity);
    E create(E entity);
}
