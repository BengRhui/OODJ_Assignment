package backend;

import java.util.ArrayList;

/**
 * Class {@code Feedback} represents the feedback that customers provide to the system, vendor and delivery runner.
 */
public class Feedback {

    private final static ArrayList<Feedback> feedbackList = new ArrayList<Feedback>();
    /**
     * Attributes for {@code Feedback} objects.<br>
     * A list containing all {@code Feedback} objects is also included.
     */
    private String feedbackID;
    private String feedbackCategory;
    private Order orderAssociated;
    private double ratings;
    private String feedbackTitle;
    private String feedbackDetails;
    private String replyFromManager;

    /**
     * Constructor to instantiate {@code Feedback} objects.
     *
     * @param feedbackID          The ID of feedback
     * @param feedbackCategory    The categories of feedback, i.e. system, vendor and delivery runner
     * @param orderAssociated     The Order item associated with the feedback
     * @param ratings             The ratings provided by the customer in the feedback
     * @param feedbackTitle       The title of the feedback
     * @param feedbackDetails     The description of the feedback
     * @param responseFromManager The reply that the manager provides to the customer
     */
    public Feedback(String feedbackID, String feedbackCategory, Order orderAssociated, double ratings, String feedbackTitle, String feedbackDetails, String responseFromManager) {
        this.feedbackID = feedbackID;
        this.feedbackCategory = feedbackCategory;
        this.orderAssociated = orderAssociated;
        this.ratings = ratings;
        this.feedbackTitle = feedbackTitle;
        this.feedbackDetails = feedbackDetails;
        this.replyFromManager = responseFromManager;
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
     * Getters and setters for {@code Feedback} class.
     */
    public String getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(String feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getFeedbackCategory() {
        return feedbackCategory;
    }

    public void setFeedbackCategory(String feedbackCategory) {
        this.feedbackCategory = feedbackCategory;
    }

    public Order getOrderAssociated() {
        return orderAssociated;
    }

    public void setOrderAssociated(Order orderAssociated) {
        this.orderAssociated = orderAssociated;
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
     * A method to add {@code Feedback} objects to the overall list.
     *
     * @param feedback The {@code Feedback} object to be added to list
     */
    public void addToFeedbackList(Feedback feedback) {
        feedbackList.add(feedback);
    }

    /**
     * A method to print out information for {@code Feedback} objects.
     *
     * @return String representation of the {@code Feedback} objects
     */
    @Override
    public String toString() {
        return "Feedback ID: " + feedbackID + "\n" +
                "Feedback Category: " + feedbackCategory + "\n" +
                "Order Associated: " + "\n" +
                orderAssociated + "\n" +
                "Ratings: " + ratings + "\n" +
                "Feedback Title: " + feedbackTitle + "\n" +
                "Feedback Details: " + feedbackDetails + "\n" +
                "Reply From Manager: " + replyFromManager;
    }
}
