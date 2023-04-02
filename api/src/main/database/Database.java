import java.util.Properties;

/**
 * @author Niketa K.
 */
public class Database {
    private Jdbi jdbi;

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
