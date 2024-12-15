package backend.file_io;

import java.util.ArrayList;

/**
 * Abstract class {@code UserFileIO} contains the general methods to read and write files related to users.
 *
 * @author Beng Rhui (TP068495)
 */
public abstract class UserFileIO extends FileIO {

    /**
     * Fixed variables to aid in coding.
     */
    private static final String CREDENTIALS_FILE_NAME = "login_credentials.txt";

    /**
     * A general method to return the email and password of users based on user ID.
     *
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
