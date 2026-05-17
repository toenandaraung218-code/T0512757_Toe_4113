IY4113 Milestone 3

| Assessment Details | Please Complete All Details                                      |
| ------------------ | ---------------------------------------------------------------- |
| Group              | 4113 A                                                           |
| Module Title       | Applied Software Engineering using object Orientated Programming |
| Assessment Type    | Java fundamental's part 1                                        |
| Module Tutor Name  | Jonathan Shore                                                   |
| Student ID Number  | P512757                                                          |
| Date of Submission | 17/05/2026                                                       |
| Word Count         | 2229                                                             |

- [x] *I confirm that this assignment is my own work. Where I have referred to academic sources, I have provided in-text citations and included the sources in
  the final reference list.*

- [x] *Where I have used AI, I have cited and referenced appropriately.

---

### Research (minimum of 2, at least 3)

---

Title of research: Java ArrayList and for-loop iteration

Reference (link): Oracle.(n.d.).ArrayList(Java SE 17 & JDK 17).Retrieved from :(https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/util/ArrayList.html)[Accessed 14 May 2026].

How does the research help with coding practise?: It helped me clearly understand how to keep journey data stored in memory and work with it effectively by looping through the collection to filter results, count entries, and calculate totals—all without relying on a database.

Key coding ideas you could reuse in your program: ArrayList<Journey>, for (Journey j : list) loop, .add(), .remove(), .isEmpty(), .size()

Screenshot of research: 

![](/Users/toenandaraung/Desktop/Screenshot%202026-05-17%20at%2012.19.38.png)



![](/Users/toenandaraung/Desktop/Screenshot%202026-05-17%20at%2012.20.01.png)







Title of research:How do I start coding a fare system in Java?

Reference (link): Anthropic. (2024). Claude (Version Sonnet 4.6) [Large language model]. Retrieved from: https://www.claude.ai [Accessed 14 May 2026]

How does the research help with coding practise?: Claude helped me understand how to structure my Java program based on my flowchart and explained how to implement the fare calculation, daily cap and discount logic step by step.

Key coding ideas you could reuse in your program: 

Using BigDecimal for accurate fare rounding

Daily cap check using running totals

Input validation using while loop and try/catch

Using ArrayList to store journeys in memory

---

### Program Code

import java.util.Scanner;  

public class Main {  

    public static void main(String[] args) {  
    
        Scanner        sc      = new Scanner(System.in);  
        JourneyManager jm      = new JourneyManager();  
        ReportManager  reports = new ReportManager(jm);  
    
        System.out.println("==============================");  
        System.out.println("   CITY RIDE FARE SYSTEM      ");  
        System.out.println("==============================");  
    
        boolean running = true;  
    
        while (running) {  
    
            // Display main menu  
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
    
            // Get user choice - validate input  
            int choice = InputHelper.readInt(sc, "Enter option (1-8): ", 1, 8);  
    
            // Execute selected option  
            if      (choice == 1) jm.addJourney(sc);  
            else if (choice == 2) jm.listJourneys();  
            else if (choice == 3) reports.filterJourneys(sc);  
            else if (choice == 4) reports.viewSummary();  
            else if (choice == 5) reports.viewTotalsByPassengerType();  
            else if (choice == 6) jm.removeJourney(sc);  
            else if (choice == 7) jm.resetSystem(sc);  
            else if (choice == 8) {  
                System.out.println("Goodbye!");  
                running = false;  
            }  
        }  
    
        sc.close();  
    }  

}

import java.math.BigDecimal;  
public class Journey {  

    private int id;  
    private int fromZone;  
    private int toZone;  
    private int zonesCrossed;  
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
        this.zonesCrossed  = Math.abs(toZone - fromZone) + 1;  
        this.passengerType = passengerType;  
        this.timeBand      = timeBand;  
        this.baseFare      = baseFare;  
        this.discount      = discount;  
        this.fareCharged   = fareCharged;  
    }  
    public int getId() { return id; }  
    public int getFromZone() { return fromZone; }  
    public int getToZone() { return toZone; }  
    public int getZonesCrossed() { return zonesCrossed; }  
    public CityRideDataset.PassengerType getPassengerType() { return passengerType; }  
    public CityRideDataset.TimeBand getTimeBand() { return timeBand; }  
    public BigDecimal getBaseFare() { return baseFare; }  
    public BigDecimal getDiscount() { return discount; }  
    public BigDecimal getFareCharged() { return fareCharged; }  
    
    
    public String toString() {  
        return "ID:" + id  
                + "  From:" + fromZone  
                + "  To:" + toZone  
                + "  Zones:" + zonesCrossed  
                + "  Type:" + passengerType  
                + "  Band:" + timeBand  
                + "  Base:£" + baseFare.setScale(2)  
                + "  Discount:£" + discount.setScale(2)  
                + "  Charged:£" + fareCharged.setScale(2);  
    }  

}

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

import java.math.BigDecimal;  
import java.math.RoundingMode;  
import java.util.ArrayList;  
import java.util.Scanner;  

public class JourneyManager {  

    private ArrayList<Journey> Journeys = new ArrayList<>();  
    public ArrayList<Journey> getJourneys() {  
        return Journeys;  
    }  
    
    BigDecimal totalAdult   = new BigDecimal("0.00");  
    BigDecimal totalStudent = new BigDecimal("0.00");  
    BigDecimal totalChild   = new BigDecimal("0.00");  
    BigDecimal totalSenior  = new BigDecimal("0.00");  
    
    int nextId = 1;  
    
