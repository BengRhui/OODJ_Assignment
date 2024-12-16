package backend.entity;

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
    public static Stall getStall(String stallID) {

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
