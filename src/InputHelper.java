import java.math.BigDecimal;
import java.util.Scanner;
import java.time.LocalDate;
//all my input stuff lives here so I don't repeat myself everywhere
public class InputHelper {

    //keeping the scanner here means I don't have to pass it around everytime
    private Scanner sc;

    public InputHelper() {
        this.sc = new Scanner (System.in);
    }

    public LocalDate readDate( String prompt){
        while (true){
            System.out.println(prompt);
            String input = sc.nextLine().trim();
            if (input.isEmpty()){
                return LocalDate.now();
            }
            try{
                return LocalDate.parse(input);
            }
            catch(Exception e){
                System.out.println("Invalid date format.Please use yyyy-MM-dd,e.g. 2026-06-23.");
            }
        }
    }

    public String readText(String prompt){
        while(true){
            System.out.print(prompt);
            String input =sc.nextLine().trim();
            if(!input.isEmpty()){
                return input;
            }
            System.out.println("This field cannot be empty.Please try again.");
        }
    }


    //keep asking until the user actually types a number in range
    public int readInt(String prompt, int min, int max) {
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
            catch (NumberFormatException e) {

                //they typed something weird, just ask again
                System.out.println("Invalid input. Please enter a whole number.");
            }
        }
    }


    //same thing but 0 or empty means they want to skip it
    public int readIntOptional(String prompt, int min, int max) {
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

    //for things like prices - returns null if they skip
    public BigDecimal readDecimalOptional(String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (input.isEmpty() || input.equals("0")) return null;
            try {
                return new BigDecimal(input);
            }
            catch (NumberFormatException e) {

                //not a valid number , try again
                System.out.println("Invalid amount. Example: 2.50");
            }
        }
    }

    //only accepts yes/no or y/n, nothing else
    public boolean readYesNo( String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();
            if (input.equalsIgnoreCase("yes") || input.equalsIgnoreCase("y")) return true;
            if (input.equalsIgnoreCase("no")  || input.equalsIgnoreCase("n"))  return false;
            System.out.println("Please type yes or no.");
        }
    }

    public void close(){
        sc.close();
    }
}