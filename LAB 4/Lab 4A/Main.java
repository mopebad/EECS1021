import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter x: ");
        double x = input.nextDouble();

        double y = Math.sin(5 * x) / Math.log(1.5 + Math.cos(x));

        y = Math.round(y * 100.0) / 100.0;

        System.out.println(y);
    }
}
