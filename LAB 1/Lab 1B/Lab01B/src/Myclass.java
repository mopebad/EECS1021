import java.util.Scanner;

public class Myclass {
    public static void main(String[] args)  {

        Scanner input = new Scanner(System.in);

        // Ask user for name
        System.out.println("What is your name?");
        String name = input.nextLine();

        // Ask user for student number
        System.out.println("Enter your YU student number.");
        String student_id = input.nextLine();

        // Get the last 4 digits
        String last4dig = student_id.substring(student_id.length() - 4);

        // Final message
        System.out.println("Hello, " + name.toUpperCase() + " the last 4 digits of your student number is " + last4dig);
    }
}