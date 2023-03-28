package com.iloveyou.domain.persistence;

import java.util.Optional;
import java.util.List;
import com.iloveyou.domain.Entity;

public class EntityDAO implements DAO<Entity> {
    public void create(Entity t) {}
    // TODO: implement
    public Optional<Entity> read(long id) { return Optional.ofNullable(null); }
    // TODO: implement 
    public List<Entity> readAll() { return null; }
    public void update(Entity t) {}
    public void delete(long id) {}
}
