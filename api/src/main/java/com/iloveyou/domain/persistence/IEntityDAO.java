package com.iloveyou.domain.persistence;

import com.iloveyou.domain.Entity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface IEntityDAO {
    @SqlUpdate("INSERT INTO FARMFOLIO.ENTITY VALUES (:id, :name)")
    int insert(@BindBean Entity e);

    @SqlQuery("SELECT * FROM FARMFOLIO.ENTITY WHERE id = ?")
    @RegisterBeanMapper(Entity.class)
    Entity read(int id);
    
    @SqlQuery("SELECT * FROM FARMFOLIO.ENTITY")
    @RegisterBeanMapper(Entity.class)
    List<Entity> readAll();
    
    // void update(Entity t);
    
    @SqlUpdate("DELETE FROM FARMFOLIO.ENTITY WHERE id = ?")
    int delete(int id);
}
