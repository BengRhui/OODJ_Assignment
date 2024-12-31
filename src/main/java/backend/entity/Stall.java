package backend.entity;

import backend.file_io.StallFileIO;
import backend.notification.VendorNotification;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Class {@code Stall} is used to represent the different types of stalls in the food court.
 *
 * @author Beng Rhui (TP068495)
 */
public class Stall {

    /**
     * Attributes of a {@code Stall} object.<br>
     * A list that consists of all {@code Stall} object is also included.
     */
    private final static ArrayList<Stall> stallList = new ArrayList<>();
    private String stallID;
    private String stallName;
    private StallCategories[] stallCategories;

    /**
     * Constructor to instantiate {@code Stall} object.
     *
     * @param stallID         The ID of the store
     * @param stallName       The name of the store
     * @param stallCategories The categories of food associated with the store
     */
    public Stall(String stallID, String stallName, StallCategories[] stallCategories) {
        this.stallID = stallID;
        this.stallName = stallName;
        Arrays.sort(stallCategories);
        this.stallCategories = stallCategories;
    }

    /**
     * A method to return the overall list consisting of all {@code Stall} objects.
     *
     * @return An ArrayList with all {@code Stall} objects
     */
    public static ArrayList<Stall> getStallList() {
        return stallList;
    }

    /**
     * A method to add {@code Stall} objects to the overall list.
     *
     * @param stall The {@code Stall} objects to be added
     */
    public static void addStallToList(Stall... stall) {

        // Throws an error if there is no stall passed into the argument, or a null stall is passed into argument
        if (stall.length == 0 || Arrays.stream(stall).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Arguments should contain at least one Stall object");
        }

        // Add all the stalls from the arguments into the list
        stallList.addAll(
                Arrays.asList(stall)
        );
    }

    /**
     * A method to retrieve the associated {@code Store} object based on store ID.
     *
     * @param stallID The ID of the stall
     * @return The {@code Stall} object associated with the ID
     */
    public static Stall getStallByID(String stallID) {

        // Loop through the list of stores
        for (Stall stall : stallList) {

            // Continue loop if stall ID does not match
            if (!stall.getStallID().equals(stallID)) {
                continue;
            }

            // Return the associated stall object if ID matches
            return stall;
        }

        // Return null if no ID matches
        return null;
    }

    /**
     * A method to retrieve the corresponding stall by stall name.
     *
     * @param stallName The name of the stall
     * @return The {@code Stall} object associated, returns {@code null} if no stall is found
     */
    public static Stall getStallByName(String stallName) throws IllegalArgumentException {

        // Throw an exception if the input is erroneous
        if (stallName == null || stallName.isBlank())
            throw new IllegalArgumentException("Input should not be null or blank.");

        return stallList.stream()                                                   // Convert list into a stream
                .filter(stall -> stall.stallName.equalsIgnoreCase(stallName))       // Filter the matching stall
                .findFirst()                                                        // Get the stall
                .orElse(null);                                                      // Returns null if stall is not found
    }

    /**
     * A method to retrieve the list of vendors of a specific stall
     *
     * @param stall The specific stall containing vendors
     * @return The list of vendors associated with the stall
     */
    public static ArrayList<Vendor> getVendors(Stall stall) {

        // Throw exceptions if the value of stall passed in is null
        if (stall == null) {
            throw new IllegalArgumentException("Stall cannot be null");
        }

        // Create a new list to store the associated vendors
        ArrayList<Vendor> vendors = new ArrayList<>();

        // Loop through the list of vendors
        for (Vendor vendor : Vendor.getVendorList()) {

            // Continue loop if the stall ID does not match
            if (!vendor.getStall().getStallID().equals(stall.getStallID())) {
                continue;
            }

            // Add vendors to list if the stall ID matches
            vendors.add(vendor);
        }

        // Return the list of vendors
        return vendors;
    }

