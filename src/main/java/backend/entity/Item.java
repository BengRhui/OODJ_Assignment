package backend.entity;

import backend.file_io.ItemFileIO;
import backend.file_io.PictureIO;
import backend.notification.VendorNotification;

import java.io.File;
import java.util.*;
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
     * A method to retrieve the list of items based on vendor.
     *
     * @param vendor The vendor object that has association with the items
     * @return A list of filtered items
     */
    public static ArrayList<Item> getItemList(Vendor vendor) {

        // Return null if the input is null
        if (vendor == null) return null;

        // Get the associated stall
        Stall associatedStall = vendor.getStall();
        if (associatedStall == null) return null;

        // Filter the item list based on stall ID
        return getItemList(associatedStall);
    }

    /**
     * A method to retrieve the items associated to a stall.
     *
     * @param stall The stall object associated with the items
     * @return A list of items from the stall
     */
    public static ArrayList<Item> getItemList(Stall stall) {

        // Return null if the input is null
        if (stall == null) return null;

        // Filter the item list and sort them based on stall ID
        return getItemList().stream()
                .filter(item -> item.stall != null && item.stall.getStallID().equals(stall.getStallID()))
                .sorted(Comparator.comparing(Item::getItemID))
                .collect(Collectors.toCollection(ArrayList::new));
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
        if (name.isBlank() || price <= 0 || description.isBlank() || vendor == null) return false;

        // Return false if the name of the item matches with existing items of the same stall in the list
        boolean itemExist = itemList.stream()                                                    // Get the list of items
                .filter(item -> item.stall == null || item.stall.equals(vendor.getStall()))      // Filter the items to the ones in stall
                .anyMatch(item -> item.itemName.equalsIgnoreCase(name));                         // Check if item name exists in list
        if (itemExist) return false;

        // Create a new item
        Item newItem = new Item(
                Item.generateItemID(),
                name,
                vendor.getStall(),
                price,
                description
        );

        // If picture is uploaded successfully
        if (PictureIO.uploadVendorItemPicture(picture, newItem)) {

            // Add the item into list
            Item.addItemToList(newItem);

            // Write to file after modification
            ItemFileIO.writeFile();

            // Return true to indicate successfully adding a new item
            return true;
        }

        // Return false if the item picture could not be set
        return false;
    }

    /**
     * A method to delete all items related to the stall.
     *
     * @param stallID The ID of the stall
     * @return {@code true} if items are deleted successfully, else {@code false}
     */
    public static boolean deleteItem(String stallID) throws IllegalArgumentException {

        // Check if the stall ID is valid
        if (stallID == null || stallID.isBlank())
            throw new IllegalArgumentException("The stall ID cannot be empty or null.");

        // Find if there exist any items associated with the stall ID
        ArrayList<Item> itemsToBeRemoved = Item.getItemList().stream()
                .filter(item -> item.getStall() != null && item.getStall().getStallID().equals(stallID))
                .collect(Collectors.toCollection(ArrayList::new));
        if (itemsToBeRemoved.isEmpty()) return false;

        // Remove the items from the list
        for (Item item : itemsToBeRemoved) {
            if (item.deleteItem() != 1) return false;
        }

        // Return true for successful deletion
        return true;
    }

    /**
     * A method for vendors to modify the details of an item.
     *
     * @param name        The name of the item
     * @param price       The price of the item
     * @param description The description of the item
     * @param picture     THe picture associated with the item
     * @return Status is {@code true} if everything works well, else {@code wrong}
     */
    public boolean modifyItemDetails(String name, double price, String description, File picture) {

        // Make sure that the arguments are valid
        if (name.isBlank() || price <= 0 || description.isBlank()) return false;

        // Check if name is used by other items from the same stall
        boolean isNameRepeated = itemList.stream()
                .filter(item -> item.stall == null || item.stall.equals(this.stall) && !item.equals(this))
                .anyMatch(item -> item.itemName.equalsIgnoreCase(name));
        if (isNameRepeated) return false;

        // Update the picture and return false if unsuccessful
        if (!PictureIO.uploadVendorItemPicture(picture, this)) return false;

        // Modify the item details
        this.setItemName(name);
        this.setPrice(price);
        this.setDescription(description);

        // Write to file after modification
        ItemFileIO.writeFile();

        // Return true upon successful modification
        return true;
    }

    /**
     * A method to delete an item from the list
     *
     * @return {@code 1} if the item is deleted successfully
     * {@code 0} if the item is still in the order list
     * {@code -1} if the item picture could not be deleted
     * {@code -2} if the item cannot be removed from the item list
     */
    public int deleteItem() {

        // Stop deletion if the item is currently ordered by others
        boolean itemInOrder = Order.getOrderList().stream()                                 // Convert list to stream
                .filter(order -> order.getOrderStatus() != Order.OrderStatus.COMPLETED &&   // Avoid involving completed and cancelled orders
                        order.getOrderStatus() != Order.OrderStatus.CANCELLED)
                .anyMatch(                                                                  // Check if there is any orders matching condition
                        order -> order.getOrderItem().keySet().stream()                     // Condition is that:
                                .anyMatch(                                                  // Find if there's any match
                                        item -> item.getItemID().equals(this.itemID)        // For the items in each order
                                )
                );
        if (itemInOrder) return 0;

        // Delete the picture of the item
        if (!PictureIO.deleteItemPicture(this)) return -1;

        // Return false if the item was not found in the list (cannot be deleted)
        if (!Item.getItemList().remove(this)) return -2;

        // Write to file and return true after deletion
        ItemFileIO.writeFile();
        return 1;
    }

    /**
     * A method for manager to delete an item.
     *
     * @return {@code true} if the deletion is successful, else {@code false}
     */
    public boolean managerDeleteItem() {

        // Delete the item and return its value
        if (this.deleteItem() != 1) return false;

        // Create notification to notify vendor that the item is deleted
        return VendorNotification.createNewNotification(
                "Item " + this.itemID + " Deleted",
                "The item " + this.itemName + " (" + this.itemID + ") has been removed by the manager due to its inappropriate nature for sale on this platform.",
                this.stall
        );
    }

    /**
     * A method to calculate the price of an item according to their quantities,
     *
     * @param quantity The number of item involved
     * @return The calculated price of an item
     * @throws IllegalArgumentException Occurs when the amount for quantity is less than or equals to 0
     */
    public double getPrice(int quantity) throws IllegalArgumentException {

        // Throw an exception if quantity is wrong
        if (quantity <= 0) throw new IllegalArgumentException("The quantity cannot be less than or equal to 0.");

        // Return the unit price multiplied with quantity
        return this.getPrice() * quantity;
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