    public void addJourney(Scanner sc) {  
        System.out.println("\n--- ADD JOURNEY ---");  
    
        int fromZone = InputHelper.readInt(sc, "Enter start zone (1-5): ", 1, 5);  
        int toZone   = InputHelper.readInt(sc, "Enter destination zone (1-5): ", 1, 5);  
    
        System.out.println("Passenger type: 1=Adult  2=Student  3=Child  4=Senior Citizen");  
        int ptChoice = InputHelper.readInt(sc, "Choose (1-4): ", 1, 4);  
    
        CityRideDataset.PassengerType passengerType;  
        if(ptChoice == 1) passengerType = CityRideDataset.PassengerType.ADULT;  
        else if (ptChoice == 2) passengerType = CityRideDataset.PassengerType.STUDENT;  
        else if (ptChoice == 3) passengerType = CityRideDataset.PassengerType.CHILD;  
        else passengerType = CityRideDataset.PassengerType.SENIOR_CITIZEN;  
    
        System.out.println("Time band: 1=Peak  2=Off-Peak");  
        int tbChoice = InputHelper.readInt(sc, "Choose (1-2): ", 1, 2);  
    
        CityRideDataset.TimeBand timeBand;  
        if (tbChoice == 1) timeBand = CityRideDataset.TimeBand.PEAK;  
        else timeBand = CityRideDataset.TimeBand.OFF_PEAK;  
    
        int zonesCrossed = Math.abs(toZone - fromZone) + 1;  
    
        BigDecimal baseFare = CityRideDataset.getBaseFare(fromZone, toZone, timeBand);  
        if (baseFare == null) {  
            System.out.println("Error: No fare found. Journey not added.");  
            return;  
        }  
    
        BigDecimal discountRate   = CityRideDataset.DISCOUNT_RATE.get(passengerType);  
        BigDecimal discountAmount = baseFare.multiply(discountRate).setScale(2, RoundingMode.HALF_UP);  
        BigDecimal afterDiscount  = baseFare.subtract(discountAmount).setScale(2, RoundingMode.HALF_UP);  
    
        BigDecimal cap            = CityRideDataset.DAILY_CAP.get(passengerType);  
        BigDecimal runningTotal   = getRunningTotal(passengerType);  
        BigDecimal fareCharged;  
    
        if (runningTotal.add(afterDiscount).compareTo(cap) > 0) {  
            fareCharged = cap.subtract(runningTotal).max(BigDecimal.ZERO).setScale(2, RoundingMode.HALF_UP);  
            System.out.println("Daily cap of £" + cap + " reached! Fare adjusted to £" + fareCharged);  
        } else {  
            fareCharged = afterDiscount;  
        }  
    
        updateRunningTotal(passengerType, fareCharged);  
    
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
        for (Journey j : Journeys) {  
            System.out.println(j);  
        }  
        System.out.println("Total: " + Journeys.size() + " journey(s)");  
    }  
    
    
    
    public void removeJourney(Scanner sc) {  
        System.out.println("\n--- REMOVE JOURNEY ---");  
        if (Journeys.isEmpty()) {  
            System.out.println("No journeys to remove.");  
            return;  
        }  
    
        int id = InputHelper.readInt(sc, "Enter Journey ID to remove: ", 1, Integer.MAX_VALUE);  
    
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
        boolean confirm = InputHelper.readYesNo(sc, "Remove this journey? (yes/no): ");  
        if (!confirm) {  
            System.out.println("Removal cancelled.");  
            return;  
        }  
    
        updateRunningTotal(found.getPassengerType(), found.getFareCharged().negate());  
        Journeys.remove(found);  
        System.out.println("Journey " + id + " removed successfully.");  
    }  
    
    
    
    public void resetSystem(Scanner sc) {  
        System.out.println("\n--- RESET SYSTEM ---");  
        System.out.println("WARNING: This will delete ALL journeys and reset all totals.");  
        boolean confirm = InputHelper.readYesNo(sc, "Are you sure? (yes/no): ");  
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

### Updated Gantt Chart

![](/Users/toenandaraung/Desktop/Screenshot%202026-05-17%20at%2014.36.02.png)





## **Diary Entry 1 — 14/05/26**

At the start of Milestone 3, I researched the Oracle Java documentation, focusing on how `ArrayList` works to store and manage objects dynamically. This helped me understand how to keep a list of journeys in memory and use methods like `add()`, `get()`, and `size()` effectively.

I then began coding the main structure of the City Ride Lite program by creating the key classes: `Journey`, `JourneyManager`, `ReportManager`, and `InputHelper`. I implemented the `Journey` class first, making sure it could store all journey details and display them clearly. I followed the flowcharts from Milestone 2 to keep the program logic consistent with my design.

---

## **Diary Entry 2 — 15/05/26**

On this day, I focused on implementing the fare calculation and journey management features inside `JourneyManager`. I wrote the logic to calculate fares based on zones travelled, passenger type discounts, and peak or off-peak time bands. I also added the daily fare cap rules for different passenger categories.

I tested the program by adding multiple journeys to ensure the totals and caps were working correctly. Some errors appeared when handling totals, so I adjusted how values were stored and updated after each journey. This helped the system behave more accurately and match the expected results from my design.

---

## **Diary Entry 3 — 16/05/26**

This session focused on completing the reporting and filtering features using the `ReportManager` class. I implemented functions to filter journeys by passenger type, zone, time band, and fare range. I also created the daily summary report showing totals, averages, most expensive journey, and counts by passenger type.

After finishing the features, I tested the whole program from start to finish through the main menu. I fixed small issues with input handling and output formatting to make the program clearer and more user-friendly. This completed the main coding work planned for Milestone 3.
