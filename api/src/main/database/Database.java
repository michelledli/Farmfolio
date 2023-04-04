import java.util.Properties;

/**
 * @author Niketa K.
 * @author Michelle Li
 */
public class Database {
    private Jdbi jdbi;
    private String createSchema = "CREATE SCHEMA farmfolio";
    private String createEntity = "CREATE TABLE ENTITY" + 
                                "(ID INT NOT NULL," +
                                "PRIMARY KEY (`ID`))";
    private String createFeature = "CREATE TABLE FEATURE" +  
                                "(ID INT NOT NULL AUTO_INCREMENT," + 
                                "FEATURE_KEY VARCHAR(45) NOT NULL," + 
                                "PRIMARY KEY (ID)," + 
                                "UNIQUE INDEX FEATURE_KEY_UNIQUE (FEATURE_KEY ASC) VISIBLE," + 
                                "UNIQUE INDEX FEATURE_LABEL_UNIQUE (FEATURE_LABEL ASC) VISIBLE)";
    private String createFeature_Type = "CREATE TABLE FEATURE_TYPE" + 
                                        "(ID INT NOT NULL AUTO_INCREMENT," + 
                                        "TYPE VARCHAR(45) NOT NULL," + 
                                        "PRIMARY KEY (ID))";
    private String createEntity_Feature = "CREATE TABLE ENTITY_FEATURE" +
                                        "ENTITY_ID INT NOT NULL," +
                                        "FEATURE_ID VARCHAR(45) NOT NULL," + 
                                        "FEATURE_TYPE_ID VARCHAR(45) NOT NULL" + 
                                        "DATA VARCHAR(45) NOT NULL," + 
                                        "PRIMARY KEY (ID)" + 
                                        "FOREIGN KEY (ENTITY_ID) REFERENCES ENTITY(ID)" + 
                                        "FOREIGN KEY (FEATURE_ID) REFERENCES FEATURE(ID)" + 
                                        "FOREIGN KEY (FEATURE_TYPE_ID) REFERENCES FEATURE_TYPE(ID))";
    private string dropTablesSql;

    /**
     * Constructor which creates the object, wrapping the functionality of java.sql.Driver.connect()
     * @param connectionUrl
     * @param properties
     */
    Database(String connectionUrl, Properties properties) {
        this.jdbi = Jdbi.create("jdbc:h2:mem:test");
    }

    /**
     * Perform an arbitrary query upon the given database
     * @param query
     */
    void performQuery(String query) {

    }

    /**
     * Drop all tables from H2 and create the schema defined below
     */
    void resetDatabase() {

    }
}
