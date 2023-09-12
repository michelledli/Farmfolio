package com.iloveyou.domain.persistence;

import com.iloveyou.domain.Feature;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

public interface IFeatureDAO {
    @SqlUpdate("INSERT INTO FARMFOLIO.FEATURE VALUES (:id, :entity_id, :feature_key, :data)")
    int insert(@BindBean Feature e);

    @SqlQuery("SELECT * FROM FARMFOLIO.FEATURE WHERE id = ?")
    @RegisterBeanMapper(Feature.class)
    Feature read(int id);
    
    @SqlQuery("SELECT * FROM FARMFOLIO.FEATURE")
    @RegisterBeanMapper(Feature.class)
    List<Feature> readAll();

    @SqlUpdate("DELETE FROM FARMFOLIO.FEATURE WHERE id = ?")
    int delete(int id);
}
