import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

public class TesEdit {
    public static void main(String[] args) {
            try {
                // Establishing database connection
                Connection connection = OracleDBConnection.getGlobalConnection();

                // Select the price
                String selectQuery = "SELECT clean_price FROM DM_TBL_TEST WHERE dates = ? and isin = ?";
                String date = "2023-07-21"; // Replace with the actual date
                String isin = "US3138Y4BK43"; // Replace with the actual ISIN ID
                double price = 0.0;

                try (PreparedStatement selectStatement = connection.prepareStatement(selectQuery)) {
                    selectStatement.setString(1, date);
                    selectStatement.setString(2, isin);
                    try (ResultSet resultSet = selectStatement.executeQuery()) {
                        if (resultSet.next()) {
                            price = resultSet.getDouble("clean_price");
                        } else {
                            System.out.println("Book not found.");
                            return;
                        }
                    }
                }

                // Calculate new price
                double newPrice = price + (0.1 * price);

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

                // Closing the connection
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
}
