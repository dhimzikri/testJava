import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class TesEdit {
    public static void main(String[] args) {
            try {
                Connection connection = OracleDBConnection.getGlobalConnection();
                Properties props = new Properties();
                FileInputStream fis = new FileInputStream("prop_conf/db.properties");
                props.load(fis);
                fis.close();

                // Select price from date and isin
                String date = "2023-08-28"; // date
                String isin = "US3137B2TG58"; // ISIN ID
                // set Var Price
                double price = 0.0;

                try (PreparedStatement statPrice = connection.prepareStatement(props.getProperty("db.selPrice"))) {
                    statPrice.setString(1, date);
                    statPrice.setString(2, isin);
                    try (ResultSet resultSet = statPrice.executeQuery()) {
                        if (resultSet.next()) {
                            price = resultSet.getDouble("clean_price");
                        } else {
                            System.out.println("ISIN or Date not found.");
                            return;
                        }
                    }
                }

                //new_price +10%
                double newPrice = price + (0.1 * price); // Real price 85.433586

                // Update the price
                try (PreparedStatement updateStatement = connection.prepareStatement(props.getProperty("db.updPrice"))) {
                    updateStatement.setDouble(1, newPrice);
                    updateStatement.setString(2, date);
                    updateStatement.setString(3, isin);
                    int rowsAffected = updateStatement.executeUpdate();
                    if (rowsAffected > 0) {
                        System.out.println("Price updated successfully.");
                    } else {
                        System.out.println("Price update failed.");
                    }
                }
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
    }
}
