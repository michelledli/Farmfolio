package com.iloveyou.domain.persistence;

import java.util.List;

import com.iloveyou.domain.Entity;

import org.jdbi.v3.core.Jdbi;

public class EntityDAO implements IEntityDAO {
    private Jdbi jdbi;

    public EntityDAO(Database database) {
        this.jdbi = database.getJDBI();
    }

    public int insert(Entity e) {
        return jdbi.withExtension(
            IEntityDAO.class, 
            dao -> { return dao.insert(e); }
        );
    }

    public Entity read(int id) { 
        return jdbi.withExtension(
            IEntityDAO.class,
            dao -> { return dao.read(id); }
        );
    }

    public List<Entity> readAll() {
        return jdbi.withExtension(
            IEntityDAO.class,
            dao -> { return dao.readAll(); }
        );
    }


    public int delete(int id){
        return jdbi.withExtension(
            IEntityDAO.class,
            dao -> { return dao.delete(id); }
        );
    }
}
