import java.io.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        String fileName = "C:\\Users\\joshm\\IdeaProjects\\EECS1021\\Lab7\\CO2_Input.csv";

        ArrayList<String> lines = new ArrayList<>();

        double co2Sum = 0;
        double tempSum = 0;
        double humiditySum = 0;

        int count = 0;

        try {

            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = br.readLine()) != null) {
                lines.add(line);
            }

            br.close();

            for (int i = 1; i < lines.size(); i++) {

                String[] values = lines.get(i).split(",");

                double co2 = Double.parseDouble(values[2]);
                double temp = Double.parseDouble(values[3]);
                double humidity = Double.parseDouble(values[4]);

                co2Sum += co2;
                tempSum += temp;
                humiditySum += humidity;

                count++;
            }

            double co2Avg = co2Sum / count;
            double tempAvg = tempSum / count;
            double humidityAvg = humiditySum / count;

            System.out.println("CO2 Average: " + co2Avg);
            System.out.println("Temperature Average: " + tempAvg);
            System.out.println("Humidity Average: " + humidityAvg);

            PrintWriter writer = new PrintWriter("Lab7/output.txt");

            writer.println("CO2 Average: " + co2Avg);
            writer.println("Temperature Average: " + tempAvg);
            writer.println("Humidity Average: " + humidityAvg);

            writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}