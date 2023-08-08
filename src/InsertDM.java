import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
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
                dataRecords.add(new TesEPM("BondFN AL7707-MGFNAL7707/234M/05/01/2035/3.5%","US3132DMRB36", "92,743602", "2023-07-25"));
                dataRecords.add(new TesEPM("BondFN AL7707-MGFNAL7707/234M/05/01/2035/3.5%","US3132DMRB36", "92,743602", "2023-07-26"));
                dataRecords.add(new TesEPM("BondFN AL7707-MGFNAL7707/234M/05/01/2035/3.5%","US3132DMRB36", "92,743602", "2023-07-27"));
                dataRecords.add(new TesEPM("BondFN AL7707-MGFNAL7707/234M/05/01/2035/3.5%","US3132DMRB36", "92,743602", "2023-07-28"));
                dataRecords.add(new TesEPM("BondFN AL7707-MGFNAL7707/234M/05/01/2035/3.5%","US3132DMRB36", "92,743602", "2023-07-29"));

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