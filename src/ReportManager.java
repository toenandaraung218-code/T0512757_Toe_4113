import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

//handles all the reporting side - filtering , summaries, totals
// it doesn't store any data itself, it just reads from JourneyManager
public class ReportManager {

    // need a reference to JourneyManager so I can read the journey
    JourneyManager jm;

    public ReportManager(JourneyManager jm) {
        this.jm = jm;
    }


    public void filterJourneys(InputHelper input) {

        System.out.println("\n--- FILTER JOURNEYS ---");

        System.out.println("Passenger type: 1=Adult 2=Student 3=Child 4=Senior 0=Any");
        //O is a valid choice here - it means "don't filter by type"
        int ptChoice = input.readIntOptional("Choose: ", 0, 4);

        CityRideDataset.PassengerType filterType = null;
        if (ptChoice == 1) filterType = CityRideDataset.PassengerType.ADULT;
        else if (ptChoice == 2) filterType = CityRideDataset.PassengerType.STUDENT;
        else if (ptChoice == 3) filterType = CityRideDataset.PassengerType.CHILD;
        else if (ptChoice == 4) filterType = CityRideDataset.PassengerType.SENIOR_CITIZEN;

        int filterZone = input.readIntOptional("Zone (1-5, 0=Any): ", 0, 5);

        //building a separate list of matches rather than modifying the original
        ArrayList<Journey> results = new ArrayList<>();

        for (Journey j : jm.getJourneys()) {
            //skip this journey if it doesn't if it doesn't match the passenger type filter
            if (filterType != null && j.getPassengerType() != filterType) continue;

            //skip if it doesn't involve the zone they filtered by
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

        //adding up every fare charged to get the grand total
        BigDecimal total = new BigDecimal("0.00");

        for (Journey j : jm.getJourneys()) {
            total = total.add(j.getFareCharged());
        }

        //dividing total by number of journeys to get the average fare
        BigDecimal avg = total.divide(
                new BigDecimal(jm.getJourneys().size()),
                2,
                RoundingMode.HALF_UP
        );

        //starting with the first journey and comparing as we go to find the most expensive
        Journey mostExpensive = jm.getJourneys().get(0);

        for (Journey j : jm.getJourneys()) {
            if (j.getFareCharged().compareTo(mostExpensive.getFareCharged()) > 0) {
                mostExpensive = j;
            }
        }

        //counting how many journeys belong to each passenger type
        int countAdult = 0, countStudent = 0, countChild = 0, countSenior = 0;

        for (Journey j : jm.getJourneys()) {
            if (j.getPassengerType() == CityRideDataset.PassengerType.ADULT) countAdult++;
            else if (j.getPassengerType() == CityRideDataset.PassengerType.STUDENT) countStudent++;
            else if (j.getPassengerType() == CityRideDataset.PassengerType.CHILD) countChild++;
            else                                                                    countSenior++;
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

        //showing how much each types has spent today vs their cap - pulled straight from journeyManager
        System.out.println("\n-- Daily Totals vs Cap --");
        System.out.println("Adult          : £" + jm.totalAdult);
        System.out.println("Student        : £" + jm.totalStudent);
        System.out.println("Child          : £" + jm.totalChild);
        System.out.println("Senior Citizen : £" + jm.totalSenior);
    }


    //shorter version of the above - just totals and counts, no full summary
    public void viewTotalsByPassengerType() {

        //counting journeys per type again so we can show them next to the  totals
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