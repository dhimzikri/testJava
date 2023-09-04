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
            List<AttrFetchDM> listEPM = new ArrayList<>();
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
                        AttrFetchDM attrFetchDM = new AttrFetchDM();
                        attrFetchDM.setPOSITIONS(resultSet.getString("POSITIONS"));
                        attrFetchDM.setBOOK(resultSet.getString("BOOK"));
                        attrFetchDM.setISIN(resultSet.getString("ISIN"));
                        attrFetchDM.setPRODUCT_TYPE(resultSet.getString("PRODUCT_TYPE"));
                        attrFetchDM.setPRODUCT_SUBTYPE(resultSet.getString("PRODUCT_SUBTYPE"));
                        attrFetchDM.setCUSIP(resultSet.getString("CUSIP"));
                        attrFetchDM.setFIGI(resultSet.getString("FIGI"));
                        attrFetchDM.setCLEAN_PRICE(resultSet.getDouble("CLEAN_PRICE"));
                        System.out.println(attrFetchDM.toString());
                        listEPM.add(attrFetchDM);
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
