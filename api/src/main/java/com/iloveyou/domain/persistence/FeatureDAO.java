package com.iloveyou.domain.persistence;

import java.util.List;

import com.iloveyou.domain.Feature;

import org.jdbi.v3.core.Jdbi;

public class FeatureDAO implements IFeatureDAO{
    private Jdbi jdbi;

    public FeatureDAO(Database database) {
        this.jdbi = database.getJDBI();
    }

    @Override
    public int insert(Feature e) {
        return jdbi.withExtension(
            IFeatureDAO.class, 
            dao -> { return dao.insert(e); }
        );
    }

    @Override
    public Feature read(int id) {
        return jdbi.withExtension(
            IFeatureDAO.class,
            dao -> { return dao.read(id); }
        );
    }

    @Override
    public List<Feature> readAll() {
        return jdbi.withExtension(
            IFeatureDAO.class,
            dao -> { return dao.readAll(); }
        );
    }

    @Override
    public int delete(int id) {
        return jdbi.withExtension(
            IFeatureDAO.class,
            dao -> { return dao.delete(id); }
        );
    }
    
}
