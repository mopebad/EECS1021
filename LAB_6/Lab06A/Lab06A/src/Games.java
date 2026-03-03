import java.util.Scanner;
import java.util.Random;

public class Games {

    public static void guessing() {

        Scanner input = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Please enter the upper range of the game: ");
        int upper = input.nextInt();

        // Generate random number between 0 and upper (inclusive)
        int answer = random.nextInt(upper + 1);

        int guess;
        int count = 0;

        do {
            System.out.print("Enter a Guess: ");
            guess = input.nextInt();
            count++;

            if (guess > answer) {
                System.out.println("Guess is higher than the answer.");
            } 
            else if (guess < answer) {
                System.out.println("Guess is lower than the answer.");
            } 
            else {
                System.out.println("Correct! Took " + count + " guesses to find the answer.");
            }

        } while (guess != answer);

        input.close();
    }
}