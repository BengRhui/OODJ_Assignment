package backend.entity;

import backend.file_io.CredentialsFileIO;
import backend.file_io.DeliveryRunnerFileIO;
import backend.notification.DeliveryRunnerNotification;
import backend.utility.Utility;

import java.util.*;
import java.util.stream.Collectors;

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
    private final static Map<String, Boolean> availabilityList = new HashMap<>();
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
                (runnerID, availability) -> {
                    if (availability) availableRunners.add(getRunner(runnerID));
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

            // Add runner and the corresponding availability into the Map
            availabilityList.put(runner.userID, availability);
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
     * A method to get the availability list for the current system.
     *
     * @return A Map consisting of the runner ID, with a boolean representing availability
     */
    public static Map<String, Boolean> getAvailabilityList() {
        return availabilityList;
    }

    /**
     * A method to retrieve the list of delivery runners based on their names.
     *
     * @param name The name of the runner
     * @return The filtered list of runners
     */
    public static ArrayList<DeliveryRunner> searchRunnerByName(String name) {

        // Filter the overall runner list using name as input
        return getDeliveryRunnerList().stream()
                .filter(runner -> runner.name.toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A method to retrieve the list of delivery runners based on their contact number.
     *
     * @param contactNumber The contact number of runner
     * @return A filtered list of runners
     */
    public static ArrayList<DeliveryRunner> searchRunnerByContactNumber(String contactNumber) {

        // Filter overall runner list based on contact number
        return getDeliveryRunnerList().stream()
                .filter(runner -> runner.contactNumber.contains(contactNumber))
                .collect(Collectors.toCollection(ArrayList::new));
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
     * A method to create a new delivery runner account.
     *
     * @param runnerID        The ID of the runner
     * @param name            The name of the runner
     * @param contactNumber   The contact number of the runner
     * @param email           The email of the runner
     * @param password        The password of the runner
     * @param confirmPassword The password retyped
     * @return {@code 1} if the new account is created successfully<br>
     * {@code 0} if there exist any empty values<br>
     * {@code -1} if the email is not in the correct format<br>
     * {@code -2} if the email is not available<br>
     * {@code -3} if the password does not meet requirement<br>
     * {@code -4} if the password does not match with "confirm password"<br>
     * {@code -5} if the contact number is not in the correct format
     */
    public static int createNewRunner(
            String runnerID,
            String name,
            String contactNumber,
            String email,
            char[] password,
            char[] confirmPassword) {

        // Check if there is any empty values
        if (name.equalsIgnoreCase("Enter Name Here") ||
                contactNumber.equalsIgnoreCase("Enter Contact Number (e.g. 012-3456789)") ||
                email.equalsIgnoreCase("Enter Email Here") ||
                Utility.generateString(password).equalsIgnoreCase("Enter Password Here") ||
                Utility.generateString(confirmPassword).equalsIgnoreCase("Retype Password Here")
        ) return 0;

        // Case 1: Check if the email format is correct (-1)
        if (!checkEmailFormat(email)) return -1;

        // Case 2: Check if the email is available (-2)
        if (!isEmailAvailable(email)) return -2;

        // Case 3: Check if password meets requirement (-3)
        if (!validatePassword(
                Utility.generateString(password))
        ) return -3;

        // Case 4: Check if both passwords match (-4)
        if (!Arrays.equals(password, confirmPassword)) return -4;

        // Case 5: Check if the format of contact number is correct (-5)
        if (!checkContactNumberFormat(contactNumber)) return -5;

        // Create the new runner
        DeliveryRunner newRunner = new DeliveryRunner(
                runnerID,
                email,
                Utility.generateString(password),
                name,
                contactNumber
        );

        // Add to list
        addToRunnerList(newRunner);

        // Update the availability list
        availabilityList.put(newRunner.userID, true);

        // Write to file
        CredentialsFileIO.writeCredentialsFile();
        DeliveryRunnerFileIO runnerIO = new DeliveryRunnerFileIO();
        runnerIO.writeFile();

        // Return 1 for successful operation
        return 1;
    }

    /**
     * A method to modify the details of the delivery runner account.
     *
     * @param name            The name of the runner
     * @param contactNumber   The contact number of the runner
     * @param email           The email of the runner
     * @param password        The password of the runner
     * @param confirmPassword The password retyped
     * @return {@code 1}  if the new account is created successfully<br>
     * {@code 0} if there exist any empty values (except those that will be validated later)<br>
     * {@code -1} if the email is not in the correct format<br>
     * {@code -2} if the email is not available<br>
     * {@code -3} if the password does not meet requirement<br>
     * {@code -4} if the password does not match with "confirm password"<br>
     * {@code -5} if the contact number is not in the correct format<br>
     * {@code -6} if notification is unable to be created
     */
    public int modifyRunner(
            String name,
            String contactNumber,
            String email,
            char[] password,
            char[] confirmPassword) {

        // Check if there is any empty values
        if (name.equalsIgnoreCase("Enter Name Here") ||
                contactNumber.equalsIgnoreCase("Enter Contact Number (e.g. 012-3456789)") ||
                email.equalsIgnoreCase("Enter Email Here") ||
                Utility.generateString(password).equalsIgnoreCase("Enter Password Here") ||
                Utility.generateString(confirmPassword).equalsIgnoreCase("Retype Password Here")
        ) return 0;

        // Check if email is in correct format
        if (!checkEmailFormat(email)) return -1;

        // Check if email is available
        if (!this.email.equals(email) && !isEmailAvailable(email)) return -2;

        // Check if password meets requirement
        if (!validatePassword(
                Utility.generateString(password))
        ) return -3;

        // Check if both passwords match
        if (!Arrays.equals(password, confirmPassword)) return -4;

        // Check if contact number is in correct format
        if (!checkContactNumberFormat(contactNumber)) return -5;

        // Create notification to inform that details is modified
        boolean createNotification = DeliveryRunnerNotification.createNewNotification(
                "Personal Information Updated",
                "Your personal information has been updated.",
                this
        );
        if (!createNotification) return -6;

        // Change the details
        this.setName(name);
        this.setContactNumber(contactNumber);
        this.setEmail(email);
        this.setPassword(
                Utility.generateString(password)
        );

        // Write to file
        CredentialsFileIO.writeCredentialsFile();
        DeliveryRunnerFileIO runnerIO = new DeliveryRunnerFileIO();
        runnerIO.writeFile();

        // Return 1 to indicate successful modification
        return 1;
    }

    /**
     * A method to delete the current delivery runner account.
     *
     * @return {@code true} if the account is deleted successfully, else {@code false}
     */
    public boolean deleteRunner() {

        // Delete the relevant notifications
        boolean deleteNotification = DeliveryRunnerNotification.deleteRunnerFromNotification(this.getUserID());
        if (!deleteNotification) return false;

        // Delete runner from availability list
        availabilityList.remove(this.userID);

        // Change the relevant runner attributes in order to null
        boolean changeToNull = Order.changeRunnerToNull(this.getUserID());
        if (!changeToNull) return false;

        // Remove from list
        boolean removeFromList = deliveryRunnerList.remove(this);
        if (!removeFromList) return false;

        // Write to file
        CredentialsFileIO.writeCredentialsFile();
        DeliveryRunnerFileIO runnerIO = new DeliveryRunnerFileIO();
        runnerIO.writeFile();

        // Return true for successful operation
        return true;
    }

    /**
     * A method to obtain the overall delivery count of the runner.
     *
     * @param filter The timeframe used to filter the order data
     * @return The delivery count of user
     */
    public int getDeliveryCount(Utility.TimeframeFilter filter) {

        // Retrieve the order list based on the filter
        ArrayList<Order> orderList = Order.filterOrder(this, filter);

        // Filter the order list with only the completed and cancelled ones
        orderList = orderList.stream()
                .filter(order -> order.getOrderStatus() == Order.OrderStatus.COMPLETED ||
                        order.getOrderStatus() == Order.OrderStatus.CANCELLED)
                .collect(Collectors.toCollection(ArrayList::new));

        // Return the size of the list as delivery count
        return orderList.size();
    }

    /**
     * A method to calculate the amount of tips received by the runner.
     *
     * @param filter The timeframe used to filter the order data
     * @return The amount of tips received
     */
    public double getTipsAmount(Utility.TimeframeFilter filter) {

        // Initialize a variable to store tips amount
        double tipsAmount = 0;

        // Get the order list based on filter, and only include completed or cancelled ones
        ArrayList<Order> orderList = Order.filterOrder(this, filter).stream()
                .filter(order -> order.getOrderStatus() == Order.OrderStatus.COMPLETED ||
                        order.getOrderStatus() == Order.OrderStatus.CANCELLED)
                .collect(Collectors.toCollection(ArrayList::new));

        // Loop through the order list
        for (Order order : orderList) {

            // Continue if the tips received is null
            if (order.getTipsForRunner() == null) continue;

            // Add the tips to the variable
            tipsAmount += order.getTipsForRunner();
        }

        // Return the total amount at last
        return tipsAmount;
    }

    /**
     * A private method to retrieve the overall rating details based on filter.
     *
     * @param filter The timeframe used to filter the feedback list
     * @return A list containing two information: overall ratings and feedback count
     */
    private double[] getOverallRatingDetails(Utility.TimeframeFilter filter) {

        // Initialize variables to store information
        double ratings = 0;
        int feedbackCount = 0;

        // Obtain the filtered feedback list based on the filter
        ArrayList<Feedback> feedbackList = Feedback.filterFeedback(filter);

        // Loop through each feedback
        for (Feedback feedback : feedbackList) {

            // Check if the feedback category is runner (coz only runner feedbacks should be counted) and the runner in charge is the current runner
            if (feedback.getFeedbackCategory() == Feedback.Category.DELIVERY_RUNNER && feedback.getOrderAssociated().getRunnerInCharge().equals(this)) {

                // Add the ratings to the initialized variable and increment feedback count
                ratings += feedback.getRatings();
                feedbackCount++;
            }
        }

        // Check if the feedback count is 0
        if (feedbackCount == 0) return new double[]{0, 0};

        // Calculate the average ratings
        double overallRating = ratings / feedbackCount;

        // Return both values
        return new double[]{overallRating, feedbackCount};
    }

    /**
     * A method to obtain the ratings of a delivery runner.
     *
     * @param filter The timeframe imposed to filter the feedback list
     * @return The average rating of the runner
     */
    public double getRatings(Utility.TimeframeFilter filter) {

        // Get the first element of the method as overall ratings
        return getOverallRatingDetails(filter)[0];
    }

    /**
     * A method to obtain the overall feedback count of a delivery runner.
     *
     * @param filter The timeframe used to filter the feedback list
     * @return The overall count of feedback taken into consideration
     */
    public int getFeedbackCount(Utility.TimeframeFilter filter) {

        // Get the second element of the method and cast to int as feedback count
        return (int) getOverallRatingDetails(filter)[1];
    }

    /**
     * A method to retrieve the orders associated with the current delivery runner (only one).
     *
     * @return The Order item associated with the delivery runner
     */
    public Order retrieveCurrentAssociatedOrder() {

        // Filter the current orders and get the incomplete ones that associate with the current runner
        return Order.getOrderList().stream()
                .filter(order -> order.getOrderStatus() != Order.OrderStatus.COMPLETED && order.getOrderStatus() != Order.OrderStatus.CANCELLED)
                .filter(order -> order.getDiningType() == Order.DiningType.DELIVERY && order.getRunnerInCharge() != null)
                .filter(order -> order.getRunnerInCharge() != null && order.getRunnerInCharge().getUserID().equals(this.userID))
                .findAny()
                .orElse(null);
    }

    /**
     * A method to update the availability of runners (mainly used when runner rejects an order)
     *
     * @param status The status of the runner
     * @return Status whether if the availability is updated successfully. If not, the {@code Map} has to be initialized again.
     */
    public boolean updateAvailability(boolean status) {

        // Replace the status in the Map with the new status
        Boolean state = availabilityList.replace(this.userID, status);

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
