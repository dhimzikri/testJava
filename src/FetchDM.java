import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

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
                        TesEPM tesEPM = new TesEPM("TCW-USAGENCYMBS", "US36179S5L78", "93,590235", "2023-06-05");
                        tesEPM.setPOSITIONS(resultSet.getString("POSITIONS"));
                        tesEPM.setBOOK(resultSet.getString("BOOK"));
                        tesEPM.setISIN(resultSet.getString("ISIN"));
                        tesEPM.setPRODUCT_TYPE(resultSet.getString("PRODUCT_TYPE"));
                        tesEPM.setPRODUCT_SUBTYPE(resultSet.getString("PRODUCT_SUBTYPE"));
                        tesEPM.setCUSIP(resultSet.getString("CUSIP"));
                        tesEPM.setFIGI(resultSet.getString("FIGI"));
                        tesEPM.setCLEAN_PRICE(resultSet.getDouble("CLEAN_PRICE"));
                        System.out.println(tesEPM.toString());
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
