import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * @author Niketa K.
 * @author Michelle Li
 */
public class Database {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:mem:farmfolio";
    private Connection connection = null; 
    private Statement statement = null; 
    
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
    private String dropTablesSql;

    /**
     * Constructor which creates the Database object, wrapping the functionality of java.sql.Driver.connect()
     * @param connectionUrl
     */
    Database(String connectionUrl) {
        try {
        Class.forName(JDBC_DRIVER);
        System.out.println("Connecting to database..."); 
        this.connection = DriverManager.getConnection(connectionUrl);
        this.statement = connection.createStatement();
        } catch (Exception e) {
            System.err.println("Failed to connect to database.\nError: "+e.getMessage());
        }
        System.out.println("Connection successful...");
    }

    /**
     * Default constructor for the Database object. Creates a database instance using a default in memory database url.
     */
    Database() {
        try {
            Class.forName(JDBC_DRIVER);
            System.out.println("Connecting to local database..."); 
            this.connection = DriverManager.getConnection(DB_URL);
            this.statement = connection.createStatement();
            } catch (Exception e) {
                System.err.println("Failed to connect to local database.\nError: "+e.getMessage());
            }
            System.out.println("Connection successful...");
    }

    /**
     * Simple method to check if the Database has been connected to.
     * @return
     */
    boolean isConnected() {
        return (this.connection != null) ? true : false;
    }

    /**
     * Perform an arbitrary query upon the given database
     * @param query
     */
    void performQuery(String query) throws SQLException {
        this.statement.executeQuery(query);
    }

    /**
     * Drop all tables from H2 and create the schema defined below
     */
    void resetDatabase() {
        // TODO
    }
}