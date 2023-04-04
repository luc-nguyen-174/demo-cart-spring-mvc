package com.example.demoaddcartspringmvc.service;

import java.util.Optional;

public interface IGeneric<T> {
    Iterable<T> findAll();

    Optional<T> findById(Long id);

    void save(T t);

    void remove(Long id);
}
