import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Properties;

public class InsByCSV {
    public static void main(String[] args) {
        try {
            Connection connection = OracleDBConnection.getGlobalConnection();
            Properties props = new Properties();
            FileInputStream fis = new FileInputStream("prop_conf/db.properties");
            props.load(fis);

            try {
                String csvFilePath = props.getProperty("csvFilePath");
                BufferedReader br = new BufferedReader(new FileReader(csvFilePath));
                String line;

                String insCSV = props.getProperty("insCSV");
                PreparedStatement ptms = connection.prepareStatement(insCSV);
                br.readLine();

                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    ptms.setString(1, data[0]); //position
                    ptms.setString(2, data[1]); //book
                    ptms.setString(3, data[2]); //ProductType
                    ptms.setString(4, data[3]); //ProductSubType
                    ptms.setString(5, data[4]); //ISIN
                    ptms.setString(6, data[5]); //CUSIP
                    ptms.setString(7, data[6]); //FIGI
                    ptms.setDouble(8, Double.parseDouble(data[7]));//Clean_price
                    ptms.executeUpdate();
                }
                br.close();
                ptms.close();
                connection.close();

                System.out.println("Data inserted!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
