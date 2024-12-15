package backend.entity;

import java.util.ArrayList;
import java.util.Arrays;

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
    private String[] stallCategories;

    /**
     * Constructor to instantiate {@code Stall} object.
     *
     * @param stallID         The ID of the store
     * @param stallName       The name of the store
     * @param stallCategories The categories of food associated with the store
     */
    public Stall(String stallID, String stallName, String[] stallCategories) {
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
     * A method to add a {@code Stall} object to the overall list.
     *
     * @param stall The {@code Stall} object to be added
     */
    public static void addStallToList(Stall stall) {
        stallList.add(stall);
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

    public String[] getStallCategories() {
        return stallCategories;
    }

    public void setStallCategories(String[] stallCategories) {
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
}
