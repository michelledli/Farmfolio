package com.iloveyou.domain.service;

import com.iloveyou.domain.persistence.DAO;

public abstract class Service<T> {
    private DAO<T> dao;

    Service(DAO<T> dao) {
        this.dao = dao;
    }

    public abstract boolean isValid(T t);
}
