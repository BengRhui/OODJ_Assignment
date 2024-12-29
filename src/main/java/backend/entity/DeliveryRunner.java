package backend.entity;

import java.util.*;

/**
 * Class {@code DeliveryRunner} represents the delivery people who will use the system to update delivery progress.
 *
 * @author Beng Rhui (TP068495)
 */
public class DeliveryRunner extends User {

    /**
     * Additional attributes for {@code DeliveryRunner} objects.<br>
     * A list that contains all delivery runners is also included.
     */
    private final static ArrayList<DeliveryRunner> deliveryRunnerList = new ArrayList<>();
    private final static HashMap<DeliveryRunner, Boolean> availabilityList = new HashMap<>();
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
     * A method to randomly obtain an available runner that is free.
     *
     * @return A random delivery runner that is available. Returns {@code null} if no runner is available.
     */
    public static DeliveryRunner getAvailableRunner() {

        // Initialize array to store available runners
        ArrayList<DeliveryRunner> availableRunners = new ArrayList<>();

        // From the availability list, get the available runners
        availabilityList.forEach(
                (runner, availability) -> {
                    if (availability) availableRunners.add(runner);
                }
        );

        // Return null if the list of available runners is empty
        if (availableRunners.isEmpty()) return null;

        // Randomly pick an index
        Random randomNumber = new Random();
        int chosenIndex = randomNumber.nextInt(availableRunners.size());

        // Retrieve and return the relevant runner
        return availableRunners.get(chosenIndex);
    }

    /**
     * A method to initialize the availability list that represents the availability of each runner.
     */
    public static void initializeAvailabilityList() {

        // Loop through each runner
        for (DeliveryRunner runner : deliveryRunnerList) {

            // Check for the runner availability
            boolean availability = runner.checkAvailability();

            // Add runner and the corresponding availability into the HashMap
            availabilityList.put(runner, availability);
        }
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
     * A method to add multiple instances of {@code DeliveryRunner} to the overall list
     *
     * @param runner The {@code DeliveryRunner} objects to be added into the list
     */
    public static void addToRunnerList(DeliveryRunner... runner) {

        // Throws an error if there is no runner passed into the argument, or a null runner is passed into argument
        if (runner.length == 0 || Arrays.stream(runner).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Arguments should contain at least one DeliveryRunner object");
        }

        // Add all the runners from the arguments into the list
        deliveryRunnerList.addAll(
                Arrays.asList(runner)
        );
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
     * A method to generate new delivery runner ID.
     *
     * @return The new delivery runner ID generated
     */
    public static String generateNewID() {

        // Declare variables to record index
        int index = 1;

        // Start a loop
        while (true) {

            // Get the generated ID
            String generatedID = String.format("R%03d", index);

            // Check if the generated ID is in the runner list
            boolean generatedIDExists = deliveryRunnerList.stream()             // Get the list of delivery runners
                    .anyMatch(runner -> runner.userID.equals(generatedID));     // Check if there is any match with the existing runner ID

            // If the ID does not exist among the runner list, return that ID
            if (!generatedIDExists) return generatedID;

            // Increment the index if there is a match
            index++;
        }
    }

    /**
     * A method to update the availability of runners (mainly used when runner rejects an order)
     *
     * @param status The status of the runner
     * @return Status whether if the availability is updated successfully. If not, the {@code HashMap} has to be initialized again.
     */
    public boolean updateAvailability(boolean status) {

        // Replace the status in the HashMap with the new status
        Boolean state = availabilityList.replace(this, status);

        // Return false if the runner is not found, else return true
        return state != null;
    }

    /**
     * A method to check the availability of a runner.
     *
     * @return {@code true} if the runner is free from any incomplete orders, {@code false} otherwise
     */
    public boolean checkAvailability() {
        return Order.getOrderList().stream()                                                        // Convert list of orders into stream
                .filter(                                                                            // Get orders excluding completed and cancelled ones
                        order -> !order.getOrderStatus().equals(Order.OrderStatus.COMPLETED) &&
                                !order.getOrderStatus().equals(Order.OrderStatus.CANCELLED) &&
                                !(order.getRunnerInCharge() == null)
                )
                .noneMatch(order -> order.getRunnerInCharge().equals(this));                        // Return true if runner is not in the list
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
