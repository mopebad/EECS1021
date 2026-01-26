// Name: Joshua Chan
// Student Number: 222272447
// Lab Section: Section W lab 1

import java.util.Scanner;

public class Lab02A {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        // Prompt and read input value
        System.out.print("Enter a value: ");
        double x = input.nextDouble();

        // Calculate sin(x^2.6)
        double result = Math.sin(Math.pow(x, 2.6));

        // Round to three decimal places
        result = Math.round(result * 1000.0) / 1000.0;

        // Output result (matches example)
        System.out.println("Sin(x^2.6) is: " + result);

        input.close();
    }
}
