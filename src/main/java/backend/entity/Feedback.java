package backend.entity;

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
     * A list containing all {@code Feedback} objects is also included.
     */
    private final static ArrayList<Feedback> feedbackList = new ArrayList<>();
    private String feedbackID;
    private Category feedbackCategory;
    private Customer customerAssociated;
    private Order orderAssociated;
    private LocalDateTime feedbackSubmissionTime;
    private double ratings;
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
    public Feedback(String feedbackID, Category feedbackCategory, Customer customerAssociated, Order orderAssociated, LocalDateTime feedbackSubmissionTime, double ratings, String feedbackTitle, String feedbackDetails, String replyFromManager) {
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
     * A method to obtain the overall ratings of a stall
     *
     * @param stall The stall where the ratings belong to
     * @return A double value representing the average ratings of a stall.<br>
     * {@code 0} represents the stall does not have a rating
     */
    public static double getOverallRating(Stall stall) {

        // Declare variables to store data to calculate average
        double totalRating = 0;
        int feedbackCount = 0;

        // Loop through the list of feedbacks
        for (Feedback feedback : feedbackList) {

            // Record the rating if the feedback is vendor type and the stall matches
            if (feedback.feedbackCategory == Category.VENDOR && feedback.orderAssociated.getOrderedStall().equals(stall)) {
                totalRating += feedback.ratings;
                feedbackCount++;
            }
        }

        // Return 0 if feedback count is 0, indicating no review is given
        if (feedbackCount == 0) return 0;

        // Return the average value of the reviews
        return totalRating / feedbackCount;
    }

    /**
     * A method to filter the feedback list based on category.
     *
     * @param category The feedback category to be applied as a filter
     * @return A filtered feedback list
     */
    public static ArrayList<Feedback> getFeedbackList(Category category) {

        // Filter the feedback list based on the category
        return getFeedbackList().stream()
                .filter(feedback -> feedback.feedbackCategory == category)
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

    public double getRatings() {
        return ratings;
    }

    public void setRatings(double ratings) {
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
        public final static String[] CATEGORY_OPTIONS = Arrays.stream(Category.values())  // Get the fields
                .map(Category::toString)                                                  // Map to values in toString
                .toArray(String[]::new);                                                  // Return as string array

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
