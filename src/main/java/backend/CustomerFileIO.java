package backend;

import java.util.ArrayList;

/**
 * Class {@code CustomerFileIO} contains the methods used to read and write customer information file.
 * @author Beng Rhui (TP068495)
 */
public class CustomerFileIO extends UserFileIO {

    /**
     * Fixed variables to store information used for coding.
     */
    private final static String CUSTOMER_FILE_NAME = "customer_information.txt";
    private final static int NUMBER_OF_INFORMATION_IN_CUSTOMER = 10;
    private final static int[] SPACING_SIZE = {5, 50, 15, 40, 40, 10, 20, 15, 10, 50};

    /**
     * A method to read information from customer file and login credentials file to create {@code Customer} objects.
     */
    @Override
    public void readFile() {

        // Obtain information from customer text file
        ArrayList<String[]> unprocessedInformation = getListFromFile(CUSTOMER_FILE_NAME);

        // Loop through each record
        for (String[] individualRecords : unprocessedInformation) {

            // Create a new customer object, then add to both customer list and user list
            Customer newCustomer = createCustomerObject(individualRecords);
            Customer.addToCustomerList(newCustomer);
            User.addUserToList(newCustomer);
        }
    }

    /**
     * A method to create a {@code Customer} object based on individual records.
     * @param individualRecords The individual line of customer information
     * @return A {@code Customer} object with all information
     */
    public static Customer createCustomerObject(String[] individualRecords) {

        // Get information from credentials file
        ArrayList<String[]> credentialsInformation = getListFromFile(CREDENTIALS_FILE_NAME);

        // Extract customer information from input string array
        String userID = individualRecords[0];
        String name = individualRecords[1];
        String contactNumber = individualRecords[2];
        String addressLine1 = individualRecords[3];
        String addressLine2 = individualRecords[4];
        String postcode = individualRecords[5];
        String city = individualRecords[6];
        String state = individualRecords[7];
        double eWalletAmount = Double.parseDouble(individualRecords[8]);
        String deliveryNote = individualRecords[9];

        // Set email and password as null in default
        String email = null;
        String password = null;

        // Create address object
        Address address = new Address(addressLine1, addressLine2, postcode, city, state);

        // Loop through each record in the credentials list
        for (String[] credentials : credentialsInformation) {

            // Continue the loop if the user ID does not match
            if (!credentials[0].equals(individualRecords[0])) {
                continue;
            }

            // Retrieve email and password if user ID matches, then end the loop
            email = credentials[1];
            password = credentials[2];
            break;
        }

        // Create and return the generated customer object
        return new Customer(userID, email, password, name, contactNumber, address, eWalletAmount, deliveryNote);
    }

    /**
     * A method to write customer information into customer file.
     */
    @Override
    public void writeFile() {

        // Get all customer objects
        ArrayList<Customer> customerList = Customer.getCustomerList();

        // Set a local variable to store all customer information after converted to string arrays
        ArrayList<String[]> allInformation = new ArrayList<>();

        // Loop through each customer objects
        for (Customer customer : customerList) {

            // Create a string array to store information
            String[] record = new String[NUMBER_OF_INFORMATION_IN_CUSTOMER];
            record[0] = customer.getUserID();
            record[1] = customer.getName();
            record[2] = customer.getContactNumber();
            record[3] = customer.getAddress().getAddressLine1();
            record[4] = customer.getAddress().getAddressLine2();
            record[5] = customer.getAddress().getPostcode();
            record[6] = customer.getAddress().getCity();
            record[7] = customer.getAddress().getState();
            record[8] = String.valueOf(customer.getEWalletAmount());
            record[9] = customer.getDeliveryNote();

            // Add the string array to the overall array
            allInformation.add(record);
        }

        // Write the array into file
        writeListToFile(CUSTOMER_FILE_NAME, allInformation, SPACING_SIZE);
    }
}
