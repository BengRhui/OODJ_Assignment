package backend.entity;

import backend.file_io.CustomerFileIO;
import backend.file_io.FeedbackFileIO;
import backend.notification.CustomerNotification;
import backend.utility.Utility;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class {@code Feedback} represents the feedback that customers provide to the system, vendor and delivery runner.
 *
 * @author Beng Rhui (TP068495)
 */
public class Feedback {

    /**
     * Attributes for {@code Feedback} objects.<br>
     * A list containing all {@code Feedback} objects and lists used to write to Excel file is also included.
     */
    private final static ArrayList<Feedback> feedbackList = new ArrayList<>();
    private final static String[] headerList = {
            "Feedback ID",
            "Feedback Type",
            "Order ID",
            "Customer Name",
            "Customer Contact No.",
            "Stall Name (if applicable)",
            "Delivery Runner Name (if applicable)",
            "Feedback Submission Time",
            "Ratings",
            "Feedback Title",
            "Feedback Description",
            "Response to Feedback"
    };
    private final static int[] columnWidth = {
            256 * 15,
            256 * 20,
            256 * 20,
            256 * 50,
            256 * 25,
            256 * 35,
            256 * 40,
            256 * 30,
            256 * 10,
            256 * 30,
            256 * 50,
            256 * 50
    };

    private String feedbackID;
    private Category feedbackCategory;
    private Customer customerAssociated;
    private Order orderAssociated;
    private LocalDateTime feedbackSubmissionTime;
    private int ratings;
    private String feedbackTitle;
    private String feedbackDetails;
    private String replyFromManager;

    /**
     * Constructor to instantiate {@code Feedback} objects.
     *
     * @param feedbackID       The ID of feedback
     * @param feedbackCategory The categories of feedback, i.e. system, vendor and delivery runner
     * @param orderAssociated  The Order item associated with the feedback
     * @param ratings          The ratings provided by the customer in the feedback
     * @param feedbackTitle    The title of the feedback
     * @param feedbackDetails  The description of the feedback
     * @param replyFromManager The reply that the manager provides to the customer
     */
    public Feedback(String feedbackID, Category feedbackCategory, Customer customerAssociated, Order orderAssociated, LocalDateTime feedbackSubmissionTime, int ratings, String feedbackTitle, String feedbackDetails, String replyFromManager) {
        this.feedbackID = feedbackID;
        this.feedbackCategory = feedbackCategory;
        this.customerAssociated = customerAssociated;
        this.orderAssociated = orderAssociated;
        this.feedbackSubmissionTime = feedbackSubmissionTime;
        this.ratings = ratings;
        this.feedbackTitle = feedbackTitle;
        this.feedbackDetails = feedbackDetails;
        this.replyFromManager = replyFromManager;
    }

    /**
     * A method to automatically generate the ID for a new feedback.
     *
     * @return The ID string of the feedback
     */
    public static String generateNewID() {

        // Create a variable to record index
        int index = 1;

        // Start an infinity loop
        while (true) {

            // Generate an ID
            String generatedID = String.format("F%03d", index);

            // Check if the ID is used by another feedback
            boolean isAvailable = feedbackList.stream()
                    .noneMatch(feedback -> feedback.getFeedbackID().equals(generatedID));

            // If not used by others, return the ID
            if (isAvailable) return generatedID;

            // If yes, increase index and try again
            index++;
        }
    }

    /**
     * A method to change the customer attribute of associated customer to null if a customer is deleted.
     *
     * @param customerID The ID of the associated customer
     * @return {@code true} if the operation is successful, else {@code false}
     */
    public static boolean changeCustomerToNull(String customerID) {

        // Return false if the customer ID is null or empty
        if (customerID == null || customerID.isBlank()) return false;

        // Loop through each feedback in the feedback list
        for (Feedback feedback : feedbackList) {

            // Make sure that the customer is not null and it matches the ID
            if (feedback.customerAssociated != null && feedback.customerAssociated.userID.equals(customerID))

                // Set the customer to null if condition meets
                feedback.customerAssociated = null;
        }

        // Write into file and return true for successful operation
        FeedbackFileIO.writeFile();
        return true;
    }

