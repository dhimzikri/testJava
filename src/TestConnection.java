import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class TestConnection {

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
                String querySelect = props.getProperty("db.query");

                try (Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(querySelect)) {

                    // Process query results
                    while (resultSet.next()) {
                        AssetDef tesAssetDef = new AssetDef();
                        tesAssetDef.setAssetID(resultSet.getString("asset_id"));
                        tesAssetDef.setAsset_Name(resultSet.getString("asset_name"));
                        tesAssetDef.setCurrency(resultSet.getString("currency"));
                        tesAssetDef.setInst_Type(resultSet.getString("inst_type"));
                        tesAssetDef.setTimeToMature(resultSet.getDouble("TIME_TO_MATURITY"));
                        tesAssetDef.setGET_DATE(resultSet.getString("get_date"));
                        tesAssetDef.setPRICE(resultSet.getDouble("PRICE"));
                        System.out.println(tesAssetDef.toString());
                        listAssetDef.add(tesAssetDef);
                    }

                }
                connection.close();
            }

//            List<AssetDef> listPriceUn97 = new ArrayList<>();
//            listPriceUn97 = listAssetDef.stream().filter(p -> p.getPRICE()<96).collect(Collectors.toList());
//            for (AssetDef AssetU97:listPriceUn97
//                 ) {
//                System.out.println(AssetU97.toString());
//            }

//            List<AssetDef> listAusBond = new ArrayList<>();
//            listAusBond = listAssetDef.stream().filter(o -> o.getInst_Type().equals("Yankee Bond")).collect(Collectors.toList());
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
