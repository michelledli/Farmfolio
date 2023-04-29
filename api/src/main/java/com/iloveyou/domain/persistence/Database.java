package com.iloveyou.domain.persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.jdbi.v3.core.Handle;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Batch;

/**
 * @author Niketa K.
 * @author Michelle Li
 */
public class Database {
    private Jdbi jdbi;

    static final String JDBC_DRIVER = "org.h2.Driver";

    public static final String DB_URL = "jdbc:h2:file:./resources/db";
    private Connection connection = null; 
    private Statement statement = null; 
    
    public static final String CREATE_SCHEMA = "CREATE SCHEMA FARMFOLIO";
    
    public static final String CREATE_ENTITY = "CREATE TABLE FARMFOLIO.ENTITY " + 
                                "(ID INT NOT NULL," +
                                "PRIMARY KEY (ID))";
    
    public static final String CREATE_FEATURE_TYPE = "CREATE TABLE FARMFOLIO.FEATURE_TYPE " + 
                                "(ID INT NOT NULL AUTO_INCREMENT, " + 
                                "TYPE VARCHAR(45) NOT NULL, " + 
                                "PRIMARY KEY (ID))";
                        
    public static final String CREATE_FEATURE_KEY = "CREATE TABLE FARMFOLIO.FEATURE_KEY " +  
                                "(ID INT NOT NULL AUTO_INCREMENT, " + 
                                "KEY VARCHAR(64) NOT NULL, " + 
                                "LABEL VARCHAR(64) NOT NULL, " +
                                "FEATURE_TYPE_ID INT NOT NULL" +
                                "PRIMARY KEY (ID))" + 
                                "CREATE UNIQUE INDEX KEY_UNIQUE ON FARMFOLIO.FEATURE_KEY (KEY ASC);" + 
                                "CREATE UNIQUE INDEX LABEL_UNIQUE ON FARMFOLIO.FEATURE_KEY (LABEL ASC);";

    public static final String CREATE_FEATURE = "CREATE TABLE FARMFOLIO.FEATURE " +
                                        "(ID INT NOT NULL AUTO_INCREMENT, " +
                                        "ENTITY_ID INT NOT NULL, " +
                                        "FEATURE_KEY_ID VARCHAR(45) NOT NULL, " + 
                                        "DATA VARCHAR(45) NOT NULL, " + 
                                        "PRIMARY KEY (ID))" + 
                                        "FOREIGN KEY (ENTITY_ID) REFERENCES ENTITY(ID), " + 
                                        "FOREIGN KEY (FEATURE_KEY_ID) REFERENCES FEATURE_KEY(ID)";

    public static final String dropTablesSql = "DROP SCHEMA FARMFOLIO CASCADE";

    private Database() {}

    /**
     * Constructor which creates the Database object, wrapping the functionality of java.sql.Driver.connect()
     * @param connectionUrl
     */
    public Database(String url) {
        jdbi = Jdbi.create(url);
    }

    /**
     * Perform an arbitrary query upon the given database
     * @param query
     */
    public void performQuery(String statement) {
        this.jdbi.withHandle((Handle handle) -> { return handle.execute(statement); });
    }

    /**
     * Drop all tables from H2 and create the schema defined below
     */
    public void resetDatabase() {
        jdbi.withHandle((Handle handle) -> {
            handle.execute(dropTablesSql);            
            handle.execute(CREATE_SCHEMA);
            handle.execute(CREATE_ENTITY);
            handle.execute(CREATE_FEATURE_TYPE);
            handle.execute(CREATE_FEATURE_KEY);
            handle.execute(CREATE_FEATURE); 
            return null;
        });
    }

    public Jdbi getJDBI() {
        return jdbi;
    }
}