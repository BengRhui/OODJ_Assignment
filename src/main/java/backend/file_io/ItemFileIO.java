package backend.file_io;

import backend.entity.Item;
import backend.entity.Stall;

import java.util.ArrayList;

/**
 * Class {@code ItemFileIO} contains the methods to read and write item-related files.
 *
 * @author Beng Rhui (TP068495)
 */
public class ItemFileIO extends FileIO {

    /**
     * Fixed variables to aid in coding.
     */
    private final static String ITEM_FILE_NAME = "item_details.txt";
    private final static int NUMBER_OF_INFORMATION_IN_FILE = 5;
    private final static int[] SPACING_SIZE = {5, 30, 5, 10, 150};

    /**
     * A method to read item file and initialize {@code Item} objects.
     */
    public static void readFile() {

        // Retrieve information from text file
        ArrayList<String[]> informationList = getListFromFile(ITEM_FILE_NAME);

        // Loop through each information
        for (String[] information : informationList) {

            // Create the item object based on the information and add to list
            Item newItem = createItemObject(information);
            Item.addItemToList(newItem);
        }
    }

    /**
     * A method to create {@code Item} objects based on string record from file.
     *
     * @param recordFromFile The string array obtained from file
     * @return The {@code Item} associated with the string array
     */
    public static Item createItemObject(String[] recordFromFile) {

        // Retrieve information from the string array
        String itemID = recordFromFile[0];
        String itemName = recordFromFile[1];
        Stall stall = Stall.getStall(recordFromFile[2]);
        double price = Double.parseDouble(recordFromFile[3]);
        String description = recordFromFile[4];

        // Create and return a new item object
        return new Item(itemID, itemName, stall, price, description);
    }

    /**
     * A method to write {@code Item} objects into file.
     */
    public static void writeFile() {

        // Retrieve the list of all item objects
        ArrayList<Item> itemList = Item.getItemList();

        // Create a list to store the information to be written to text file
        ArrayList<String[]> informationToFile = new ArrayList<>();

        // Loop through each item object
        for (Item item : itemList) {

            // Retrieve the information and add to list
            String[] record = new String[NUMBER_OF_INFORMATION_IN_FILE];
            record[0] = item.getItemID();
            record[1] = item.getItemName();
            record[2] = item.getStall().getStallID();
            record[3] = Double.toString(item.getPrice());
            record[4] = item.getDescription();
            informationToFile.add(record);
        }

        // Write the list to item file
        writeListToFile(ITEM_FILE_NAME, informationToFile, SPACING_SIZE);
    }
}
