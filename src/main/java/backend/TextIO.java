package backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class {@code TextIO} contains all the methods to retrieve and write the contents of text files.
 */
public class TextIO {

    /**
     * The PARENT_PATH_TO_FILE variable contains the file path to the folder containing the text files
     */
    private final static String PARENT_PATH_TO_FILE = "src/main/resources/text_file/";

    /**
     * A method to read text files from directory
     *
     * @param fileName The name of the text file to be read
     * @return An ArrayList containing the contents of the text file.<br>
     * System exits automatically if the text file is not found.
     */
    public static ArrayList<String[]> readFile(String fileName) {

        // Obtain the path to the file
        String filePath = PARENT_PATH_TO_FILE + fileName.strip().toLowerCase();

        try {

            // Attempt to read file using FileReader and BufferedReader
            FileReader file = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(file);

            // Create local variables: array (store all lines) and string (store individual line during loop)
            ArrayList<String[]> fileContents = new ArrayList<>();
            String line;

            // Loop through each line in the text file
            while ((line = reader.readLine()) != null) {

                // Split each information and remove the trailing blank spaces
                String[] elementSeparated = line.split(";");
                for (int i = 0; i < elementSeparated.length; i++) {
                    elementSeparated[i] = elementSeparated[i].strip();
                }

                // Add the processed information into array
                fileContents.add(elementSeparated);
            }

            // Close the file and return the array
            file.close();
            return fileContents;

        } catch (IOException e) {

            // Print out the file name that is not available and exit the system immediately
            System.out.println(fileName + " file is not found.");
            System.exit(0);
        }

        return null;
    }
}
