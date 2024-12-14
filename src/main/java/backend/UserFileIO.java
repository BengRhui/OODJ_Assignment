package backend;

import java.util.ArrayList;

/**
 * Abstract class {@code UserFileIO} contains the general methods to read and write files related to users.
 * @author Beng Rhui (TP068495)
 */
public abstract class UserFileIO extends FileIO {

    /**
     * Fixed variables to aid in writing the login credentials file.
     */
    protected static final String CREDENTIALS_FILE_NAME = "login_credentials.txt";
    private static final int INFORMATION_NUMBER_IN_CREDENTIALS = 3;
    private static final int[] SPACING_SIZE = {5, 30, 20};

    /**
     * A method to write the login credentials file.
     */
    public static void writeCredentialsFile() {

        // Get the list of all users in the system
        ArrayList<User> array = User.getUserList();

        // Create a local variable to store the formatted information
        ArrayList<String[]> information = new ArrayList<>();

        // Loop through each user and put the information into a String array
        for (User user : array) {
            String[] individualInfo = new String[INFORMATION_NUMBER_IN_CREDENTIALS];
            individualInfo[0] = user.getUserID();
            individualInfo[1] = user.getEmail();
            individualInfo[2] = user.getPassword();
            information.add(individualInfo);
        }

        // Sort the array in ascending order based on user ID
        information.sort((userOne, userTwo) -> userOne[0].compareToIgnoreCase(userTwo[0]));

        // Write the information into the login credentials file
        writeListToFile(CREDENTIALS_FILE_NAME, information, SPACING_SIZE);
    }

    /**
     * A method to return the email and password of users based on user ID.
     * @param userID The ID of user
     * @return A string array consisting of email and password of user
     */
    public static String[] getUsernameAndPassword(String userID) {

        // Get the list of credentials from credentials file
        ArrayList<String[]> credentialsList = getListFromFile(CREDENTIALS_FILE_NAME);

        // Set email and password as null in default
        String email = null;
        String password = null;

        // Loop through the credential list
        for (String[] credentials : credentialsList) {

            // Continue the loop if user ID does not match
            if (!credentials[0].equals(userID)) {
                continue;
            }

            // Retrieve email and password if user ID matches, then end the loop
            email = credentials[1];
            password = credentials[2];
            break;
        }

        // Return the email and password
        return new String[]{email, password};
    }

    /**
     * Abstract methods to be modified in different IO classes
     */
    public abstract void readFile();

    public abstract void writeFile();
}