    /**
     * A method to help check if the stall name has been used by another stall. This is critical as we have to ensure that the names are unique.
     *
     * @param stallName The input name to be checked
     * @return {@code true} if the name is not used, else {@code false}
     */
    public static boolean checkIfStallNameIsUsed(String stallName) {

        // Check if the name exists in the list. Return true if it does not exist, otherwise false.
        return stallList.stream()
                .anyMatch(stall -> stall.stallName.equalsIgnoreCase(stallName));
    }

    /**
     * A method to generate new stall ID.
     *
     * @return The new stall ID generated
     */
    public static String generateNewID() {

        // Declare variables to record index
        int index = 1;

        // Start a loop
        while (true) {

            // Get the generated ID
            String generatedID = String.format("S%03d", index);

            // Check if the generated ID is in the stall list
            boolean generatedIDExists = stallList.stream()                      // Get the list of stalls
                    .anyMatch(stall -> stall.stallID.equals(generatedID));      // Check if there is any match with the existing stall ID

            // If the ID does not exist among the stall list, return that ID
            if (!generatedIDExists) return generatedID;

            // Increment the index if there is a match
            index++;
        }
    }

    /**
     * A method to create a new stall.
     *
     * @param stallID         The ID of the stall
     * @param stallName       The name of the stall
     * @param stallCategories The categories of the stall
     * @return {@code 1} if the stall is created successfully<br>
     * {@code 0} if there exist empty values
     * {@code -1} if the name has been used by other stalls
     * {@code -2} if there exists any invalid category (should not happen but included just in case)
     */
    public static int createNewStall(
            String stallID,
            String stallName,
            String[] stallCategories
    ) {

        // Check if there is any empty values
        if (stallID == null || stallID.isBlank() || stallName == null || stallName.isBlank() || stallCategories == null || stallCategories.length == 0)
            return 0;

        // Check if the name has been used by another stall
        if (checkIfStallNameIsUsed(stallName)) return -1;

        // Convert the categories into StallCategories type
        StallCategories[] addedCategories = Arrays.stream(stallCategories)
                .map(StallCategories::generateFromString)
                .toArray(StallCategories[]::new);

        // Check if there is any invalid categories
        boolean invalidCategory = Arrays.stream(addedCategories).anyMatch(Objects::isNull);
        if (invalidCategory) return -2;

        // Create a new stall and add to list
        Stall newStall = new Stall(stallID, stallName, addedCategories);
        addStallToList(newStall);

        // Write to file
        StallFileIO.writeFile();

        // Return true for successful operation
        return 1;
    }

    /**
     * A method to modify the details of a stall.
     *
     * @param stallName       The name of the stall
     * @param stallCategories The category of the stall
     * @return {@code 1} if the stall is created successfully<br>
     * {@code 0} if there exist empty values
     * {@code -1} if the name has been used by other stalls
     * {@code -2} if there exists any invalid category (should not happen but included just in case)
     * {@code -3} if the notification cannot be created
     */
    public int modifyStall(
            String stallName,
            String[] stallCategories
    ) {

        // Check if there exist any empty values (0)
        if (stallName == null || stallName.isBlank() || stallCategories == null || stallCategories.length == 0)
            return 0;

        // Check if the stall name is used by other stalls (-1)
        if (!stallName.equalsIgnoreCase(this.stallName) && checkIfStallNameIsUsed(stallName)) return -1;

        // Convert categories into StallCategory type
        StallCategories[] categories = Arrays.stream(stallCategories)
                .map(StallCategories::generateFromString)
                .toArray(StallCategories[]::new);

        // Check if the categories are appropriate (-2)
        boolean consistNull = Arrays.stream(categories)
                .anyMatch(Objects::isNull);
        if (consistNull) return -2;

        // Create a notification to inform that the details have been changed
        boolean createNotification = VendorNotification.createNewNotification(
                "Stall Information Updated",
                "The stall information has been updated successfully.",
                this
        );
        if (!createNotification) return -3;

        // Modify the attributes
        this.setStallName(stallName);
        this.setStallCategories(categories);

        // Write to file
        StallFileIO.writeFile();

        // Return 1 to indicate successful modification
        return 1;
    }

