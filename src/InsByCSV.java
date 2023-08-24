import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class InsByCSV {
    public static void main(String[] args) {
        try {
            Connection connection = OracleDBConnection.getGlobalConnection();
            String csvFilePath = "C:\\\\Users\\\\ptap2\\\\Downloads\\\\Book1.csv";
            BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
            String line;

            String insCSV = "INSERT INTO dm_tbl_test (BOOK, ISIN, CLEAN_PRICE, DATES) VALUES (?, ?, ?, ?)";
            PreparedStatement ptms = connection.prepareStatement(insCSV);
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                ptms.setString(1, data[0]);
                ptms.setString(2, data[1]);
                ptms.setDouble(3, Double.parseDouble(data[2]));
                ptms.setString(4, data[3]);
                ptms.executeUpdate();
            }
            br.close();
            ptms.close();
            connection.close();

            System.out.println("Data inserted!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
