IY4113 Milestone 4

| Assessment Details | Please Complete All Details                                         |
| ------------------ | ------------------------------------------------------------------- |
| Group              | 4113 A                                                              |
| Module Title       | Applied Software Engineering using object Orientated Programming    |
| Assessment Type    | Java fundamental's part 1                                           |
| Module Tutor Name  | Jonathan Shore                                                      |
| Student ID Number  | P512757                                                             |
| Date of Submission | 24 May 2026                                                         |
| Word Count         | 2796                                                                |
| GItHub Link        | https://github.com/toenandaraung218-code/T0512757_Toe_4113/settings |

- [x] *I confirm that this assignment is my own work. Where I have referred to academic sources, I have provided in-text citations and included the sources in
  the final reference list.*

- [x] *Where I have used AI, I have cited and referenced appropriately.
  
  Oracle.(n.d.).ArrayList(Java SE 17 & JDK 17).Retrieved from :([https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayList.html)[Accessed](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayList.html) 22 May 2026].
  
  Anthropic. (2024). Claude (Version Sonnet 4.6) [Large language model]. Retrieved from: [https://www.claude.ai](https://www.claude.ai) [Accessed 14 May 2026]

------------------------------------------------------------------------------------------------------------------------------

### Program Code

------------------------------------------------------------------------------------------------------------------------------

## Main.java

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

## InputHelper.java

import java.math.BigDecimal;  
import java.util.Scanner;  
//all my input stuff lives here so I don't repeat myself everywhere  
public class InputHelper {  

    //keeping the scanner here means I don't have to pass it around everytime  
    private Scanner sc;  
    
    public InputHelper() {  
        this.sc = new Scanner (System.in);  
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

## Journey.java

import java.math.BigDecimal;  
import java.math.RoundingMode;  

// this class just holds the data for one journey — it doesn't do any logic  
// JourneyManager is the one that creates and manages these  
public class Journey {  

    // every field is private so nothing outside can change them directly  
    private int id;  
    private int fromZone;  
    private int toZone;  
    private int zonesCrossed;// calculated in the constructor, not passed in  
    private CityRideDataset.PassengerType passengerType;  
    private CityRideDataset.TimeBand timeBand;  
    private BigDecimal baseFare;  
    private BigDecimal discount;  
    private BigDecimal fareCharged;  
    
    public Journey(int id, int fromZone, int toZone,  
                   CityRideDataset.PassengerType passengerType,  
                   CityRideDataset.TimeBand timeBand,  
                   BigDecimal baseFare, BigDecimal discount, BigDecimal fareCharged) {  
        this.id            = id;  
        this.fromZone      = fromZone;  
        this.toZone        = toZone;  
        // working out zones crossed here so I don't have to pass it in every time  
        this.zonesCrossed  = Math.abs(toZone - fromZone) + 1;  
        this.passengerType = passengerType;  
        this.timeBand      = timeBand;  
        this.baseFare      = baseFare;  
        this.discount      = discount;  
        this.fareCharged   = fareCharged;  
    }  
    
    // getters — the only way to read the data from outside this class  
    public int getId() { return id; }  
    public int getFromZone() { return fromZone; }  
    public int getToZone() { return toZone; }  
    public int getZonesCrossed() { return zonesCrossed; }  
    public CityRideDataset.PassengerType getPassengerType() { return passengerType; }  
    public CityRideDataset.TimeBand getTimeBand() { return timeBand; }  
    public BigDecimal getBaseFare() { return baseFare; }  
    public BigDecimal getDiscount() { return discount; }  
    public BigDecimal getFareCharged() { return fareCharged; }  
    
    // this is what gets printed whenever a Journey object is used in a println  
    // RoundingMode.HALF_UP makes sure the pence don't go weird (e.g. 2.5000001)    @Override  
    public String toString() {  
        return "ID:" + id  
                + "  From:" + fromZone  
                + "  To:" + toZone  
                + "  Zones:" + zonesCrossed  
                + "  Type:" + passengerType  
                + "  Band:" + timeBand  
                + "  Base:£" + baseFare.setScale(2, RoundingMode.HALF_UP)  
                + "  Discount:£" + discount.setScale(2, RoundingMode.HALF_UP)  
                + "  Charged:£" + fareCharged.setScale(2, RoundingMode.HALF_UP);  
    }

## JourneyManager

import java.math.BigDecimal;  
import java.math.RoundingMode;  
import java.util.ArrayList;  

//Manages everything to do with journeys - adding,listing , removing , resetting  
public class JourneyManager {  

    //all journeys get stored here as a list  
    private ArrayList<Journey> Journeys = new ArrayList<>();  
    
    public ArrayList<Journey> getJourneys() {  
        return Journeys;  
    }  
    
    //keeping a running total per passenger type so I can check the daily cap  
    BigDecimal totalAdult   = new BigDecimal("0.00");  
    BigDecimal totalStudent = new BigDecimal("0.00");  
    BigDecimal totalChild   = new BigDecimal("0.00");  
    BigDecimal totalSenior  = new BigDecimal("0.00");  
    
    //starts at 1 and goes up every time a journey is added, so each journey has a unique ID  
    int nextId = 1;  
    
    
    public void addJourney(InputHelper input) {  
        System.out.println("\n--- ADD JOURNEY ---");  
    
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
        } else {  
            fareCharged = afterDiscount;  
        }  
    
        //adding the fare to the running total for this passenger type  
        updateRunningTotal(passengerType, fareCharged);  
    
        //building the journey object and saving it to the list  
        Journey journey = new Journey(nextId, fromZone, toZone,  
                passengerType, timeBand, baseFare, discountAmount, fareCharged);  
    
        Journeys.add(journey);  
        nextId++;  
        System.out.println("\nJourney added! ID: " + journey.getId());  
        System.out.println("Route       : Zone " + fromZone + " to Zone " + toZone + " (" + zonesCrossed + " zones)");  
        System.out.println("Passenger   : " + passengerType);  
        System.out.println("Time Band   : " + timeBand);  
        System.out.println("Base Fare   : £" + baseFare);  
        System.out.println("Discount    : £" + discountAmount);  
        System.out.println("Fare Charged: £" + fareCharged);  
        System.out.println("Daily Total : £" + getRunningTotal(passengerType) + " / £" + cap);  
    }  
    
    
    public void listJourneys() {  
        System.out.println("\n--- ALL JOURNEYS ---");  
        if (Journeys.isEmpty()) {  
            System.out.println("No journeys recorded yet.");  
            return;  
        }  
    
        //just printing each journey using its to String method  
        for (Journey j : Journeys) {  
            System.out.println(j);  
        }  
        System.out.println("Total: " + Journeys.size() + " journey(s)");  
    }  
    
    
    
    public void removeJourney(InputHelper input) {  
        System.out.println("\n--- REMOVE JOURNEY ---");  
        if (Journeys.isEmpty()) {  
            System.out.println("No journeys to remove.");  
            return;  
        }  
    
        int id = input.readInt("Enter Journey ID to remove: ", 1, Integer.MAX_VALUE);  
    
        //searching the list to find the journey with that ID  
        Journey found = null;  
        for (Journey j : Journeys) {  
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
        Journeys.remove(found);  
        System.out.println("Journey " + id + " removed successfully.");  
    }  
    
    
    
    public void resetSystem(InputHelper input) {  
        System.out.println("\n--- RESET SYSTEM ---");  
        System.out.println("WARNING: This will delete ALL journeys and reset all totals.");  
        //making sure they really mean it before wiping everything  
        boolean confirm = input.readYesNo("Are you sure? (yes/no): ");  
        if (confirm) {  
            Journeys.clear();  
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

## ReportManager.java

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

------------------------------------------------------------------------------------------------------------------------------

### Updated Gantt Chart

------------------------------------------------------------------------------------------------------------------------------

![](/Users/toenandaraung/Desktop/Screenshot%202026-05-24%20at%2019.40.35.png)

------------------------------------------------------------------------------------------------------------------------------

### Diary Entries

------------------------------------------------------------------------------------------------------------------------------

## Diary Entry 1 - May 22nd 2026

Spent a good part of today refactoring InputHelper as the design didn't feel right. Every method was static so I had to keep passing the same Scanner object into every method call. Initially I didn't see the issue with this, but after researching more about object oriented design I realised that the class was behaving more like a collection of functions and not a real object. The hardest part was understanding the difference between static methods and instance methods and the fact that in removing static we needed a constructor. Since that was where the creation of the Scanner was originally done, I also updated Main.java Moving the Scanner into the class as a field made the structure finally make sense

## Diary Entry 2 - May 23, 2026

Worked on the refactoring some more today, and updated JourneyManager and ReportManager to use InputHelper instead of passing around Scanner directly. While looking through the code I also found a typo inside a BigDecimal constructor that would have caused the program to crash at runtime, so I fixed that as well.

I also spent some time better understanding exception handling. Initially I thought it was safer to use catch (Exception e) everywhere because it catches all errors but I realized that it can actually hide unexpected problems and make debugging much harder. Another thing I found hard was to use negate() to remove journey fares. It took me a while to figure out that I could pass a negative value to subtract from the running total instead of having to write separate logic for subtraction.

## Diary Entry 3 - May 24,2026

Most of today’s work was spent on making sure that my code was readable and contained appropriate comments. It occurred to me that it’s relatively easy to comment on what my code does; it’s rather hard to comment on why it is done the way it is done.

The main task for today was making my comments better and meaningful for JourneyManager, ReportManager, and Journey classes, explaining the logic of particular parts of code. For instance, one of the challenges was to figure out the differences between readInt and readIntOptional, as I failed at understanding them initially. ReadIntOptional allows the use of 0 as a “skip” answer, whereas readInt must have a real value within range. Also, @Override annotation turned out to be quite useful, as it allows to notice errors of method overriding from its superclass.

------------------------------------------------------------------------------------------------------------------------------
