// Name: Joshua Chan
// Student Number: 222272447
// Lab Section: Section W lab 1

import java.util.Scanner;

public class Lab02B {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter a value for x: ");
        double x = input.nextDouble();

        // tell user negative entries are not allowed 
        if (x < 0) {
            System.out.println("Negative entries are not allowed.");
            System.exit(0);
        }

        // calculate a
        double a = 2 / (7.2 * Math.pow(x, -3) + 2);

        // calculate the numerator
        double numerator = Math.pow(
                (2 * Math.pow(x, 2.2) - 1.7),
                2 * Math.tan(a)
        );

        // calculate the denominator
        double denominator = Math.log(
                Math.pow(x, 1.0 / 5.0) - (1.5 / x)
        );

        // calculate y
        double y = numerator / denominator;

        System.out.println("For x=" + x + ", y is evaluated as " + y + ".");

        input.close();
    }
}
