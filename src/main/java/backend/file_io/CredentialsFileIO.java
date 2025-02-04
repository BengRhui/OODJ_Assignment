package backend.file_io;

import backend.entity.User;

import java.util.ArrayList;

/**
 * Class {@code CredentialsFileIO} contains the general methods to read and write login credentials file.
 *
 * @author Beng Rhui (TP068495)
 */
public class CredentialsFileIO extends FileIO {

    /**
     * Fixed variables to aid in writing the login credentials file.
     */
    public static final String CREDENTIALS_FILE_NAME = "login_credentials.txt";
    public static final int INFORMATION_NUMBER_IN_CREDENTIALS = 3;
    public static final int[] SPACING_SIZE = {5, 30, 20};

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
}
