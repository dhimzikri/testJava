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
        try {
            Connection connection = OracleDBConnection.getGlobalConnection();
            List<AssetDef> listAssetDef = new ArrayList<>();
            if (connection != null) {
                System.out.println("Connected to Oracle database!");

                // Read properties
                Properties props = new Properties();
                FileInputStream fis = new FileInputStream("prop_conf/db.properties");
                props.load(fis);

                // Sample data
                ArrayList<TesEPM> dataRecords = new ArrayList<>();
                dataRecords.add(new TesEPM("TCW-USAGENCYMBS","US36179S5L78", "93,590235", "2023-06-05"));
                dataRecords.add(new TesEPM("TCW-USAGENCYMBS","US36179R4E62", "93,70233", "2023-06-14"));
                dataRecords.add(new TesEPM("TCW-USAGENCYMBS","US31418DHY13", "87,465861", "2023-06-25"));
                dataRecords.add(new TesEPM("TCW-USAGENCYMBS","US36179SWY98", "95,928859", "2023-06-12"));
                dataRecords.add(new TesEPM("TCW-USAGENCYMBS","US36179T7L33", "97,7624", "2023-06-09"));

                // SQL INSERT statement
                String queryInsert = props.getProperty("db.queryInsert");

                try (
                        PreparedStatement ptms = connection.prepareStatement(queryInsert)) {
                    for (TesEPM record : dataRecords) {
                        ptms.setString(1, record.getBOOK());
                        ptms.setString(2, record.getISIN());
                        ptms.setString(3, String.valueOf(record.getCLEAN_PRICE()));
                        ptms.setString(4, record.getDATES());
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