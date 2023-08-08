import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class FetchDM {

    public static void main(String[] args) {
        try {
            Connection connection = OracleDBConnection.getGlobalConnection();
//            List<AssetDef> listAssetDef = new ArrayList<>();
            List<TesEPM> listEPM = new ArrayList<>();
            if (connection != null) {
                System.out.println("Connected to Oracle database!");

                // Read properties
                Properties props = new Properties();
                FileInputStream fis = new FileInputStream("prop_conf/db.properties");
                props.load(fis);
                String querySelect = props.getProperty("db.querySelect");

                // Execute SELECT query
                try (Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(querySelect)) {

                    // Process query results
                    while (resultSet.next()) {
                        TesEPM tesEPM = new TesEPM("", "", "","");
                        tesEPM.setBOOK(resultSet.getString("BOOK"));
                        tesEPM.setISIN(resultSet.getString("ISIN"));
                        tesEPM.setCLEAN_PRICE(resultSet.getString("CLEAN_PRICE"));
                        tesEPM.setDATES(resultSet.getString("DATES"));
                        System.out.println(tesEPM);
                        listEPM.add(tesEPM);
                    }
                }
                connection.close();
            }


//            List<AssetDef> listPriceUn97 = new ArrayList<>();
//            listPriceUn97 = listAssetDef.stream().filter(p -> p.getPRICE()<96).collect(Collectors.toList());
//            for (AssetDef AssetU97:listPriceUn97
//            ) {
//                System.out.println(AssetU97.toString());
//            }

//            List<AssetDef> listAusBond = new ArrayList<>();
//            listAusBond = listAssetDef.stream().filter(o -> o.getInst_Type().equals("Austrian Bond")).collect(Collectors.toList());
//            for (AssetDef AusBon:listAusBond
//                 ) {
//                System.out.println(AusBon.toString());
//            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
            // Handle connection and query errors here
            System.out.println("Duh");
        }
    }
}
