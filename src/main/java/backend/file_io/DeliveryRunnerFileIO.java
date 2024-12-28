package backend.file_io;

import backend.entity.DeliveryRunner;

import java.util.ArrayList;

/**
 * Class {@code DeliveryRunnerFileIO} contains the methods to read and write delivery runner files
 *
 * @author Beng Rhui (TP068495)
 */
public class DeliveryRunnerFileIO extends UserFileIO {

    /**
     * Fixed variables to help in coding
     */
    public final static String RUNNER_FILE_NAME = "delivery_runner_information.txt";
    public final static int NUMBER_OF_INFO_IN_FILE = 3;
    public final static int[] SPACING_SIZE = {5, 50, 15};

    /**
     * A method to create delivery runner objects based on records stored as string array.
     *
     * @param individualRecord The string array containing the delivery runner's information
     * @return The {@code DeliveryRunner} object being created
     */
    public static DeliveryRunner createDeliveryRunnerObject(String[] individualRecord) {

        // Retrieve information from the string array
        String userID = individualRecord[0];
        String name = individualRecord[1];
        String contactNumber = individualRecord[2];

        // Retrieve email and password
        String[] credentials = getUsernameAndPassword(userID);
        String email = credentials[0];
        String password = credentials[1];

        // Create delivery runner object
        return new DeliveryRunner(userID, email, password, name, contactNumber);
    }

    /**
     * A method to read delivery runner file and initialize the delivery runner objects.
     */
    @Override
    public void readFile() {

        // Reset list before reading files
        DeliveryRunner.getDeliveryRunnerList().clear();

        // Get list of runners from text file
        ArrayList<String[]> runnerList = getListFromFile(RUNNER_FILE_NAME);

        // Loop through the runners
        for (String[] runnerRecord : runnerList) {

            // Create the runners and add them to list
            DeliveryRunner newRunner = createDeliveryRunnerObject(runnerRecord);
            DeliveryRunner.addToRunnerList(newRunner);
        }

        // Initialize a HashMap that records the availability of runners
        DeliveryRunner.initializeAvailabilityList();
    }

    /**
     * A method to write information into delivery runner file.
     */
    @Override
    public void writeFile() {

        // Get the list of delivery runners
        ArrayList<DeliveryRunner> runnerList = DeliveryRunner.getDeliveryRunnerList();

        // Instantiate a local variable to store the data to be written to file
        ArrayList<String[]> information = new ArrayList<>();

        // Loop through each delivery runner
        for (DeliveryRunner runner : runnerList) {

            // Retrieve information to be stored and add to array
            String[] runnerInformation = new String[NUMBER_OF_INFO_IN_FILE];
            runnerInformation[0] = runner.getUserID();
            runnerInformation[1] = runner.getName();
            runnerInformation[2] = runner.getContactNumber();
            information.add(runnerInformation);
        }

        // Write the information into the file
        writeListToFile(RUNNER_FILE_NAME, information, SPACING_SIZE);
    }
}
