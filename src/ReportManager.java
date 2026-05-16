import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Scanner;

public class ReportManager {

    JourneyManager jm;

    public ReportManager(JourneyManager jm) {
        this.jm = jm;
    }


    public void filterJourneys(Scanner sc) {

        System.out.println("\n--- FILTER JOURNEYS ---");

        System.out.println("Passenger type: 1=Adult 2=Student 3=Child 4=Senior 0=Any");
        int ptChoice = InputHelper.readIntOptional(sc, "Choose: ", 0, 4);

        CityRideDataset.PassengerType filterType = null;
        if (ptChoice == 1) filterType = CityRideDataset.PassengerType.ADULT;
        else if (ptChoice == 2) filterType = CityRideDataset.PassengerType.STUDENT;
        else if (ptChoice == 3) filterType = CityRideDataset.PassengerType.CHILD;
        else if (ptChoice == 4) filterType = CityRideDataset.PassengerType.SENIOR_CITIZEN;

        int filterZone = InputHelper.readIntOptional(sc, "Zone (1-5, 0=Any): ", 0, 5);

        ArrayList<Journey> results = new ArrayList<>();

        for (Journey j : jm.getJourneys()) {
            if (filterType != null && j.getPassengerType() != filterType) continue;
            if (filterZone > 0 && j.getFromZone() != filterZone && j.getToZone() != filterZone) continue;
            results.add(j);
        }

        System.out.println("Matching journeys: " + results.size());
        for (Journey j : results) {
            System.out.println(j);
        }
    }


    public void viewSummary() {

        System.out.println("\n--- DAILY SUMMARY ---");

        if (jm.getJourneys().isEmpty()) {
            System.out.println("No journeys recorded yet.");
            return;
        }

        BigDecimal total = new BigDecimal("0.00");

        for (Journey j : jm.getJourneys()) {
            total = total.add(j.getFareCharged());
        }

        BigDecimal avg = total.divide(
                new BigDecimal(jm.getJourneys().size()),
                2,
                RoundingMode.HALF_UP
        );

        Journey mostExpensive = jm.getJourneys().get(0);

        for (Journey j : jm.getJourneys()) {
            if (j.getFareCharged().compareTo(mostExpensive.getFareCharged()) > 0) {
                mostExpensive = j;
            }
        }

        int countAdult = 0, countStudent = 0, countChild = 0, countSenior = 0;

        for (Journey j : jm.getJourneys()) {
            if (j.getPassengerType() == CityRideDataset.PassengerType.ADULT) countAdult++;
            else if (j.getPassengerType() == CityRideDataset.PassengerType.STUDENT) countStudent++;
            else if (j.getPassengerType() == CityRideDataset.PassengerType.CHILD) countChild++;
            else countSenior++;
        }

        System.out.println("Total Journeys : " + jm.getJourneys().size());
        System.out.println("Total Amount   : £" + total);
        System.out.println("Average Fare   : £" + avg);

        System.out.println("Most Expensive : Journey ID " + mostExpensive.getId()
                + " - £" + mostExpensive.getFareCharged()
                + " (Zone " + mostExpensive.getFromZone()
                + " to " + mostExpensive.getToZone()
                + ", " + mostExpensive.getPassengerType() + ")");

        System.out.println("\n-- Category Counts --");
        System.out.println("Adult          : " + countAdult);
        System.out.println("Student        : " + countStudent);
        System.out.println("Child          : " + countChild);
        System.out.println("Senior Citizen : " + countSenior);

        System.out.println("\n-- Daily Totals vs Cap --");
        System.out.println("Adult          : £" + jm.totalAdult);
        System.out.println("Student        : £" + jm.totalStudent);
        System.out.println("Child          : £" + jm.totalChild);
        System.out.println("Senior Citizen : £" + jm.totalSenior);
    }


    public void viewTotalsByPassengerType() {

        int countAdult = 0, countStudent = 0, countChild = 0, countSenior = 0;

        for (Journey j : jm.getJourneys()) {
            if (j.getPassengerType() == CityRideDataset.PassengerType.ADULT) countAdult++;
            else if (j.getPassengerType() == CityRideDataset.PassengerType.STUDENT) countStudent++;
            else if (j.getPassengerType() == CityRideDataset.PassengerType.CHILD) countChild++;
            else countSenior++;
        }

        System.out.println("Adult   : £" + jm.totalAdult + " (" + countAdult + ")");
        System.out.println("Student : £" + jm.totalStudent + " (" + countStudent + ")");
        System.out.println("Child   : £" + jm.totalChild + " (" + countChild + ")");
        System.out.println("Senior  : £" + jm.totalSenior + " (" + countSenior + ")");
    }
}