import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //InputHelper now owns the scanner, no need for one here
        InputHelper input     = new InputHelper();
        JourneyManager jm      = new JourneyManager();
        ReportManager  reports = new ReportManager(jm);

        System.out.println("==============================");
        System.out.println("   CITY RIDE FARE SYSTEM      ");
        System.out.println("==============================");

        boolean running = true;

        while (running) {

            System.out.println("\n==============================");
            System.out.println("         MAIN MENU            ");
            System.out.println("==============================");
            System.out.println("1. Add Journey");
            System.out.println("2. List Journeys");
            System.out.println("3. Filter Journeys");
            System.out.println("4. View Daily Summary");
            System.out.println("5. View Totals by Passenger Type");
            System.out.println("6. Remove Journey");
            System.out.println("7. Reset System");
            System.out.println("8. Exit");
            System.out.println("==============================");

            //using the input object instead of the old static call
            int choice = input.readInt("Enter option (1-8): ", 1, 8);

            if      (choice == 1) jm.addJourney(input);
            else if (choice == 2) jm.listJourneys();
            else if (choice == 3) reports.filterJourneys(input);
            else if (choice == 4) reports.viewSummary();
            else if (choice == 5) reports.viewTotalsByPassengerType();
            else if (choice == 6) jm.removeJourney(input);
            else if (choice == 7) jm.resetSystem(input);
            else if (choice == 8) {
                System.out.println("Goodbye!");
                running = false;
            }
        }
        input.close();


    }
}