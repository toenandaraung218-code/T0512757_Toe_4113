import java.math.BigDecimal;
import java.util.Scanner;
public class InputHelper {

    public static int readInt(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            try {
                int number = Integer.parseInt(input);
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println("Please enter a number between " + min + " and " + max + ".");
            }
            catch (Exception e) {
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }

    public static int readIntOptional(Scanner sc, String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (input.isEmpty() || input.equals("0")) return 0;
            try {
                int number = Integer.parseInt(input);
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println("Please enter a number between " + min + " and " + max + ", or 0 to skip.");
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    public static BigDecimal readDecimalOptional(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (input.isEmpty() || input.equals("0")) return null;
            try {
                return new BigDecimal(input);
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid amount. Example: 2.50");
            }
        }
    }

    public static boolean readYesNo(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) return true;
            if (input.equalsIgnoreCase("no")  || input.equalsIgnoreCase("n"))  return false;
            System.out.println("Please type yes or no.");
        }
    }
}