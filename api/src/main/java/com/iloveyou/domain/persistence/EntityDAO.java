package com.iloveyou.domain.persistence;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

import com.iloveyou.domain.Entity;

public class EntityDAO {
    public void create(Entity t) {}
    public Optional<Entity> read(UUID id) { return Optional.ofNullable(null); }
    public List<Entity> readAll() { return null; }
    public void update(Entity t) {}
    public void delete(UUID id) {}
}