    /**
     * A method to delete the current stall. If there are still vendors associated with the stall, the operation will not proceed.
     * @return {@code true} if the stall is deleted successfully, else {@code false}
     */
    public boolean deleteStall() {

        // Check if there is any vendors associated with the stall. If yes, the stall cannot be deleted
        boolean stallHasVendors = Vendor.getVendorList().stream()
                .anyMatch(vendor -> vendor.getStall().getStallID().equals(this.stallID));
        if (stallHasVendors) return false;

        // Remove all the items associated with the stall
        boolean deleteItem = Item.deleteItem(this.stallID);
        if (!deleteItem) return false;

        // Change all the stall attribute to null for relevant orders
        boolean changeStatus = Order.changeStallToNull(this.stallID);
        if (!changeStatus) return false;

        // Remove stall from list
        boolean removeFromList = stallList.remove(this);
        if (!removeFromList) return false;

        // Write to file
        StallFileIO.writeFile();

        // Return true for successful deletion
        return true;
    }

    /**
     * Getters and setters for {@code Stall} class.
     */
    public String getStallID() {
        return stallID;
    }

    public void setStallID(String stallID) {
        this.stallID = stallID;
    }

    public String getStallName() {
        return stallName;
    }

    public void setStallName(String stallName) {
        this.stallName = stallName;
    }

    public StallCategories[] getStallCategories() {
        return stallCategories;
    }

    public void setStallCategories(StallCategories[] stallCategories) {
        Arrays.sort(stallCategories);               // Sort before added as attributes
        this.stallCategories = stallCategories;
    }

    /**
     * A method to print out the information of {@code Stall} object.
     *
     * @return String representation of the {@code Stall} object
     */
    @Override
    public String toString() {
        return "Stall ID: " + stallID + "\n" +
                "Stall Name: " + stallName + "\n" +
                "Stall Categories: " + Arrays.toString(stallCategories);
    }

    /**
     * Enum {@code StallCategories} represents the different types of categories that a stall can have.
     */
    public enum StallCategories {

        /**
         * The different categories of stall
         */
        HALAL, NON_HALAL, LOCAL, SNACKS, KOREAN, HEALTHY, CHINESE, NOODLES, JAPANESE, BEVERAGES, RICE, INDIAN,
        FAST_FOOD, WESTERN, DESSERT, MALAY;

        /**
         * The list containing all stall categories
         */
        public final static String[] STALL_CATEGORIES = Arrays.stream(StallCategories.values())   // Get the fields
                .map(StallCategories::toString)                                                   // Get their toString
                .toArray(String[]::new);                                                          // Return as array

        /**
         * A method to generate stall categories based on string input
         *
         * @param category The string input corresponding to the category
         * @return The {@code StallCategories} corresponding to the string
         */
        public static StallCategories generateFromString(String category) {
            return Arrays.stream(StallCategories.values())                          // Get the fields
                    .filter(field -> field.toString().equalsIgnoreCase(category))   // Find the matching field
                    .findFirst()                                                    // Find the first occurrence
                    .orElse(null);                                                  // Return null if not found
        }

        /**
         * A method to return the string value of stall categories.
         *
         * @return The string representation of stall categories
         */
        @Override
        public String toString() {
            return switch (this) {
                case HALAL -> "Halal";
                case NON_HALAL -> "Non-Halal";
                case LOCAL -> "Local";
                case SNACKS -> "Snacks";
                case KOREAN -> "Korean";
                case HEALTHY -> "Healthy";
                case CHINESE -> "Chinese";
                case NOODLES -> "Noodles";
                case JAPANESE -> "Japanese";
                case BEVERAGES -> "Beverages";
                case RICE -> "Rice";
                case INDIAN -> "Indian";
                case FAST_FOOD -> "Fast Food";
                case WESTERN -> "Western";
                case DESSERT -> "Dessert";
                case MALAY -> "Malay";
            };
        }
    }
}
