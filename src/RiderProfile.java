import java.math.BigDecimal;

public class RiderProfile {

    private String name;
    private CityRideDataset.PassengerType passengerType;
    private String defaultPaymentOption;

    public RiderProfile(String name, CityRideDataset.PassengerType passengerType, String defaultPaymentOption) {
        this.name = name;
        this.passengerType = passengerType;
        this.defaultPaymentOption = defaultPaymentOption;
    }

    public String getName() {
        return name;
    }

    public CityRideDataset.PassengerType getPassengerType() {
        return passengerType;
    }

    public String getDefaultPaymentOption() {
        return defaultPaymentOption;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPassengerType(CityRideDataset.PassengerType passengerType) {
        this.passengerType = passengerType;
    }

    public void setDefaultPaymentOption(String defaultPaymentOption) {
        this.defaultPaymentOption = defaultPaymentOption;
    }

    public static RiderProfile createNewProfile(InputHelper input) {
        System.out.println("\n--- CREATE NEW PROFILE ---");

        String name = input.readText("Enter your name: ");

        System.out.println("Passenger type: 1=Adult  2=Student  3=Child  4=Senior Citizen");
        int ptChoice = input.readInt("Choose (1-4): ", 1, 4);

        CityRideDataset.PassengerType passengerType;
        if      (ptChoice == 1) passengerType = CityRideDataset.PassengerType.ADULT;
        else if (ptChoice == 2) passengerType = CityRideDataset.PassengerType.STUDENT;
        else if (ptChoice == 3) passengerType = CityRideDataset.PassengerType.CHILD;
        else                    passengerType = CityRideDataset.PassengerType.SENIOR_CITIZEN;

        String payment = input.readText("Enter default payment option (e.g. Card, Cash, Contactless): ");

        RiderProfile profile = new RiderProfile(name, passengerType, payment);
        System.out.println("Profile created for " + name + ".");
        return profile;
    }

    @Override
    public String toString() {
        return "Name: " + name
                + "  Passenger Type: " + passengerType
                + "  Default Payment: " + defaultPaymentOption;
    }
}