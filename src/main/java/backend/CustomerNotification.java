package backend;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class {@code CustomerNotification} represents the notification that customers will receive in the system.
 * @author Beng Rhui (TP068495)
 */
public class CustomerNotification implements Notification{

    /**
     * Attributes for {@code CustomerNotification} class.<br>
     * An overall list containing all {@code CustomerNotification} objects is included.<br>
     */
    private String notificationID;
    private Customer customer;
    private LocalDateTime notificationTime;
    private NotificationStatus readStatus;
    private String notificationTitle;
    private String notificationDetails;

    private final static ArrayList<Notification> customerNotificationList = new ArrayList<>();

    /**
     * Constructor to instantiate {@code CustomerNotification} objects.
     *
     * @param notificationID      The ID of the notification
     * @param customer              The customer associated with the notification
     * @param notificationTime The time when the notification is created
     * @param readStatus Status to record if customer has read the notification
     * @param notificationTitle The title of the notification
     * @param notificationDetails The description of the notification
     */
    public CustomerNotification(String notificationID, Customer customer, LocalDateTime notificationTime,
                                NotificationStatus readStatus, String notificationTitle, String notificationDetails) {
        this.notificationID = notificationID;
        this.customer = customer;
        this.notificationTime = notificationTime;
        this.readStatus = readStatus;
        this.notificationTitle = notificationTitle;
        this.notificationDetails = notificationDetails;
        addToList(this);
    }

    /**
     * Getters and setters for {@code CustomerNotification} class.
     */
    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDateTime getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(LocalDateTime notificationTime) {
        this.notificationTime = notificationTime;
    }

    public NotificationStatus getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(NotificationStatus readStatus) {
        this.readStatus = readStatus;
    }

    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    public String getNotificationDetails() {
        return notificationDetails;
    }

    public void setNotificationDetails(String notificationDetails) {
        this.notificationDetails = notificationDetails;
    }

    /**
     * A method to retrieve the list containing all customer notifications.
     * @return An ArrayList consisting of all {@code CustomerNotification} instances
     */
    public static ArrayList<Notification> getCustomerNotificationList() {
        return customerNotificationList;
    }

    /**
     * A method to add customer notifications into an overall list.
     * @param notification The {@code CustomerNotification} object to be added to list
     */
    public static void addToList(CustomerNotification notification) {
        customerNotificationList.add(notification);
    }

    /**
     * A method to print out the full information for customer notifications.
     * @return String representation of the {@code CustomerNotification} object
     */
    @Override
    public String toString() {
        return "Notification ID: " + notificationID + "\n" +
                "Customer Involved: " + "\n" +
                customer.toString() + "\n" +
                "Notification Time: " + Utility.printTime(notificationTime) + "\n" +
                "Notification Status: " + readStatus.toString() + "\n" +
                "Notification Title: " + notificationTitle + "\n" +
                "Notification Details: " + notificationDetails;
    }
    /**
     * A method to retrieve the message portion of customer notifications.
     * @return The message details of customer notification
     */
    @Override
    public String getMessage() {
        return "Notification Title: " + notificationTitle + "\n" +
                "Notification Details: " + notificationDetails;
    }

    /**
     * A method to switch the status of notifications from "unread" to "read".
     */
    @Override
    public void markAsRead() {
        this.readStatus = NotificationStatus.READ;
    }
}
