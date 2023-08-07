import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class OracleDBConnection {
    private static Connection connection = null;

    public static Connection getGlobalConnection() {
        if (connection == null) {
            try {
                Properties props = new Properties();
                FileInputStream fis = new FileInputStream("prop_conf/db.properties");
                props.load(fis);

                String dbUrl = props.getProperty("urlDb.orc");
                String username = props.getProperty("userDb.orc");
                String password = props.getProperty("passDb.orc");


                connection = DriverManager.getConnection(dbUrl, username, password);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void closeGlobalConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
