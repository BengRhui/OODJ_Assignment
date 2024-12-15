package backend.entity;

import java.util.ArrayList;

/**
 * Class {@code Item} represents the item sold by each stall in the food court.
 *
 * @author Beng Rhui (TP068495)
 */
public class Item {

    /**
     * Attributes for the {@code Item} object.<br>
     * A list that contains all {@code Item} objects is also included.
     */
    private final static ArrayList<Item> itemList = new ArrayList<>();
    private String itemID;
    private String itemName;
    private Stall stall;
    private double price;
    private String description;

    /**
     * Constructor to instantiate the {@code Item} object.
     *
     * @param itemID      The ID of the item
     * @param itemName    The name of the item
     * @param stall       The stall associated with the item
     * @param price       The price of the item
     * @param description The description of the item
     */
    public Item(String itemID, String itemName, Stall stall, double price, String description) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.stall = stall;
        this.price = price;
        this.description = description;
    }

    /**
     * A method to return a list containing all {@code Item} objects.
     *
     * @return An ArrayList consisting of all {@code Item} objects
     */
    public static ArrayList<Item> getItemList() {
        return itemList;
    }

    /**
     * A method to add {@code Item} objects into an overall list.
     *
     * @param item The {@code Item} object to be added to list
     */
    public static void addItemToList(Item item) {
        itemList.add(item);
    }

    /**
     * A method to retrieve {@code Item} object by ID.
     *
     * @param itemID The ID of the item
     * @return The {@code Item} object associated with the ID
     */
    public static Item getItem(String itemID) {

        // Loop through the list of item objects
        for (Item item : itemList) {

            // Continue loop if item ID does not match
            if (!item.itemID.equals(itemID)) {
                continue;
            }

            // Return the respective item object if item ID matches
            return item;
        }

        // Return null if there is no matching ID
        return null;
    }

    /**
     * Getters and setters for the {@code Item} class.
     */
    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Stall getStall() {
        return stall;
    }

    public void setStall(Stall stall) {
        this.stall = stall;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * A method to print out all information of the {@code Item} object.
     *
     * @return String representation of the {@code Item} object
     */
    @Override
    public String toString() {
        return "Item ID: " + itemID + "\n" +
                "Item Name: " + itemName + "\n" +
                "Stall: " + "\n" +
                stall.toString() + "\n" +
                "Price: " + price + "\n" +
                "Item Description: " + description;
    }
}
