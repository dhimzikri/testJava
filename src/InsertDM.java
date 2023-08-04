import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class InsertDM {
    public static Connection getConnection() throws SQLException, IOException {
        try {
            Class.forName("oracle.jdbc.OracleDriver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Oracle JDBC Driver not found. Include it in the classpath.", e);
        }

        Properties props = new Properties();
        FileInputStream fis = new FileInputStream("prop_conf/db.properties");
        props.load(fis);

        String url = props.getProperty("urlDb.orc");
        String username = props.getProperty("userDb.orc");
        String password = props.getProperty("passDb.orc");

        return DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) {
        try {
            Connection connection = getConnection();
            List<AssetDef> listAssetDef = new ArrayList<>();
            if (connection != null) {
                System.out.println("Connected to Oracle database!");

                // Read properties
                Properties props = new Properties();
                FileInputStream fis = new FileInputStream("prop_conf/db.properties");
                props.load(fis);
                String querySelect = props.getProperty("db.query");

                // Sample data
                ArrayList<TesEPM> dataRecords = new ArrayList<>();
                dataRecords.add(new TesEPM());
                dataRecords.add(new TesEPM());
                dataRecords.add(new TesEPM());
                dataRecords.add(new TesEPM());
                dataRecords.add(new TesEPM());
                dataRecords.add(new TesEPM());
                dataRecords.add(new TesEPM());
                dataRecords.add(new TesEPM());

                // SQL INSERT statement
                String queryInsert = props.getProperty("db.queryInsert");

                try (PreparedStatement ptms = connection.prepareStatement(queryInsert)) {
                    for (TesEPM record : dataRecords) {
                        ptms.setString(1, record.getBOOK());
                        ptms.setString(2, record.getISIN());
                        ptms.setString(3, String.valueOf(record.getCLEAN_PRICE()));
                        ptms.executeUpdate();
                    }
                    ptms.close();
                    connection.close();

                    System.out.println("Data insertion completed successfully!");
                } catch (SQLException e) {
                    e.printStackTrace();

                }
                connection.close();
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            // Handle connection and query errors here
            System.out.println("Duh");
        }
    }
}
