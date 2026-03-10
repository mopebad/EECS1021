import com.opencsv.CSVReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MainCSV {
    public static void main(String[] args) {
        String inputFile = "C:\\Users\\joshm\\IdeaProjects\\EECS1021\\Lab7\\CO2_Input.csv";
        String outputFile = "Lab7/output.txt";

        ArrayList<String[]> rows = new ArrayList<>();

        double co2Sum = 0;
        double tempSum = 0;
        double humiditySum = 0;
        int count = 0;

        try {
            CSVReader reader = new CSVReader(new FileReader(inputFile));
            List<String[]> allRows = reader.readAll();
            rows.addAll(allRows);
            reader.close();

            for (int i = 1; i < rows.size(); i++) {   // skip header row
                String[] row = rows.get(i);

                co2Sum += Double.parseDouble(row[2]);       // Column 3
                tempSum += Double.parseDouble(row[3]);      // Column 4
                humiditySum += Double.parseDouble(row[4]);  // Column 5
                count++;
            }

            double co2Average = co2Sum / count;
            double tempAverage = tempSum / count;
            double humidityAverage = humiditySum / count;

            System.out.println("CO2 average: " + co2Average);
            System.out.println("Temperature average: " + tempAverage);
            System.out.println("Humidity average: " + humidityAverage);

            PrintWriter writer = new PrintWriter(outputFile);
            writer.println("CO2 average: " + co2Average);
            writer.println("Temperature average: " + tempAverage);
            writer.println("Humidity average: " + humidityAverage);
            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}