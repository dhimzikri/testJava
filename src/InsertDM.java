import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class InsertDM {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement ptms = null;
        FileInputStream fis = null;

        try {
            connection = OracleDBConnection.getGlobalConnection();
            if (connection != null) {
                System.out.println("Connected to the Oracle database!");

                // Read properties
                Properties props = new Properties();
                fis = new FileInputStream("prop_conf/db.properties");
                props.load(fis);

                // Sample data
                List<AttrSingleInsDM> dataRecords = new ArrayList<>();
                dataRecords.add(new AttrSingleInsDM(
                        "sef",
                        "sef",
                        "sf",
                        "sef",
                        "sfsef",
                        "sefsef",
                        "sefsef",
                        "2374687"));

                // SQL INSERT statement
                String queryInsert = props.getProperty("db.queryInsert");

                ptms = connection.prepareStatement(queryInsert);

                for (AttrSingleInsDM record : dataRecords) {
                    ptms.setString(1, record.getPOSITIONS());
                    ptms.setString(2, record.getBOOK());
                    ptms.setString(3, record.getISIN());
                    ptms.setString(4, record.getPRODUCT_TYPE());
                    ptms.setString(5, record.getPRODUCT_SUBTYPE());
                    ptms.setString(6, record.getCUSIP());
                    ptms.setString(7, record.getFIGI());
                    ptms.setDouble(8, record.getCLEAN_PRICE());

                    ptms.executeUpdate();
                }

                System.out.println("Data insertion completed successfully!");
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            try {
                if (ptms != null) {
                    ptms.close();
                }
                if (fis != null) {
                    fis.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException | IOException e) {
                e.printStackTrace();
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
