import java.math.BigDecimal;

public class SettingsManager {

    private String adminPassword;

    // discount rates per passenger type
    private BigDecimal discountAdult;
    private BigDecimal discountStudent;
    private BigDecimal discountChild;
    private BigDecimal discountSenior;

    // daily caps per passenger type
    private BigDecimal capAdult;
    private BigDecimal capStudent;
    private BigDecimal capChild;
    private BigDecimal capSenior;

    private boolean usingDefaults;

    public SettingsManager() {
        loadSettings();
    }

    private void loadSettings() {
        loadDefaults();
    }




    private void loadDefaults() {
        usingDefaults = true;
        adminPassword = "admin123";

        discountAdult   = new BigDecimal("0.00");
        discountStudent = new BigDecimal("0.25");
        discountChild   = new BigDecimal("0.50");
        discountSenior  = new BigDecimal("0.30");

        capAdult   = new BigDecimal("12.00");
        capStudent = new BigDecimal("9.00");
        capChild   = new BigDecimal("6.00");
        capSenior  = new BigDecimal("8.40");

        System.out.println("No settings file found - using safe default values.");
    }

    public boolean isUsingDefaults() {
        return usingDefaults;
    }

    public String getAdminPassword() {
        return adminPassword;
    }


    public BigDecimal getDiscountRate(CityRideDataset.PassengerType pt) {
        switch (pt) {
            case ADULT:
                return discountAdult;
            case STUDENT:
                return discountStudent;
            case CHILD:
                return discountChild;
            default:
                return discountSenior;
        }
    }

    public BigDecimal getDailyCap(CityRideDataset.PassengerType pt) {
        switch (pt) {
            case ADULT:
                return capAdult;
            case STUDENT:
                return capStudent;
            case CHILD:
                return capChild;
            default:
                return capSenior;
        }
    }


    // (Anthropic,2026)
    //AI-assisted: Claude helped identify and error in the validation and update logic
    //For the discount rate method
    // lets Admin update a daily cap - validated before being applied
    public boolean updateDailyCap(CityRideDataset.PassengerType pt, BigDecimal newCap) {
        if (newCap == null || newCap.compareTo(BigDecimal.ZERO) <= 0) {
            System.out.println("Invalid cap value - must be greater than 0. Change not saved.");
            return false;
        }
        switch (pt) {
            case ADULT:
                capAdult = newCap;
                break;
            case STUDENT:
                capStudent = newCap;
                break;
            case CHILD:
                capChild = newCap;
                break;
            default:
                capSenior = newCap;
                break;
        }
        System.out.println("Daily cap for " + pt + " updated to £" + newCap);
        return true;
    }

    // lets Admin update a discount rate
    public boolean updateDiscountRate(CityRideDataset.PassengerType pt, BigDecimal newRate) {
        if (newRate == null || newRate.compareTo(BigDecimal.ZERO) < 0 || newRate.compareTo(BigDecimal.ONE) > 0) {
            System.out.println("Invalid discount rate - must be between 0 and 1. Change not saved.");
            return false;
        }
        switch (pt) {
            case ADULT:
                discountAdult = newRate;
                break;
            case STUDENT:
                discountStudent = newRate;
                break;
            case CHILD:
                discountChild = newRate;
                break;
            default:
                discountSenior = newRate;
                break;
        }
        System.out.println("Discount rate for " + pt + " updated to " + newRate);
        return true;
    }

    // shows the admin the current active settings
    public void displaySettings() {
        System.out.println("\n--- CURRENT SETTINGS ---");

        System.out.println("Source: " + (usingDefaults ? "Safe defaults" : "Settings file"));
        System.out.println("\nDiscount Rates:");
        System.out.println("  ADULT: " + discountAdult);
        System.out.println("  STUDENT: " + discountStudent);
        System.out.println("  CHILD: " + discountChild);
        System.out.println("  SENIOR_CITIZEN: " + discountSenior);


        System.out.println("\nDaily Caps:");
        System.out.println("  ADULT: £" + capAdult);
        System.out.println("  STUDENT: £" + capStudent);
        System.out.println("  CHILD: £" + capChild);
        System.out.println("  SENIOR_CITIZEN: £" + capSenior);
    }
}
