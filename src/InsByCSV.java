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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
