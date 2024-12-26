package backend.entity;

import backend.file_io.PictureIO;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
    public final static Item deliveryFees = new Item(
            "I001",
            "Delivery Fees",
            null,
            2,
            "Fees charged for delivery services"
    );
    private static ArrayList<Item> itemList = new ArrayList<>(List.of(deliveryFees));
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
     * A setter to set item list (used for reset purposes).
     *
     * @param list The new list to be set to replace the current list
     */
    public static void setItemList(ArrayList<Item> list) {
        itemList = list;
    }

    /**
     * A method to add {@code Item} objects into an overall list.
     *
     * @param item The {@code Item} objects to be added to list
     */
    public static void addItemToList(Item... item) {

        // Throws an error if there is no item passed into the argument, or a null item is passed into argument
        if (item.length == 0 || Arrays.stream(item).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Arguments should contain at least one Item object");
        }

        // Add all the items from the arguments into the list
        itemList.addAll(
                Arrays.asList(item)
        );
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
     * A method to automatically generate a new item ID.
     *
     * @return The new item ID generated
     */
    public static String generateItemID() {

        // Initialize an index
        int index = 1;

        // Get the list of item ID
        ArrayList<String> itemIDList = itemList.stream()              // Get the list of items
                .map(item -> item.itemID)                             // Map each item to their item ID
                .collect(Collectors.toCollection(ArrayList::new));    // Convert the list of ID into arrays

        // Begin loop
        while (true) {

            // Convert index into ID
            String newID = String.format("I%03d", index);

            // Check if ID is in the list
            if (!itemIDList.contains(newID)) {

                // Return the ID if it's not in the list
                return newID;
            }

            // Increase the index if the item ID is in the list
            index++;
        }
    }

    /**
     * A method to let vendors create a new item.
     *
     * @param name        The name of the item
     * @param price       The price of the item
     * @param description The description of the item
     * @param picture     The picture of the item
     * @param vendor      The vendor associated with the item
     * @return Returns {@code true} if item is created successfully, else {@code false}
     */
    public static boolean addNewVendorItem(String name, double price, String description, File picture, Vendor vendor) {

        // Return false if the arguments are invalid
        if (name.isBlank() || price <= 0 || description.isBlank() || picture == null || vendor == null) {
            return false;
        }

        // Return false if the name of the item matches with existing items of the same stall in the list
        boolean itemExist = itemList.stream()                              // Get the list of items
                .filter(item -> item.stall == null || item.stall.equals(vendor.getStall()))      // Filter the items to the ones in stall
                .anyMatch(item -> item.itemName.equalsIgnoreCase(name));   // Check if item name exists in list
        if (itemExist) return false;

        // Create a new item
        Item newItem = new Item(
                Item.generateItemID(),
                name,
                vendor.getStall(),
                price,
                description
        );

        // Return false if the item picture could not be set
        if (!PictureIO.uploadVendorItemPicture(picture, newItem)) return false;

        // Add the item into list
        Item.addItemToList(newItem);

        // Return true to indicate successfully adding a new item
        return true;
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
                "Stall: " + (stall == null ? "null" : "\n" + stall) + "\n" +
                "Price: " + price + "\n" +
                "Item Description: " + description;
    }
}
