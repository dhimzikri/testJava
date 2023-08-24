import com.opencsv.CSVReader;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

public class ReadCSV {
    public static void main(String[] args) {
        try {
            // Load properties from the file
            Properties properties = new Properties();
            FileInputStream fis = new FileInputStream("prop_conf/db.properties");
            properties.load(fis);
            fis.close();

            // Get the CSV file path from the properties
            String csvFilePath = properties.getProperty("csvFilePath");

            // Now you can use a CSV parsing library (e.g., Apache Commons CSV) to read the CSV data
            // Let's assume you have a method to read the CSV file and process the data
            read(csvFilePath);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void read(String csvFilePath)  {
        try (CSVReader reader = new CSVReader(new FileReader(csvFilePath))) {
            List<String[]> csvData = reader.readAll();

            for (String[] row : csvData) {
                String book = row[0];
                String isin = row[1];
                String clean_price = row[2];

                // Create a new BookEPM object and do whatever you want with it
                BookEPM bookEPM = new BookEPM();
                bookEPM.setBook(book);
                bookEPM.setIsin(isin);
                bookEPM.setClean_Price(clean_price);

                // Do something with the bookEPM object (e.g., add it to a list, process it, etc.)
                System.out.println(bookEPM);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
