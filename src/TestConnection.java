import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class TestConnection {

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
                        tesAssetDef.setPRICE(resultSet.getDouble("PRICE"));
                        System.out.println(tesAssetDef.toString());
                        listAssetDef.add(tesAssetDef);
                    }

                }
                connection.close();
            }
            List<AssetDef> listPriceUn97 = new ArrayList<>();
            listPriceUn97 = listAssetDef.stream().filter(p -> p.getPRICE()<96).collect(Collectors.toList());
            for (AssetDef AssetU97:listPriceUn97
                 ) {
                System.out.println(AssetU97.toString());
            }

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
