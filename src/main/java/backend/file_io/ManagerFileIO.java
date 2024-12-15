package backend.file_io;

import backend.entity.Manager;

import java.util.ArrayList;

/**
 * Class {@code ManagerFileIO} contains the methods to read and write to manager file.
 *
 * @author Beng Rhui (TP068495)
 */
public class ManagerFileIO extends UserFileIO {

    /**
     * Fixed variables to help in coding.
     */
    private final static String MANAGER_FILE_NAME = "manager_information.txt";
    private final static int NUMBER_OF_INFO_IN_FILE = 2;
    private final static int[] SPACING_SIZE = {5, 50};

    /**
     * A method to create {@code Manager} objects.
     *
     * @param managerInformation The String array consisting of manager information
     * @return The {@code Manager} object created
     */
    public static Manager createManagerObject(String[] managerInformation) {

        // Retrieve information from string array
        String userID = managerInformation[0];
        String name = managerInformation[1];

        // Retrieve email and password
        String[] credentials = getUsernameAndPassword(userID);
        String email = credentials[0];
        String password = credentials[1];

        // Create and return a newly generated manager object
        return new Manager(userID, email, password, name);
    }

    /**
     * A method to read manager file and create manager objects
     */
    @Override
    public void readFile() {

        // Get list of managers from text file
        ArrayList<String[]> managerInformation = getListFromFile(MANAGER_FILE_NAME);

        // Loop through each record
        for (String[] record : managerInformation) {

            // Create a manager object based on the record and add to list
            Manager newManager = createManagerObject(record);
            Manager.addManagerToList(newManager);
        }
    }

    /**
     * A method to write manager objects into manager text file.
     */
    @Override
    public void writeFile() {

        // Get the list of manager objects
        ArrayList<Manager> managerList = Manager.getManagerList();

        // Declare a local variable to store the strings to be written to text file
        ArrayList<String[]> information = new ArrayList<>();

        // Loop through the list of manager objects
        for (Manager manager : managerList) {

            // Create a string and add it to the local variable
            String[] record = new String[NUMBER_OF_INFO_IN_FILE];
            record[0] = manager.getUserID();
            record[1] = manager.getName();
            information.add(record);
        }

        // Write the contents into the text file
        writeListToFile(MANAGER_FILE_NAME, information, SPACING_SIZE);
    }
}
