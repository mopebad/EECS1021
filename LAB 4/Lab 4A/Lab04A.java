import java.util.Scanner;

public class Lab04A {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("This program receives an integer vector and checks");
        System.out.println("if it has any element divisible by N & M.");

        System.out.print("Enter an integer value for N: ");
        int n = sc.nextInt();

        System.out.print("Enter an integer value for M: ");
        int m = sc.nextInt();

        sc.nextLine(); // clear newline

        System.out.println("Enter your integer vector elements below (comma separated).");
        System.out.println("Only enter numbers (do not use any letter or space).");

        String input = sc.nextLine();

        long startTime = System.currentTimeMillis();

        String[] nums = input.split(",");
        int index = -1;

        for (int i = 0; i < nums.length; i++) {
            int value = Integer.parseInt(nums[i]);
            if (value % n == 0 && value % m == 0) {
                index = i + 1;
                break;
            }
        }

        long endTime = System.currentTimeMillis();

        if (index != -1) {
            System.out.println("Element#" + index + " of the entered vector is divisible by " + n + " & " + m + ".");
        } else {
            System.out.println("The entered vector has no element divisible by " + n + " & " + m + ".");
        }

        System.out.println("The entered vector was processed in " + (endTime - startTime) + " milliseconds.");

        sc.close();
    }
}
