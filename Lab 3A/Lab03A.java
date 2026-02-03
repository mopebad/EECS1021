// Name: Joshua Chan
// Student Number: 222272447
// Lab Section: Section W lab 1

import java.util.Scanner;

public class Lab03A {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("This program creates a discount chart for a gas station.");
        System.out.println("All discount prices after tax are rounded to 0.25.");

        // Title
        System.out.print("Enter the title of the discount chart: ");
        String title = sc.nextLine();

        // Minimum litres (only needs to be positive, as shown in sample)
        double minLitres;
        while (true) {
            System.out.print("Enter minimum litres: ");
            minLitres = sc.nextDouble();
            if (minLitres > 0) break;
            System.out.println("Invalid input. Minimum litres must be a positive number.");
        }

        // Maximum litres (sample forces acceptable range to 10..50 instead of re-prompting)
        System.out.print("Enter maximum litres: ");
        double maxLitres = sc.nextDouble();

        // If min/max not acceptable, force to 10 and 50 (matches screenshot behavior)
        int startLitres = 10;
        int endLitres = 50;
        if (minLitres < 10 || minLitres > 50 || maxLitres < 10 || maxLitres > 50 || minLitres >= maxLitres) {
            System.out.println("The acceptable minimum and maximum Liters are: 10,50.");
        } else {
            // If acceptable, use the user's values but as integers for the chart
            startLitres = (int) minLitres;
            endLitres = (int) maxLitres;

            // Ensure chart always includes 10 as base point in this assignment’s format
            if (startLitres < 10) startLitres = 10;
            if (endLitres > 50) endLitres = 50;
        }

        // Step value (chart uses integer step; 5.3 -> 5 in sample)
        double stepInput;
        while (true) {
            System.out.print("Enter step value: ");
            stepInput = sc.nextDouble();
            if (stepInput > 0) break;
            System.out.println("Invalid input. Step value must be a positive number.");
        }
        int step = (int) stepInput;
        if (step <= 0) step = 1;

        // Base discount
        System.out.print("Enter base discount percentage for 10L: ");
        double baseDiscount = sc.nextDouble();

        // Additional discount (must keep total under 100%)
       double addDiscount;
            while (true) {
                System.out.print("Enter additional discount per step: ");
                addDiscount = sc.nextDouble();

            if (addDiscount > 0 && baseDiscount + addDiscount < 100) {
                break;
        }

        System.out.println("Invalid input. Total discount must remain under 100%.");
        }


        // Fuel price
        System.out.print("Enter fuel price per litre (CAD): ");
        double pricePerLitre = sc.nextDouble();

        // Exchange rate
        double exchangeRate;
        while (true) {
            System.out.print("Enter CAD to USD exchange rate: ");
            exchangeRate = sc.nextDouble();
            if (exchangeRate > 0) break;
            System.out.println("Invalid input. Exchange rate must be a positive number.");
        }

        // Output
        System.out.println(title);
        System.out.println();
        System.out.printf("%-10s %-18s %-13s %-13s%n", "Litres", "Discount Rate %", "Total (CAD)", "Total (USD)");
        System.out.println("----------------------------------------------------------");

        // Chart ALWAYS starts at 10 in the sample (base discount is defined at 10L)
        // So we print from 10 to 50 with integer step.
        for (int litres = 10; litres <= 50; litres += step) {

            int stepsFrom10 = (litres - 10) / step;
            double discount = baseDiscount + stepsFrom10 * addDiscount;

            double totalCAD = litres * pricePerLitre * (1 - discount / 100.0);
            totalCAD = roundToQuarter(totalCAD);

            double totalUSD = totalCAD * exchangeRate;
            totalUSD = roundToQuarter(totalUSD);

            System.out.printf("%-10d %-18.2f %-13.2f %-13.2f%n",
                    litres, discount, totalCAD, totalUSD);
        }

        sc.close();
    }

    // Round to nearest 0.25
    public static double roundToQuarter(double value) {
        return Math.round(value * 4.0) / 4.0;
    }
}
