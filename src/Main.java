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

        RiderProfile profile = RiderProfile.createNewProfile(input);

        SettingsManager settings = new SettingsManager();
        settings.displaySettings();

        boolean running = true;

        while (running) {

            System.out.println("\n==============================");
            System.out.println("         MAIN MENU            ");
            System.out.println("==============================");
            System.out.println("Logged in as: " + profile.getName());
            System.out.println("1. Add Journey");
            System.out.println("2. List Journeys");
            System.out.println("3. Edit Journeys");
            System.out.println("4. Filter Journeys(old, criteria)");
            System.out.println("5. Filter Journeys by Date/Time Band");
            System.out.println("6. View Daily Summary");
            System.out.println("7. View Totals by Passenger Type");
            System.out.println("8. Remove Journey");
            System.out.println("9. Reset System");
            System.out.println("10 View my profile");
            System.out.println("11. Exit");
            System.out.println("==============================");

            //using the input object instead of the old static call
            int choice = input.readInt("Enter option (1-9): ", 1, 11);

            if      (choice == 1) jm.addJourneys(input);
            else if (choice == 2) jm.listJourneys();
            else if (choice == 3) jm.editJourneys(input);
            else if (choice == 4) reports.filterJourneys(input);
            else if (choice == 5) jm.filterJourneysByDateAndTimeBand(input);
            else if (choice == 6) reports.viewSummary();
            else if (choice == 7) reports.viewTotalsByPassengerType();
            else if (choice == 8) jm.removeJourneys(input);
            else if (choice == 9) jm.resetSystem(input);
            else if (choice == 10)System.out.println("\n" + profile);
            else if (choice == 11) {
                System.out.println("Goodbye, " + profile.getName() + "!");;
                running = false;
            }
        }
        input.close();


    }
}