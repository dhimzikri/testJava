import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class FetchDM {
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
                        TesEPM tesEPM = new TesEPM();
                        tesEPM.setBOOK(resultSet.getString("BOOK"));
                        tesEPM.setISIN(resultSet.getString("ISIN"));
                        tesEPM.setCLEAN_PRICE(resultSet.getString("CLEAN_PRICE"));
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
