package backend.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Class {@code Customer} represents the customers of the food stall who uses the system.
 *
 * @author Beng Rhui (TP068495)
 */
public class Customer extends User {

    /**
     * Attributes of the {@code Customer} class.<br>
     * An ArrayList that contains all instances of {@code Customer} is also included.
     */
    private final static ArrayList<Customer> customerList = new ArrayList<>();
    private String contactNumber;
    private Address address;
    private double eWalletAmount;
    private String deliveryNote;

    /**
     * Constructor used to instantiate the {@code Customer} class.
     *
     * @param userID        The Customer ID of the user
     * @param email         The email that the customer uses to log into the account
     * @param password      The password used to log into the system
     * @param name          The real-world name of customer
     * @param contactNumber The phone number used to contact the customer
     * @param address       The delivery address of the customer
     * @param eWalletAmount The balance of e-wallet of customer
     * @param deliveryNote  The notes that a customer wishes to tell runner during delivery
     */
    public Customer(String userID, String email, String password, String name, String contactNumber,
                    Address address, double eWalletAmount, String deliveryNote) {
        super(userID, email, password, name);
        this.contactNumber = contactNumber;
        this.address = address;
        this.eWalletAmount = eWalletAmount;
        this.deliveryNote = deliveryNote;
    }

    /**
     * A method to retrieve the overall list of customers
     *
     * @return An ArrayList containing all {@code Customer} objects
     */
    public static ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    /**
     * A method to add {@code Customer} objects to an overall list
     *
     * @param customer {@code Customer} objects to be added to the list
     */
    public static void addToCustomerList(Customer... customer) {

        // Throws an error if there is no customer passed into the argument, or a null customer is passed into argument
        if (customer.length == 0 || Arrays.stream(customer).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Arguments should contain at least one Customer object");
        }

        // Add all the customers from the arguments into the list
        customerList.addAll(
                Arrays.asList(customer)
        );
    }

    /**
     * A method to retrieve {@code Customer} based on customer ID.
     *
     * @param customerID The ID of the customer
     * @return The {@code Customer} object associated with the ID
     */
    public static Customer getCustomer(String customerID) {

        // Loop through the list of customers
        for (Customer customer : customerList) {

            // Continue the loop if the ID does not match
            if (!customer.userID.equals(customerID)) {
                continue;
            }

            // Return customer object if the ID matches
            return customer;
        }

        // If no ID matches, return null
        return null;
    }

    /**
     * A method to generate new ID for customers.
     *
     * @return THe new ID generated
     */
    public static String generateNewID() {

        // Declare variables to record index
        int index = 1;

        // Start a loop
        while (true) {

            // Get the generated ID
            String generatedID = String.format("C%03d", index);

            // Check if the generated ID is in the customer list
            boolean generatedIDExists = customerList.stream()                       // Get the list of customers
                    .anyMatch(customer -> customer.userID.equals(generatedID));     // Check if there is any match with the existing customer ID

            // If the ID does not exist among the customer list, return that ID
            if (!generatedIDExists) return generatedID;

            // Increment the index if there is a match
            index++;
        }
    }

    /**
     * Getters and setters for the {@code Customer} class
     */
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public double getEWalletAmount() {
        return eWalletAmount;
    }

    public void setEWalletAmount(double eWalletAmount) {
        this.eWalletAmount = eWalletAmount;
    }

    public String getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    /**
     * A method to print out the information of {@code Customer} instances.
     *
     * @return String representation of {@code Customer} object
     */
    @Override
    public String toString() {
        return "Customer ID: " + super.userID + "\n" +
                "Customer Email: " + super.email + "\n" +
                "Customer Password: " + super.password + "\n" +
                "Customer Name: " + super.name + "\n" +
                "Customer Contact No: " + contactNumber + "\n" +
                "Customer Address: " + "\n" +
                address.toString() + "\n" +
                "Customer E-Wallet Amount: " + eWalletAmount + "\n" +
                "Customer Delivery Note: " + deliveryNote;
    }
}