    /**
     * A method to retrieve the list that contains all instances of {@code Feedback}.
     *
     * @return An ArrayList containing all {@code Feedback} objects
     */
    public static ArrayList<Feedback> getFeedbackList() {
        return feedbackList;
    }

    /**
     * A method to add {@code Feedback} objects to the overall list.
     *
     * @param feedback The {@code Feedback} objects to be added to list
     */
    public static void addToFeedbackList(Feedback... feedback) {

        // Throws an error if there is no feedback passed into the argument, or a null feedback is passed into argument
        if (feedback.length == 0 || Arrays.stream(feedback).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Arguments should contain at least one Feedback object");
        }

        // Add all the feedbacks from the arguments into the list
        feedbackList.addAll(
                Arrays.asList(feedback)
        );
    }

    /**
     * A method to retrieve feedback based on ID
     *
     * @param feedbackID The ID of the feedback
     * @return The {@code Feedback} object associated to the ID
     */
    public static Feedback getFeedback(String feedbackID) {

        // Loop through the list of feedback
        for (Feedback feedback : feedbackList) {

            // Continue loop if ID does not match
            if (!feedback.feedbackID.equals(feedbackID)) {
                continue;
            }

            // Return feedback if ID matches
            return feedback;
        }

        // Return null if all ID does not match the input ID
        return null;
    }

