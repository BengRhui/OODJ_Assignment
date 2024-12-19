package backend;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class {@code Stall} is used to represent the different types of stalls in the food court.
 *
 * @author Beng Rhui (TP068495)
 */
public class Stall {

    private final static ArrayList<Stall> stallList = new ArrayList<>();
    /**
     * Attributes of a {@code Stall} object.<br>
     * A list that consists of all {@code Stall} object is also included.
     */
    private String storeID;
    private String storeName;
    private String[] storeCategories;

    /**
     * Constructor to instantiate {@code Stall} object.
     *
     * @param storeID         The ID of the store
     * @param storeName       The name of the store
     * @param storeCategories The categories of food associated with the store
     */
    public Stall(String storeID, String storeName, String[] storeCategories) {
        this.storeID = storeID;
        this.storeName = storeName;
        this.storeCategories = storeCategories;
        addStallToList(this);
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
     * Getters and setters for {@code Stall} class.
     */
    public String getStoreID() {
        return storeID;
    }

    public void setStoreID(String storeID) {
        this.storeID = storeID;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String[] getStoreCategories() {
        return storeCategories;
    }

    public void setStoreCategories(String[] storeCategories) {
        this.storeCategories = storeCategories;
    }

    /**
     * A method to print out the information of {@code Stall} object.
     *
     * @return String representation of the {@code Stall} object
     */
    @Override
    public String toString() {
        return "Store ID: " + storeID + "\n" +
                "Store Name: " + storeName + "\n" +
                "Store Categories: " + Arrays.toString(storeCategories);
    }
}
