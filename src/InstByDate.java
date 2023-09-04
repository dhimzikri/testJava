import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Properties;

public class InstByDate {
    public static void main(String[] args) {
        try {
            Connection connection = OracleDBConnection.getGlobalConnection();
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream("prop_conf/db.properties");
            props.load(fis);

            ArrayList<AttrFetchDM> dataRecords = new ArrayList<>();
//            dataRecords.add(new AttrFetchDM("TesDate", "DateNow", "92,743602", ""));
//            dataRecords.add(new AttrFetchDM("BondFN AL7707-MGFNAL7707/234M/05/01/2035/3.5%", "US3132DMRB36", "92,743602", "2023-07-26"));
//            dataRecords.add(new AttrFetchDM("BondFN AL7707-MGFNAL7707/234M/05/01/2035/3.5%", "US3132DMRB36", "92,743602", "2023-07-27"));
//            dataRecords.add(new AttrFetchDM("BondFN AL7707-MGFNAL7707/234M/05/01/2035/3.5%", "US3132DMRB36", "92,743602", "2023-07-28"));
//            dataRecords.add(new AttrFetchDM("BondFN AL7707-MGFNAL7707/234M/05/01/2035/3.5%", "US3132DMRB36", "92,743602", "2023-07-29"));

            String insert = props.getProperty("db.queryInsert");
            LocalDate currentDate = LocalDate.now();
            try (
                    PreparedStatement ptms = connection.prepareStatement(insert)) {
                for (AttrFetchDM record : dataRecords) {
                    ptms.setString(1, record.getBOOK());
                    ptms.setString(2, record.getISIN());
                    ptms.setString(3, String.valueOf(record.getCLEAN_PRICE()));
                    ptms.setDate(4, java.sql.Date.valueOf(currentDate));
                    ptms.executeUpdate();
                }
                ptms.close();
                connection.close();

                System.out.println("Data insertion completed successfully!");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
