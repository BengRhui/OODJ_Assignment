package backend.file_io;

import backend.entity.Admin;
import backend.entity.User;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Class {@code AdminFileIO} consists of methods to read and write files related to admin users.
 *
 * @author Beng Rhui (TP068495)
 */
public class AdminFileIO extends UserFileIO {

    /**
     * Fixed variables to help in coding.
     */
    public static final String ADMIN_FILE_NAME = "admin_information.txt";
    public static final int NUMBER_OF_INFORMATION = 2;
    public static final int[] SPACING_SIZE = {5, 30};

    /**
     * A method to create {@code Admin} object by getting information from login credentials file
     *
     * @param individualStringRecord String array consisting of admins' individual information
     * @return An {@code Admin} object
     */
    private static Admin createAdminObject(String[] individualStringRecord) {

        // Set ID and name from the string array
        String userID = individualStringRecord[0];
        String name = individualStringRecord[1];

        // Retrieve email and password
        String[] loginCredentials = getUsernameAndPassword(userID);
        String email = loginCredentials[0];
        String password = loginCredentials[1];

        // Create and return the Admin object
        return new Admin(userID, email, password, name);
    }

    /**
     * A method to read both admin and credential files to create {@code Admin} objects.
     */
    @Override
    public void readFile() {

        // Reset list before reading files
        Admin.getAdminList().clear();

        // Retrieve information from admin file
        ArrayList<String[]> adminDataList = getListFromFile(ADMIN_FILE_NAME);

        // Loop through each admin record
        for (String[] data : adminDataList) {

            // Create an Admin object
            Admin newAdmin = createAdminObject(data);

            // Add the object to Admin list
            Admin.addToAdminList(newAdmin);
        }
    }

    /**
     * A method to write admin information into file.
     */
    @Override
    public void writeFile() {

        // Retrieve the overall admin list
        ArrayList<Admin> adminList = Admin.getAdminList();

        // Sort admin list
        adminList.sort(Comparator.comparing(User::getUserID));

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
