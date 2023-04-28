package com.iloveyou.domain;

import java.util.List;
import java.util.UUID;

public class Entity {
    int id;
    String name;

    public Entity() {}

    public Entity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ENTITY: [" + id + "," + name + "]";
    }
}
