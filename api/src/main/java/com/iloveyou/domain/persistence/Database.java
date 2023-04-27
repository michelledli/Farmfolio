package com.iloveyou.domain.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Batch;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

/**
 * @author Niketa K.
 * @author Michelle Li
 */
public class Database {
    private Jdbi jdbi;

    public static final String DB_URL = "jdbc:h2:file:./resources/db";

    public static final String CREATE_SCHEMA = "CREATE SCHEMA FARMFOLIO";
    
    public static final String CREATE_ENTITY = "CREATE TABLE FARMFOLIO.ENTITY " + 
                                "(ID INT NOT NULL," +
                                "NAME VARCHAR(64)," +
                                "PRIMARY KEY (ID))";
    
    public static final String CREATE_FEATURE = "CREATE TABLE FARMFOLIO.FEATURE " +  
                                "(ID INT NOT NULL AUTO_INCREMENT, " + 
                                "FEATURE_KEY VARCHAR(64) NOT NULL, " + 
                                "FEATURE_LABEL VARCHAR(64) NOT NULL, " +
                                "PRIMARY KEY (ID));" + 
                                "CREATE UNIQUE INDEX FEATURE_KEY_UNIQUE ON FARMFOLIO.FEATURE (FEATURE_KEY ASC);" + 
                                "CREATE UNIQUE INDEX FEATURE_LABEL_UNIQUE ON FARMFOLIO.FEATURE (FEATURE_LABEL ASC);";

    public static final String CREATE_FEATURE_TYPE = "CREATE TABLE FARMFOLIO.FEATURE_TYPE " + 
                                        "(ID INT NOT NULL AUTO_INCREMENT, " + 
                                        "TYPE VARCHAR(45) NOT NULL, " + 
                                        "PRIMARY KEY (ID))";

    public static final String CREATE_ENTITY_FEATURE = "CREATE TABLE FARMFOLIO.ENTITY_FEATURE " +
                                        "(ID INT NOT NULL AUTO_INCREMENT, " +
                                        "ENTITY_ID INT NOT NULL, " +
                                        "FEATURE_ID VARCHAR(45) NOT NULL, " + 
                                        "FEATURE_TYPE_ID VARCHAR(45) NOT NULL, " + 
                                        "DATA VARCHAR(45) NOT NULL, " + 
                                        "PRIMARY KEY (ID), " + 
                                        "FOREIGN KEY (ENTITY_ID) REFERENCES ENTITY(ID), " + 
                                        "FOREIGN KEY (FEATURE_ID) REFERENCES FEATURE(ID), " + 
                                        "FOREIGN KEY (FEATURE_TYPE_ID) REFERENCES FEATURE_TYPE(ID))";

    public static final String DROP_SCHEMA = "DROP SCHEMA FARMFOLIO CASCADE";

    private Database() {}

    /**
     * Constructor which creates the Database object, wrapping the functionality of java.sql.Driver.connect()
     */
    public Database(String url) {
        jdbi = Jdbi.create(url);
        jdbi.installPlugin(new SqlObjectPlugin());
    }

    /**
     * Perform an arbitrary query upon the given database
     */
    public void performQuery(String statement) {
        this.jdbi.withHandle((Handle handle) -> { return handle.execute(statement); });
    }

    /**
     * Drop all tables from H2 and create the schema defined below
     */
    public void resetDatabase() {
        jdbi.withHandle((Handle handle) -> {
            handle.execute(DROP_SCHEMA);            
            handle.execute(CREATE_SCHEMA);
            handle.execute(CREATE_ENTITY);
            handle.execute(CREATE_FEATURE); 
            handle.execute(CREATE_FEATURE_TYPE);
            handle.execute(CREATE_ENTITY_FEATURE);

            return null;
        });
    }

    public Jdbi getJDBI() {
        return jdbi;
    }
}