import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonHandler {

    // File name for storing the rider profile
    private static final String PROFILE_FILE = "riderProfile.json";

    // Save the rider profile to a JSON file
    public static void saveProfile(RiderProfile profile) {

        // Create the JSON text
        String json = "{\n"
                + "  \"name\": \"" + profile.getName() + "\",\n"
                + "  \"passengerType\": \"" + profile.getPassengerType() + "\",\n"
                + "  \"defaultPaymentOption\": \"" + profile.getDefaultPaymentOption() + "\"\n"
                + "}";

        try (FileWriter writer = new FileWriter(PROFILE_FILE)) {

            writer.write(json);
            System.out.println("Profile saved successfully.");

        } catch (IOException e) {

            System.out.println("Error saving profile: " + e.getMessage());
        }
    }

    // Load the rider profile from the JSON file
    public static RiderProfile loadProfile() {

        try {

            // Step 1: Check if the file exists
            if (!Files.exists(Paths.get(PROFILE_FILE))) {
                System.out.println("No saved profile found.");
                return null;
            }

            // Step 2: Read the file
            String json = Files.readString(Paths.get(PROFILE_FILE));

            // Step 3: Get each value
            String riderName = getValue(json, "name");
            String passengerType = getValue(json, "passengerType");
            String paymentMethod = getValue(json, "defaultPaymentOption");

            // Step 4: Check for missing values
            if (riderName == null || passengerType == null || paymentMethod == null) {
                System.out.println("Profile file is incomplete.");
                return null;
            }

            // Step 5: Convert passenger type into enum
            CityRideDataset.PassengerType type =
                    CityRideDataset.PassengerType.valueOf(passengerType);

            // Step 6: Return the RiderProfile object
            return new RiderProfile(riderName, type, paymentMethod);

        } catch (IOException e) {

            System.out.println("Error loading profile: " + e.getMessage());
            return null;

        } catch (IllegalArgumentException e) {

            System.out.println("Invalid passenger type.");
            return null;
        }
    }

    // (Anthropic, 2026)
    // AI-assisted: Claude explained a simple way to extract values
    // from a manually created JSON string without using a JSON library.
    private static String getValue(String json, String fieldName) {

        // Create the text to search for
        String key = "\"" + fieldName + "\": \"";

        // Find where the value starts
        int start = json.indexOf(key);

        if (start == -1) {
            return null;
        }

        start = start + key.length();

        // Find where the value ends
        int end = json.indexOf("\"", start);

        if (end == -1) {
            return null;
        }

        // Return the value
        return json.substring(start, end);
    }
}