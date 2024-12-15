package backend.entity;

import java.util.ArrayList;

/**
 * Class {@code DeliveryRunner} represents the delivery people who will use the system to update delivery progress.
 *
 * @author Beng Rhui (TP068495)
 */
public class DeliveryRunner extends User {

    private final static ArrayList<DeliveryRunner> deliveryRunnerList = new ArrayList<>();
    /**
     * Additional attributes for {@code DeliveryRunner} objects.<br>
     * A list that contains all delivery runners is also included.
     */
    private String contactNumber;

    /**
     * Constructor to instantiate {@code DeliveryRunner} objects.
     *
     * @param userID        The runner ID of the user
     * @param email         The email used to log into the system
     * @param password      The password used to log into the system
     * @param name          The real-world name of the delivery runner
     * @param contactNumber The mobile number of the delivery runner
     */
    public DeliveryRunner(String userID, String email, String password, String name, String contactNumber) {
        super(userID, email, password, name);
        this.contactNumber = contactNumber;
    }

    /**
     * A method to return a list containing all delivery runners.
     *
     * @return An ArrayList containing all instances of {@code DeliveryRunner} objects
     */
    public static ArrayList<DeliveryRunner> getDeliveryRunnerList() {
        return deliveryRunnerList;
    }

    /**
     * A method to add an instance of {@code DeliveryRunner} to the overall list
     *
     * @param runner The {@code DeliveryRunner} object to be added into the list
     */
    public static void addToRunnerList(DeliveryRunner runner) {
        deliveryRunnerList.add(runner);
    }

    /**
     * A method to retrieve {@code DeliveryRunner} object by using delivery runner ID.
     *
     * @param runnerID The ID of the delivery runner
     * @return The {@code DeliveryRunner} object associated with the ID
     */
    public static DeliveryRunner getRunner(String runnerID) {

        // Loop through each runner in the list
        for (DeliveryRunner runner : deliveryRunnerList) {

            // Continue the loop if the user ID does not match
            if (!runner.userID.equals(runnerID)) {
                continue;
            }

            // Return the associated runner if the ID matches
            return runner;
        }

        // Return null if there is no matching ID
        return null;
    }

    /**
     * Getter and setter for {@code DeliveryRunner} class
     */
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    /**
     * A method used to print out information of a {@code DeliveryRunner} object.
     *
     * @return A string representation of {@code DeliveryRunner}
     */
    @Override
    public String toString() {
        return "Runner ID: " + super.userID + "\n" +
                "Runner Email: " + super.email + "\n" +
                "Runner Password: " + super.password + "\n" +
                "Runner Name: " + super.name + "\n" +
                "Runner Contact No: " + contactNumber;
    }

}
