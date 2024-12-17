package backend.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Class {@code Vendor} represents the hawkers / sellers in the food court.
 *
 * @author Beng Rhui (TP068495)
 */
public class Vendor extends User {

    /**
     * Additional attribute for {@code Vendor} class.<br>
     * An overall list to record all {@code Vendor} objects is also included.
     */
    private final static ArrayList<Vendor> vendorList = new ArrayList<>();
    private Stall stall;

    /**
     * Constructor to instantiate the class {@code Vendor}.
     *
     * @param userID   The ID of the vendor
     * @param email    Email used to log into the system
     * @param password Password used to log into the system
     * @param name     Real-world name of the vendor
     * @param stall    Stall owned by the vendor
     */
    public Vendor(String userID, String email, String password, String name, Stall stall) {
        super(userID, email, password, name);
        this.stall = stall;
    }

    /**
     * A method to retrieve the list consisting of all vendors.
     *
     * @return An ArrayList consisting of all {@code Vendor} objects.
     */
    public static ArrayList<Vendor> getVendorList() {
        return vendorList;
    }

    /**
     * A method to add {@code Vendor} objects into the overall list.
     *
     * @param vendor {@code Vendor} objects to be added to overall list
     */
    public static void addToVendorList(Vendor... vendor) {

        // Throws an error if there is no vendor passed into the argument, or a null vendor is passed into argument
        if (vendor.length == 0 || Arrays.stream(vendor).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Arguments should contain at least one Vendor object");
        }

        // Add all the vendors from the arguments into the list
        vendorList.addAll(
                Arrays.asList(vendor)
        );
    }

    /**
     * Getter and setter for additional attributes.
     */
    public Stall getStall() {
        return stall;
    }

    public void setStall(Stall stall) {
        this.stall = stall;
    }

    /**
     * A method to print out information of {@code Vendor} object.
     *
     * @return String representation of {@code Vendor} object
     */
    @Override
    public String toString() {
        return "Vendor ID: " + super.userID + "\n" +
                "Vendor Email: " + super.email + "\n" +
                "Vendor Password: " + super.password + "\n" +
                "Vendor Name: " + super.name + "\n" +
                "Vendor Stall: " + "\n" +
                stall.toString();
    }

    /**
     * A method to retrieve {@code Vendor} object using vendor ID
     * @param vendorID The ID of the vendor
     * @return The {@code Vendor} object associated with the ID
     */
    public static Vendor getVendor(String vendorID) {

        // Loop through the list of vendors
        for (Vendor vendor : vendorList) {

            // Continue loop if the ID does not match
            if (!vendor.getUserID().equals(vendorID)) {
                continue;
            }

            // Return the vendor if ID matches
            return vendor;
        }

        // Return null if there is no matching ID
        return null;
    }
}
