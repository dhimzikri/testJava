import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class TesEdit {
    public static void main(String[] args) {
        try {
            Connection connection = OracleDBConnection.getGlobalConnection();
            if (connection != null) {
                System.out.println("Connected to Oracle database!");
                Properties prop = new Properties();
                FileInputStream fis = new FileInputStream("prop_conf/db.properties");
                prop.load(fis);
                String updateQuery = prop.getProperty("db.udpatePrice");
                try {
                    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                    String newValue = "34,7834"; //Real price 92,743602
                    String tgl = "2023-07-25";

                    preparedStatement.setString(1, newValue);
                    preparedStatement.setString(2, tgl);
                    int rowsUpdated = preparedStatement.executeUpdate();
                    System.out.println(rowsUpdated + " rows updated.");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
