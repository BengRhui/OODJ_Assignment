package backend;

import java.util.ArrayList;

/**
 * Class {@code AdminFileIO} consists of methods to read and write files related to admin users.
 * @author Beng Rhui (TP068495)
 */
public class AdminFileIO extends UserFileIO {

    /**
     * Fixed variables to help in coding.
     */
    private static final String ADMIN_FILE_NAME = "admin_information.txt";
    private static final int NUMBER_OF_INFORMATION = 2;
    private static final int[] SPACING_SIZE = {5, 30};

    /**
     * A method to read both admin and credential files to create {@code Admin} objects.
     */
    @Override
    public void readFile() {
        
        // Retrieve information from admin file
        ArrayList<String[]> adminDataList = getListFromFile(ADMIN_FILE_NAME);

        // Loop through each admin record
        for (String[] data : adminDataList) {
            
            // Create an Admin object
            Admin newAdmin = createAdminObject(data);

            // Add the object to both Admin and User list
            Admin.addToAdminList(newAdmin);
            User.addUserToList(newAdmin);
        }
    }

    /**
     * A method to create {@code Admin} object by getting information from login credentials file
     * @param individualStringRecord String array consisting of admins' individual information
     * @return An {@code Admin} object
     */
    private static Admin createAdminObject(String[] individualStringRecord) {

        // Retrieve data from login credentials file
        ArrayList<String[]> loginCredentialsList = getListFromFile(CREDENTIALS_FILE_NAME);

        // Set ID and name from the string array
        String userID = individualStringRecord[0];
        String name = individualStringRecord[1];

        // Set email and password as null in default
        String email = null;
        String password = null;

        // Loop through the contents of login credentials file to obtain email and password
        for (String[] credential : loginCredentialsList) {

            // Continue the loop if the user ID does not match
            if (!credential[0].equals(userID)) {
                continue;
            }

            // If user ID matches, retrieve the corresponding email and password and end the loop
            email = credential[1];
            password = credential[2];
            break;
        }

        // Create and return the Admin object
        return new Admin(userID, email, password, name);
    }

    /**
     * A method to write admin information into file.
     */
    @Override
    public void writeFile() {

        // Retrieve the overall admin list
        ArrayList<Admin> adminList = Admin.getAdminList();

        // Create a local variable to store admin information after converted to string arrays
        ArrayList<String[]> informationArray = new ArrayList<>();

        // Loop through each admin
        for (Admin admin : adminList) {

            // Create a string array, add information into it and add to the ArrayList
            String[] individualInformation = new String[NUMBER_OF_INFORMATION];
            individualInformation[0] = admin.getUserID();
            individualInformation[1] = admin.getName();
            informationArray.add(individualInformation);
        }

        // Write the array into admin file
        writeListToFile(ADMIN_FILE_NAME, informationArray, SPACING_SIZE);
    }
}
