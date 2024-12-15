package backend.file_io;

import java.io.*;
import java.util.ArrayList;

/**
 * Class {@code FileIO} contains the general methods to retrieve and write the contents of text files.
 */
public class FileIO {

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
    protected static ArrayList<String[]> getListFromFile(String fileName) {

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
            System.out.println("Error: " + fileName + " file is not found.");
            System.exit(1);
        }

        return null;
    }

    /**
     * A method to write contents into text files. System exits immediately if there is error in writing files.
     *
     * @param fileName     The name of the text file to be written to
     * @param fileContents The contents to be written
     * @param spacingSize  The empty spaces reserved to make text file tidy
     */
    protected static void writeListToFile(String fileName, ArrayList<String[]> fileContents, int[] spacingSize) {

        // The path to the text file is specified
        String filePath = PARENT_PATH_TO_FILE + fileName.strip().toLowerCase();

        try {

            // Check if the size of both arrays are the same (if not there'll be a problem with the following code)
            if (fileContents.getFirst().length != spacingSize.length) {
                throw new IllegalArgumentException();
            }

            // Creating objects to write files
            FileWriter file = new FileWriter(filePath);
            BufferedWriter writer = new BufferedWriter(file);

            // A local variable to store the line to be written
            StringBuilder inputLine = new StringBuilder();

            // Loop through each information in the array
            for (String[] line : fileContents) {
                for (int i = 0; i < line.length; i++) {

                    // Specify the format and format the string into the intended format
                    String format = "%-" + spacingSize[i] + "s";
                    String formattedLine = String.format(format, line[i]);

                    // Add the information to StringBuilder, if not the last one, then add ";" as separator
                    inputLine.append(formattedLine);
                    if (i == line.length - 1) {
                        break;
                    }
                    inputLine.append("; ");
                }

                // Insert the line into the text file and go to the next line
                writer.write(inputLine.toString());
                writer.newLine();

                // Reset StringBuilder
                inputLine.setLength(0);
            }

            // Close file after operation
            writer.close();

        } catch (IOException ex) {

            // Print error and exit system if there's error with file operations
            System.out.println("Error while writing " + fileName);
            System.exit(1);

        } catch (IllegalArgumentException ex) {

            // Print error for different array sizes
            System.out.println("Error: The size of content array does not match with the size of spacing array.");
            System.exit(1);
        }

    }
}
