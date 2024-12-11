package backend;

import java.util.ArrayList;

/**
 * Class {@code Customer} represents the customers of the food stall who uses the system.
 */
public class Customer extends User{

    /**
     * Attributes of the {@code Customer} class.<br>
     * An ArrayList that contains all instances of {@code Customer} is also included.
     */
    private String contactNumber;
    private Address address;
    private double creditAmount;
    private String deliveryNote;

    private final static ArrayList<Customer> customerList = new ArrayList<>();

    /**
     * Constructor used to instantiate the {@code Customer} class.
     * @param userID The Customer ID of the user
     * @param email The email that the customer uses to log into the account
     * @param password The password used to login to the system
     * @param name The real-world name of customer
     * @param contactNumber The phone number used to contact the customer
     * @param address The delivery address of the customer
     * @param creditAmount The balance of e-wallet of customer
     * @param deliveryNote The notes that a customer wishes to tell runner during delivery
     */
    public Customer(String userID, String email, String password, String name, String contactNumber,
                    Address address, double creditAmount, String deliveryNote) {
        super(userID, email, password, name);
        this.contactNumber = contactNumber;
        this.address = address;
        this.creditAmount = creditAmount;
        this.deliveryNote = deliveryNote;
        addToCustomerList(this);
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

    public double getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(double creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getDeliveryNote() {
        return deliveryNote;
    }

    public void setDeliveryNote(String deliveryNote) {
        this.deliveryNote = deliveryNote;
    }

    /**
     * A method to retrieve the overall list of customers
     * @return An ArrayList containing all {@code Customer} objects
     */
    public static ArrayList<Customer> getCustomerList() {
        return customerList;
    }

    /**
     * A method to add a {@code Customer} object to an overall list
     * @param customer {@code Customer} object to be added to the list
     */
    public static void addToCustomerList(Customer customer) {
        customerList.add(customer);
    }
}
