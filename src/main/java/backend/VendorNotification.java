package backend;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class {@code VendorNotification} represents the notifications for vendors.
 *
 * @author Beng Rhui (TP068495)
 */
public class VendorNotification implements Notification {

    private final static ArrayList<Notification> vendorNotificationList = new ArrayList<>();
    /**
     * Attributes for {@code VendorNotification} objects.
     * An overall list that records all vendor notifications is also included.
     */
    private String notificationID;
    private Stall stall;
    private LocalDateTime notificationTime;
    private NotificationStatus readStatus;
    private String notificationTitle;
    private String notificationDetails;

    /**
     * Constructor to instantiate {@code VendorNotification} objects.
     *
     * @param notificationID      The ID of the notification
     * @param stall               The stall associated to the notification
     * @param notificationTime    The time the notification is created
     * @param readStatus          Status that records if the vendor has read the message or not
     * @param notificationTitle   The title of the notification
     * @param notificationDetails The description of the notification
     */
    public VendorNotification(String notificationID, Stall stall, LocalDateTime notificationTime,
                              NotificationStatus readStatus, String notificationTitle, String notificationDetails) {
        this.notificationID = notificationID;
        this.stall = stall;
        this.notificationTime = notificationTime;
        this.readStatus = readStatus;
        this.notificationTitle = notificationTitle;
        this.notificationDetails = notificationDetails;
        addToList(this);
    }

    /**
     * A method to return a list consisting of all vendor notifications.
     *
     * @return An ArrayList containing all instances of {@code VendorNotification}
     */
    public static ArrayList<Notification> getVendorNotificationList() {
        return vendorNotificationList;
    }

    /**
     * A method to add vendor notifications into an overall list.
     *
     * @param notification The {@code VendorNotification} object to be added to list
     */
    public static void addToList(VendorNotification notification) {
        vendorNotificationList.add(notification);
    }

    /**
     * Getters and setters associated with the {@code VendorNotification} class.
     */
    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public Stall getStall() {
        return stall;
    }

    public void setStall(Stall stall) {
        this.stall = stall;
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
     * A method to return the message portion of the vendor notification.
     *
     * @return String representation of the message part of {@code VendorNotification} object
     */
    @Override
    public String getMessage() {
        return "Notification Title: " + notificationTitle + "\n" +
                "Notification Details: " + notificationDetails;
    }

    /**
     * A method to change the status of notification from "unread" to "read".
     */
    @Override
    public void markAsRead() {
        this.readStatus = NotificationStatus.READ;
    }

    /**
     * A method to print out the full information of vendor notifications.
     *
     * @return String representation of the {@code VendorNotification} object
     */
    @Override
    public String toString() {
        return "Notification ID: " + notificationID + "\n" +
                "Stall Involved: " + "\n" +
                stall.toString() + "\n" +
                "Notification Time: " + Utility.printTime(notificationTime) + "\n" +
                "Notification Status: " + readStatus.toString() + "\n" +
                "Notification Title: " + notificationTitle + "\n" +
                "Notification Details: " + notificationDetails;
    }
}
