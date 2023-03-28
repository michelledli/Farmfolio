package com.iloveyou.domain.persistence;

import java.util.Optional;
import java.util.List;

public interface DAO<T> {
    void create(T t);
    Optional<T> read(long id);
    List<T> readAll();
    void update(T t);
    void delete(long id);
}