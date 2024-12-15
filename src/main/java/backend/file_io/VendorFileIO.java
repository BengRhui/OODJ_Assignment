package backend.file_io;

import backend.entity.Stall;
import backend.entity.Vendor;

import java.util.ArrayList;

/**
 * Class {@code VendorFileIO} contains methods to help read and write vendor files.
 * @author Beng Rhui (TP068495)
 */
public class VendorFileIO extends UserFileIO {

    /**
     * Fixed variables to help in coding.
     */
    public final static String VENDOR_FILE_NAME = "vendor_information.txt";
    public final static int NUMBER_OF_INFORMATION_IN_FILE = 3;
    public final static int[] SPACING_SIZE = {5, 50, 5};

    /**
     * A method to read from vendor file and instantiate {@code Vendor} objects.
     */
    @Override
    public void readFile() {

        // Retrieve vendor information from text file
        ArrayList<String[]> vendorList = getListFromFile(VENDOR_FILE_NAME);

        // Loop through the records
        for (String[] record : vendorList) {

            // Create vendor object and add to list
            Vendor newVendor = createVendorObject(record);
            Vendor.addToVendorList(newVendor);
        }
    }

    /**
     * A method to create vendor object based on a string record.
     * @param vendorRecord The string record from text file
     * @return The {@code Vendor} object being created
     */
    public static Vendor createVendorObject(String[] vendorRecord) {

        // Retrieve information from string array
        String userID = vendorRecord[0];
        String name = vendorRecord[1];
        String stallID = vendorRecord[2];

        // Convert stall ID to stall object
        Stall stall = Stall.getStall(stallID);

        // Retrieve email and password
        String[] credentials = getUsernameAndPassword(userID);
        String email = credentials[0];
        String password = credentials[1];

        // Create and return vendor object
        return new Vendor(userID, email, password, name, stall);
    }

    /**
     * A method to write vendor object into files.
     */
    @Override
    public void writeFile() {

        // Get all vendor objects
        ArrayList<Vendor> vendorList = Vendor.getVendorList();

        // Declare local variable to store text to be written to text file
        ArrayList<String[]> information = new ArrayList<>();

        // Loop through all vendor objects
        for (Vendor vendor : vendorList) {

            // Create a string array consisting of information to be written to text file, then add to local variable
            String[] singleLine = new String[NUMBER_OF_INFORMATION_IN_FILE];
            singleLine[0] = vendor.getUserID();
            singleLine[1] = vendor.getName();
            singleLine[2] = vendor.getStall().getStallID();
            information.add(singleLine);
        }

        // Write the information to text file
        writeListToFile(VENDOR_FILE_NAME, information, SPACING_SIZE);
    }
}
