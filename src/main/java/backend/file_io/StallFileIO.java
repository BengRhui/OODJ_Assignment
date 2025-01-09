package backend.file_io;

import backend.entity.Stall;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Class {@code StallFileIO} contains methods to read and write information regarding stall.
 *
 * @author Beng Rhui (TP068495)
 */
public class StallFileIO extends FileIO {

    /**
     * Fixed variables to help in read and write files.
     */
    public final static String STALL_FILE_NAME = "stall_information.txt";
    public final static int NUMBER_OF_INFORMATION_IN_FILE = 3;
    public final static int[] SPACING_SIZE = {5, 40, 50};

    /**
     * A method to read from stall file and create {@code Stall} objects.
     */
    public static void readFile() {

        // Reset list before reading files
        Stall.getStallList().clear();

        // Get stall information from text file
        ArrayList<String[]> informationFromFile = getListFromFile(STALL_FILE_NAME);

        // Loop through each information
        for (String[] record : informationFromFile) {

            // Create a stall object and add to list
            Stall newStall = createStallObject(record);
            Stall.addStallToList(newStall);
        }
    }

    /**
     * A method to create {@code Stall} objects based on string array from text file
     *
     * @param recordFromFile The string record from text file
     * @return The {@code Stall} object created
     */
    public static Stall createStallObject(String[] recordFromFile) {

        // Retrieve information from the string array
        String stallID = recordFromFile[0];
        String stallName = recordFromFile[1];

        // Convert stall categories from string to StallCategory type
        String[] unformattedStallCategories = recordFromFile[2].split(",");                  // Retrieve categories
        Arrays.sort(unformattedStallCategories);                                             // Sort the categories
        Stall.StallCategories[] stallCategories = Arrays.stream(unformattedStallCategories)  // Pass into array stream
                .map(String::strip)                                                          // Strip each string
                .map(Stall.StallCategories::generateFromString)                              // Map to correct category
                .toArray(Stall.StallCategories[]::new);                                      // Return as string array

        // Create a stall object
        return new Stall(stallID, stallName, stallCategories);
    }

    /**
     * A method to write {@code Stall} objects into file.
     */
    public static void writeFile() {

        // Get all stall objects
        ArrayList<Stall> stallList = Stall.getStallList();

        // Create a local variable to store information to be written to text file
        ArrayList<String[]> informationToFile = new ArrayList<>();

        // Loop through each stall
        for (Stall stall : stallList) {

            // Retrieve information from stall object
            String[] record = new String[NUMBER_OF_INFORMATION_IN_FILE];
            record[0] = stall.getStallID();
            record[1] = stall.getStallName();

            // Transform array to string, then remove the leading and ending braces
            String categoryList = Arrays.toString(stall.getStallCategories());
            record[2] = categoryList.substring(1, categoryList.length() - 1);

            // Add to list
            informationToFile.add(record);
        }

        // Write the information to file
        writeListToFile(STALL_FILE_NAME, informationToFile, SPACING_SIZE);
    }
}
