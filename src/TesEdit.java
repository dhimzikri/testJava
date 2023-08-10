import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class TesEdit {
    public static void main(String[] args) {
            try {
                Connection connection = OracleDBConnection.getGlobalConnection();
                // Select price
                String selPrice = "SELECT clean_price FROM DM_TBL_TEST WHERE dates = ? and isin = ?";
                String date = "2023-08-05"; // date
                String isin = "US3140L6SU92"; // ISIN ID
                // set Var Price
                double price = 0.0;

                try (PreparedStatement statPrice = connection.prepareStatement(selPrice)) {
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
                String updateQuery = "UPDATE DM_TBL_TEST SET clean_price = ? WHERE dates = ? and isin = ? ";
                try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
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
            }
        }
}
