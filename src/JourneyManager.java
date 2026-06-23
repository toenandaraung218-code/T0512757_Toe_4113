import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.Callable;

//Manages everything to do with journeys - adding,listing , removing , resetting
public class JourneyManager {

    //all journeys get stored here as a list
    private ArrayList<Journey> journeys = new ArrayList<>();

    public ArrayList<Journey> getJourneys() {
        return journeys;
    }

    //keeping a running total per passenger type so I can check the daily cap
    BigDecimal totalAdult   = new BigDecimal("0.00");
    BigDecimal totalStudent = new BigDecimal("0.00");
    BigDecimal totalChild   = new BigDecimal("0.00");
    BigDecimal totalSenior  = new BigDecimal("0.00");

    //starts at 1 and goes up every time a journey is added, so each journey has a unique ID
    int nextId = 1;

    //Add journeys
    public void addJourney(InputHelper input) {
        System.out.println("\n--- ADD JOURNEY ---");

        LocalDate date = input.readDate("Enter journey date(yyyy-MM-dd) or press enter for today: ");

        //it keeps asking until the user types something valid, so I don't have to validate myself
        int fromZone = input.readInt("Enter start zone (1-5): ", 1, 5);
        int toZone   = input.readInt( "Enter destination zone (1-5): ", 1, 5);

        System.out.println("Passenger type: 1=Adult  2=Student  3=Child  4=Senior Citizen");
        //the user picks 1 to 4,noting else is valid
        int ptChoice = input.readInt("Choose (1-4): ", 1, 4);

        //turning the number they picket into the actual passenger type
        CityRideDataset.PassengerType passengerType;
        if      (ptChoice == 1) passengerType = CityRideDataset.PassengerType.ADULT;
        else if (ptChoice == 2) passengerType = CityRideDataset.PassengerType.STUDENT;
        else if (ptChoice == 3) passengerType = CityRideDataset.PassengerType.CHILD;
        else                    passengerType = CityRideDataset.PassengerType.SENIOR_CITIZEN;


        System.out.println("Time band: 1=Peak  2=Off-Peak");
        //same idea - only 1 & 2 is valid here
        int tbChoice = input.readInt("Choose (1-2): ", 1, 2);

        //turning the number into the actual time band
        CityRideDataset.TimeBand timeBand;
        if (tbChoice == 1) timeBand = CityRideDataset.TimeBand.PEAK;
        else               timeBand = CityRideDataset.TimeBand.OFF_PEAK;

        // how many zones they're travelling through - used for display only
        int zonesCrossed = Math.abs(toZone - fromZone) + 1;

        //pulling the base fare from the dataset based on zones and time band
        BigDecimal baseFare = CityRideDataset.getBaseFare(fromZone, toZone, timeBand);
        if (baseFare == null) {

            //if there's no fare for that combination
            System.out.println("Error: No fare found. Journey not added.");
            return;
        }

        //working out the discount and what's left after it
        BigDecimal discountRate   = CityRideDataset.DISCOUNT_RATE.get(passengerType);
        BigDecimal discountAmount = baseFare.multiply(discountRate).setScale(2, RoundingMode.HALF_UP);
        BigDecimal afterDiscount  = baseFare.subtract(discountAmount).setScale(2, RoundingMode.HALF_UP);

        //checking if adding this fare would go over the daily cap
        BigDecimal cap            = CityRideDataset.DAILY_CAP.get(passengerType);
        BigDecimal runningTotal   = getRunningTotal(passengerType);
        BigDecimal fareCharged;

        if (runningTotal.add(afterDiscount).compareTo(cap) > 0) {
            //they've hit the cap so they only pay whatever is left up the cap
            fareCharged = cap.subtract(runningTotal).max(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
            System.out.println("Daily cap of £" + cap + " reached! Fare adjusted to £" + fareCharged);
        }
        else {
            fareCharged = afterDiscount;
        }

        //adding the fare to the running total for this passenger type
        updateRunningTotal(passengerType, fareCharged);

        //building the journey object and saving it to the list
        Journey journey = new Journey(nextId,date, fromZone, toZone,
                passengerType, timeBand, baseFare, discountAmount, fareCharged);

        journeys.add(journey);
        nextId++;
        System.out.println("\nJourney added! ID: " + journey.getId());
        System.out.println("Date        : " + date);
        System.out.println("Route       : Zone " + fromZone + " to Zone " + toZone + " (" + zonesCrossed + " zones)");
        System.out.println("Passenger   : " + passengerType);
        System.out.println("Time Band   : " + timeBand);
        System.out.println("Base Fare   : £" + baseFare);
        System.out.println("Discount    : £" + discountAmount);
        System.out.println("Fare Charged: £" + fareCharged);
        System.out.println("Daily Total : £" + getRunningTotal(passengerType) + " / £" + cap);
    }

    //List Journeys
    public void listJourneys() {
        System.out.println("\n--- ALL JOURNEYS ---");
        if (journeys.isEmpty()) {
            System.out.println("No journeys recorded yet.");
            return;
        }

        //just printing each journey using its to String method
        for (Journey j : journeys) {
            System.out.println(j);
        }
        System.out.println("Total: " + journeys.size() + " journey(s)");
    }


    // Find journeys
    private Journey findJourneyByID(int id){
        for(Journey j : journeys){
            if(j.getId() == id){
                return j;
            }
        }
        return null;
    }


    //Edit Journeys
    private void editJourney(InputHelper input){
        System.out.println("\n-------Edit Journey-------");
        if (journeys.isEmpty()){
            System.out.println("No journeys to edit.");
            return;
        }

        int id = input.readInt("Enter Journey ID to edit: ", 1, Integer.MAX_VALUE);
        Journey found = findJourneyByID(id);

        if (found == null){
            System.out.println("Journey ID " +id + "not found");
            return;
        }
        System.out.println("Current details: " + found);

        updateRunningTotal(found.getPassengerType(), found.getFareCharged());

        int fromZone = input.readInt("Enter new start zone (1-5): ",1,5);
        int toZone = input.readInt("Enter new destination zone(1-5): ",1,5);

        System.out.println("Passenger type: 1=Adult 2=Student 3=Child 4=Senior Citizen");
        int ptChoice = input.readInt("Choose (1-4): ",1,4);
        CityRideDataset.PassengerType passengerType;
        if (ptChoice == 1) passengerType = CityRideDataset.PassengerType.ADULT;
        else if (ptChoice == 2) passengerType = CityRideDataset.PassengerType.STUDENT;
        else if (ptChoice == 3)  passengerType = CityRideDataset.PassengerType.CHILD;
        else  passengerType = CityRideDataset.PassengerType.SENIOR_CITIZEN;

        System.out.println("Time band: 1=Peak 2=Off-Peak");
        int tbChoice = input.readInt("Choose (1-2): ",1,2);
        CityRideDataset.TimeBand timeBand;
        if (tbChoice == 1) timeBand = CityRideDataset.TimeBand.PEAK;
        else timeBand = CityRideDataset.TimeBand.OFF_PEAK;

        BigDecimal baseFare = CityRideDataset.getBaseFare(fromZone,toZone,timeBand);
        if (baseFare == null){
            System.out.println("Error: No fare found for that route. Edit cancelled.");
            updateRunningTotal(found.getPassengerType(),found.getFareCharged());
            return;
        }

    }






    //Remove journeys
    public void removeJourney(InputHelper input) {
        System.out.println("\n--- REMOVE JOURNEY ---");
        if (journeys.isEmpty()) {
            System.out.println("No journeys to remove.");
            return;
        }

        int id = input.readInt("Enter Journey ID to remove: ", 1, Integer.MAX_VALUE);

        //searching the list to find the journey with that ID
        Journey found = null;
        for (Journey j : journeys) {
            if (j.getId() == id) {
                found = j;
                break;
            }
        }
        if (found == null) {
            System.out.println("Journey ID " + id + " not found.");
            return;
        }

        System.out.println("Found: " + found);
        //double checking before removing - don't want to delete the wrong one
        boolean confirm = input.readYesNo( "Remove this journey? (yes/no): ");
        if (!confirm) {
            System.out.println("Removal cancelled.");
            return;
        }

        //subtracting the fare from the running total before removing it
        updateRunningTotal(found.getPassengerType(), found.getFareCharged().negate());
        journeys.remove(found);
        System.out.println("Journey " + id + " removed successfully.");
    }



    //Reset
    public void resetSystem(InputHelper input) {
        System.out.println("\n--- RESET SYSTEM ---");
        System.out.println("WARNING: This will delete ALL journeys and reset all totals.");
        //making sure they really mean it before wiping everything
        boolean confirm = input.readYesNo("Are you sure? (yes/no): ");
        if (confirm) {
            journeys.clear();
            totalAdult = new BigDecimal("0.00");
            totalStudent = new BigDecimal("0.00");
            totalChild = new BigDecimal("0.00");
            totalSenior = new BigDecimal("0.00");
            nextId = 1;
            System.out.println("System reset. All data cleared.");
        } else {
            System.out.println("Reset cancelled.");
        }
    }


    //returns the current daily total for whichever passenger type is passed in
    public BigDecimal getRunningTotal(CityRideDataset.PassengerType pt) {
        return switch (pt) {
            case ADULT -> totalAdult;
            case STUDENT -> totalStudent;
            case CHILD -> totalChild;
            default -> totalSenior;
        };
    }

    private void updateRunningTotal(CityRideDataset.PassengerType pt, BigDecimal amount) {
        switch (pt) {
            case ADULT ->
                    totalAdult = totalAdult.add(amount).max(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
            case STUDENT ->
                    totalStudent = totalStudent.add(amount).max(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
            case CHILD ->
                    totalChild = totalChild.add(amount).max(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
            default ->
                    totalSenior = totalSenior.add(amount).max(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);
        }
    }
}