    /**
     * A method to filter the overall feedback list based on a timeframe.
     *
     * @param filter The type of filter used to filter the feedback
     * @return An array list consisting of the filtered list of feedbacks
     */
    public static ArrayList<Feedback> filterFeedback(Utility.TimeframeFilter filter) {

        // Get the starting and ending time for the selected filter
        LocalDateTime[] timeframe = Utility.getFilterStartAndEndTime(filter);
        LocalDateTime startingTime = timeframe[0];
        LocalDateTime endingTime = timeframe[1];

        // Filter the feedback list based on the timeframe
        return feedbackList.stream()
                .filter(feedback -> !feedback.feedbackSubmissionTime.isBefore(startingTime)
                        && !feedback.feedbackSubmissionTime.isAfter(endingTime))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A method to filter the feedback list based on category.
     *
     * @param category The feedback category to be applied as a filter
     * @return A filtered feedback list
     */
    public static ArrayList<Feedback> getFeedbackList(Category category) {

        // Filter the feedback list based on the category and sort from latest to oldest
        return getFeedbackList().stream()
                .filter(feedback -> feedback.feedbackCategory == category)
                .sorted(Comparator.comparing(Feedback::getFeedbackSubmissionTime).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A method to filter the feedback list based on stall.
     *
     * @param stall The stall that associates with the feedback
     * @return A filtered feedback list
     */
    public static ArrayList<Feedback> getFeedbackList(Stall stall) {

        // Filter and sort the feedback list from latest to oldest based on the stall
        return getFeedbackList().stream()
                .filter(feedback -> feedback.feedbackCategory == Category.VENDOR &&
                        feedback.orderAssociated.getOrderedStall() != null &&
                        feedback.orderAssociated.getOrderedStall().getStallID().equals(stall.getStallID()))
                .sorted(Comparator.comparing(Feedback::getFeedbackSubmissionTime).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A method to retrieve the feedback list associated to the delivery runner.
     *
     * @param runner The delivery runner involved
     * @return The filtered feedback list
     */
    public static ArrayList<Feedback> getFeedbackList(DeliveryRunner runner) {

        // Filter and sort feedback list from latest to oldest based on the stall
        return getFeedbackList().stream()
                .filter(feedback -> feedback.getFeedbackCategory() == Category.DELIVERY_RUNNER &&
                        feedback.getOrderAssociated() != null &&
                        feedback.getOrderAssociated().getRunnerInCharge() != null &&
                        feedback.getOrderAssociated().getRunnerInCharge().getUserID().equals(runner.userID))
                .sorted(Comparator.comparing(Feedback::getFeedbackSubmissionTime).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A method to retrieve the feedback list associated to a customer.
     *
     * @param customer The customer involved
     * @return The filtered feedback list
     */
    public static ArrayList<Feedback> getFeedbackList(Customer customer) {

        // Filter and sort feedback list from latest to oldest based on the stall
        return getFeedbackList().stream()
                .filter(feedback -> feedback.customerAssociated != null && feedback.customerAssociated.userID.equals(customer.userID))
                .sorted(Comparator.comparing(Feedback::getFeedbackSubmissionTime).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * A method to arrange and retrieve the list of feedbacks.
     *
     * @param category The feedback category involved
     * @param filter   The filters involved while arranging the feedback
     * @return A filtered list of feedbacks with the specific order
     */
    public static ArrayList<Feedback> arrangeFeedbackList(Category category, Filter filter) {

        // Filter based on category
        ArrayList<Feedback> feedbackList = getFeedbackList(category);
        if (feedbackList == null) return null;

        // Arrange list based on filter
        switch (filter) {
            case LATEST_TO_OLDEST ->
                    feedbackList.sort(Comparator.comparing(Feedback::getFeedbackSubmissionTime).reversed());
            case OLDEST_TO_LATEST -> feedbackList.sort(Comparator.comparing(Feedback::getFeedbackSubmissionTime));
            case HIGH_TO_LOW_RATING -> feedbackList.sort(Comparator.comparing(Feedback::getRatings).reversed());
            case LOW_TO_HIGH_RATING -> feedbackList.sort(Comparator.comparing(Feedback::getRatings));
        }

        // Return the sorted list
        return feedbackList;
    }

    /**
     * A method to allow customers to provide feedback for system, vendor and runner.
     *
     * @param category    The category for the feedback
     * @param customer    The customer involved
     * @param order       The order related to the feedback (if available)
     * @param score       The ratings provided to feedback
     * @param title       The title of the feedback
     * @param description The description of feedback
     * @param tips        The tips for runner (if applicable)
     * @return {@code 1} if the feedback is created successfully<br>
     * {@code 0} if null values exist<br>
     * {@code -1} if the category and order does not match<br>
     * {@code -2} if the tips is invalid<br>
     * {@code -3} if the e-wallet amount is insufficient to pay for the tips<br>
     * {@code -4} if transaction history cannot be created
     */
    public static int customerProvideFeedback(
            Category category,
            Customer customer,
            Order order,
            int score,
            String title,
            String description,
            Double tips
    ) {

        // Avoid null values for some parameters
        if (category == null ||
                customer == null ||
                score <= 0 ||
                title == null || title.isBlank() || title.equals("Enter Feedback Title") ||
                description == null || description.isBlank() || description.equals("Enter Description Here")) return 0;

        // Check if the input is correct
        if (category != Category.DELIVERY_RUNNER && tips != null) return -1;
        if (category == Category.SYSTEM && order != null) return -1;
        if (order != null && order.getOrderingCustomer() != null &&
                !order.getOrderingCustomer().getUserID().equals(customer.getUserID())) return -1;

        // Check if the input is valid
        if (tips != null && tips < 0) return -2;

        // Create feedback object
        Feedback submittedFeedback = new Feedback(
                generateNewID(),
                category,
                customer,
                order,
                LocalDateTime.now(),
                score,
                title,
                description,
                null
        );

        // Check if there is any tips
        if (category == Category.DELIVERY_RUNNER && (tips != null && tips > 0)) {

            // Reject if the tips is more than the customer's e-wallet amount
            if (customer.getEWalletAmount() < tips) return -3;

            // Subtract tips from customer's e-wallet
            double walletAmountAfterDelivery = customer.getEWalletAmount() - tips;
            customer.setEWalletAmount(walletAmountAfterDelivery);

            // Create transaction history
            boolean createTransaction = Transaction.createTransactionHistory(
                    customer,
                    tips,
                    Transaction.TransactionType.CASH_OUT,
                    Transaction.PaymentMethod.E_WALLET
            );
            if (!createTransaction) return -4;

        }

        // Add to list
        addToFeedbackList(submittedFeedback);

        // Write to file
        FeedbackFileIO.writeFile();
        CustomerFileIO customerIO = new CustomerFileIO();
        customerIO.writeFile();

        // Return true for successful operation
        return 1;
    }

    /**
     * A method to check if vendor feedback has been filled for that order
     *
     * @param order The order that will be checked
     * @return {@code true} if vendor feedback is filled, else {@code false}
     */
    public static boolean checkNeedToFillVendorFeedback(Order order) {

        // Check if the order is associated with a stall, if no then no need for feedback
        if (order.getOrderedStall() == null) return false;

        // Find if there is any matching vendor feedback from the customer
        return feedbackList.stream()
                .noneMatch(feedback -> feedback.feedbackCategory == Category.VENDOR &&
                        feedback.orderAssociated != null &&
                        feedback.orderAssociated.getOrderID().equals(order.getOrderID()));
    }

    /**
     * A method to check if runner feedback has been filled
     *
     * @param order The order that will be checked
     * @return {@code true} if runner feedback is filled, else {@code false}
     */
    public static boolean checkNeedToFillRunnerFeedback(Order order) {

        // Check if the order is associated with a delivery runner, if no then there's no need for feedback
        if (order.getRunnerInCharge() == null) return false;

        // Find if there is any matching vendor feedback from the customer
        return feedbackList.stream()
                .noneMatch(feedback -> feedback.feedbackCategory == Category.DELIVERY_RUNNER &&
                        feedback.orderAssociated != null &&
                        feedback.orderAssociated.getOrderID().equals(order.getOrderID()));
    }

    /**
     * A method to export Feedback data to Excel file based on filter provided.
     *
     * @param filter The timeframe used to filter the Feedback data
     * @return {@code true} if the Excel file is exported successfully, else {@code false}
     */
    public static boolean exportDataToExcel(Utility.TimeframeFilter filter) {

        // Get feedback list
        ArrayList<Feedback> feedbackList = filterFeedback(filter);

        // Get timeframe
        LocalDateTime[] timeframe = Utility.getFilterStartAndEndTime(filter);

        // Pass to method for exporting to Excel and return the boolean associated
        return Utility.downloadAsExcel(headerList, columnWidth, "Feedback Records", feedbackList, timeframe);
    }

    /**
     * Getters and setters for {@code Feedback} class.
     */
    public String getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(String feedbackID) {
        this.feedbackID = feedbackID;
    }

    public Category getFeedbackCategory() {
        return feedbackCategory;
    }

    public void setFeedbackCategory(Category feedbackCategory) {
        this.feedbackCategory = feedbackCategory;
    }

    public Customer getCustomerAssociated() {
        return customerAssociated;
    }

    public void setCustomerAssociated(Customer customerAssociated) {
        this.customerAssociated = customerAssociated;
    }

    public Order getOrderAssociated() {
        return orderAssociated;
    }

    public void setOrderAssociated(Order orderAssociated) {
        this.orderAssociated = orderAssociated;
    }

    public LocalDateTime getFeedbackSubmissionTime() {
        return feedbackSubmissionTime;
    }

    public void setFeedbackSubmissionTime(LocalDateTime feedbackSubmissionTime) {
        this.feedbackSubmissionTime = feedbackSubmissionTime;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }

    public String getFeedbackTitle() {
        return feedbackTitle;
    }

    public void setFeedbackTitle(String feedbackTitle) {
        this.feedbackTitle = feedbackTitle;
    }

    public String getFeedbackDetails() {
        return feedbackDetails;
    }

    public void setFeedbackDetails(String feedbackDetails) {
        this.feedbackDetails = feedbackDetails;
    }

    public String getReplyFromManager() {
        return replyFromManager;
    }

    public void setReplyFromManager(String replyFromManager) {
        this.replyFromManager = replyFromManager;
    }

    /**
     * A method to print out information for {@code Feedback} objects.
     *
     * @return String representation of the {@code Feedback} objects
     */
    @Override
    public String toString() {
        return "Feedback ID: " + feedbackID + "\n" +
                "Feedback Category: " + feedbackCategory.toString() + "\n" +
                "Customer Associated: " + customerAssociated.toString() + "\n" +
                "Order Associated: " + "\n" +
                orderAssociated + "\n" +
                "Submitted Time: " + Utility.generateString(feedbackSubmissionTime) + "\n" +
                "Ratings: " + ratings + "\n" +
                "Feedback Title: " + feedbackTitle + "\n" +
                "Feedback Details: " + feedbackDetails + "\n" +
                "Reply From Manager: " + replyFromManager;
    }

    /**
     * A method to update the response from manager regarding the feedback received.
     *
     * @param reply The reply from manager
     * @return {@code true} if the reply is set successfully, else {@code false}
     */
    public boolean managerProvideReply(String reply) {

        // Check if reply is empty or null
        if (reply == null || reply.isBlank()) return false;

        // Check if the correct feedback is passed into the method
        if (this.getReplyFromManager() != null || this.getCustomerAssociated() == null) return false;

        // Set the reply
        this.setReplyFromManager(reply);

        // Create notification
        boolean createNotification = CustomerNotification.createNewNotification(
                "Response to Feedback",
                "Thank you for reaching out! Our team has reviewed your feedback " + this.getFeedbackID() + " and provided some response. Please check it out for more details.",
                this.customerAssociated
        );
        if (!createNotification) return false;

        // Write to file and return true
        FeedbackFileIO.writeFile();
        return true;
    }

    /**
     * Enum {@code Category} contains the different {@code Feedback} types.
     */
    public enum Category {

        /**
         * Different status of categories
         */
        SYSTEM, VENDOR, DELIVERY_RUNNER;

        /**
         * The list of available categories in list
         */
        public final static String[] ORDER_CATEGORY_OPTIONS = Arrays.stream(Category.values())      // Get the fields
                .filter(category -> category != SYSTEM)                                             // Remove system
                .map(Category::toString)                                                            // Map to values in toString
                .toArray(String[]::new);                                                            // Return as string array

        /**
         * A method to retrieve {@code Category} based on string input
         *
         * @param category The string input corresponding to the category
         * @return The {@code Category} field corresponding to the string
         */
        public static Category getFromString(String category) {
            return Arrays.stream(Category.values())                                       // Get the fields
                    .filter(field -> field.toString().equalsIgnoreCase(category))         // Compare with input
                    .findFirst()                                                          // Find the first occurrence
                    .orElse(null);                                                        // Return null if not match
        }

        /**
         * A method to return the string value of the {@code Feedback} fields
         *
         * @return The string representation of the fields
         */
        @Override
        public String toString() {
            return switch (this) {
                case SYSTEM -> "System";
                case VENDOR -> "Vendor";
                case DELIVERY_RUNNER -> "Delivery Runner";
            };
        }
    }

    /**
     * An enum class used to store the types of filters used for filtering feedback.
     */
    public enum Filter {

        /**
         * Fields involved in the {@code Filter} enum.
         */
        LOW_TO_HIGH_RATING,
        HIGH_TO_LOW_RATING,
        LATEST_TO_OLDEST,
        OLDEST_TO_LATEST
    }
}
