package backend.entity;

import java.util.ArrayList;

/**
 * Class {@code Item} represents the item sold by each stall in the food court.
 *
 * @author Beng Rhui (TP068495)
 */
public class Item {

    private final static ArrayList<Item> itemList = new ArrayList<>();
    /**
     * Attributes for the {@code Item} object.<br>
     * A list that contains all {@code Item} objects is also included.
     */
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
     * A method to add {@code Item} objects into an overall list.
     *
     * @param item The {@code Item} object to be added to list
     */
    public void addItemToList(Item item) {
        itemList.add(item);
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
