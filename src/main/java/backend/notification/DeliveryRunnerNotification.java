package backend.notification;

import backend.entity.DeliveryRunner;
import backend.utility.Utility;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Class {@code DeliveryRunnerNotification} represents the notifications that will be received by the delivery runners.
 *
 * @author Beng Rhui (TP068495)
 */
public class DeliveryRunnerNotification implements Notification {

    /**
     * Attributes for the {@code DeliveryRunnerNotification} objects.<br>
     * A list containing all notifications for delivery runners is included.
     */
    private final static ArrayList<Notification> deliveryRunnerNotificationList = new ArrayList<>();
    private String notificationID;
    private DeliveryRunner runner;
    private LocalDateTime notificationTime;
    private NotificationStatus readStatus;
    private String notificationTitle;
    private String notificationDetails;

    /**
     * Constructor to instantiate the {@code DeliveryRunnerNotification} object.
     *
     * @param notificationID      The ID of the notification
     * @param runner              The delivery runner associated with the notification
     * @param notificationTime    The time when the notification is created
     * @param readStatus          The status that records if the runner has read the notifications or not
     * @param notificationTitle   The title of the notification
     * @param notificationDetails The description of the notification
     */
    public DeliveryRunnerNotification(String notificationID, DeliveryRunner runner,
                                      LocalDateTime notificationTime, NotificationStatus readStatus,
                                      String notificationTitle, String notificationDetails) {
        this.notificationID = notificationID;
        this.runner = runner;
        this.notificationTime = notificationTime;
        this.readStatus = readStatus;
        this.notificationTitle = notificationTitle;
        this.notificationDetails = notificationDetails;
    }

    /**
     * A method to return the list that contains all notifications for delivery runner.
     *
     * @return An ArrayList consisting of all {@code DeliveryRunnerNotification} instances.
     */
    public static ArrayList<Notification> getDeliveryRunnerNotificationList() {
        return deliveryRunnerNotificationList;
    }

    /**
     * A method to add the notifications for delivery runner into an overall list.
     *
     * @param notification The {@code DeliveryRunnerNotification} objects to be added to list
     */
    public static void addToList(DeliveryRunnerNotification... notification) {

        // Throws an error if there is no notification or a null notification is passed as argument
        if (notification.length == 0 || Arrays.stream(notification).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Arguments should contain at least one DeliveryRunnerNotification object");
        }

        // Add all the notifications from the arguments into the list
        deliveryRunnerNotificationList.addAll(
                Arrays.asList(notification)
        );
    }

    /**
     * Getters and setters for the {@code DeliveryRunnerNotification} class.
     */
    @Override
    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public DeliveryRunner getRunner() {
        return runner;
    }

    public void setRunner(DeliveryRunner runner) {
        this.runner = runner;
    }

    @Override
    public LocalDateTime getNotificationTime() {
        return notificationTime;
    }

    public void setNotificationTime(LocalDateTime notificationTime) {
        this.notificationTime = notificationTime;
    }

    @Override
    public NotificationStatus getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(NotificationStatus readStatus) {
        this.readStatus = readStatus;
    }

    @Override
    public String getNotificationTitle() {
        return notificationTitle;
    }

    public void setNotificationTitle(String notificationTitle) {
        this.notificationTitle = notificationTitle;
    }

    @Override
    public String getNotificationDetails() {
        return notificationDetails;
    }

    public void setNotificationDetails(String notificationDetails) {
        this.notificationDetails = notificationDetails;
    }

    /**
     * A method to return the ID related to the associated entity
     *
     * @return The ID of the entity
     */
    @Override
    public String getEntityID() {
        return runner.getUserID();
    }

    /**
     * A method to print out the full information of the {@code DeliveryRunnerNotification} object.
     *
     * @return String representation of the {@code DeliveryRunnerNotification} object
     */
    @Override
    public String toString() {
        return "Notification ID: " + notificationID + "\n" +
                "Runner Involved: " + "\n" +
                runner.toString() + "\n" +
                "Notification Time: " + Utility.generateString(notificationTime) + "\n" +
                "Notification Status: " + readStatus.toString() + "\n" +
                "Notification Title: " + notificationTitle + "\n" +
                "Notification Details: " + notificationDetails;
    }

    /**
     * A method to retrieve the message portion of the {@code DeliveryRunnerNotification} object
     *
     * @return The message part of the notifications for delivery runner
     */
    @Override
    public String getMessage() {
        return "Notification Title: " + notificationTitle + "\n" +
                "Notification Details: " + notificationDetails;
    }

    /**
     * A method that changes the status of notification from "unread" to "read".
     */
    @Override
    public void markAsRead() {
        this.readStatus = NotificationStatus.READ;
    }
}
