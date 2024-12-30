package backend.notification;

import backend.entity.Stall;
import backend.entity.Vendor;
import backend.file_io.NotificationIO;
import backend.utility.Utility;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class {@code VendorNotification} represents the notifications for vendors.
 *
 * @author Beng Rhui (TP068495)
 */
public class VendorNotification implements Notification {

    /**
     * Attributes for {@code VendorNotification} objects.<br>
     * An overall list that records all vendor notifications is also included.
     */
    private final static ArrayList<VendorNotification> vendorNotificationList = new ArrayList<>();
    private String notificationID;
    private Vendor vendor;
    private LocalDateTime notificationTime;
    private NotificationStatus readStatus;
    private String notificationTitle;
    private String notificationDetails;

    /**
     * Constructor to instantiate {@code VendorNotification} objects.
     *
     * @param notificationID      The ID of the notification
     * @param vendor              The vendor associated to the notification
     * @param notificationTime    The time the notification is created
     * @param readStatus          Status that records if the vendor has read the message or not
     * @param notificationTitle   The title of the notification
     * @param notificationDetails The description of the notification
     */
    public VendorNotification(String notificationID, Vendor vendor, LocalDateTime notificationTime,
                              NotificationStatus readStatus, String notificationTitle, String notificationDetails) {
        this.notificationID = notificationID;
        this.vendor = vendor;
        this.notificationTime = notificationTime;
        this.readStatus = readStatus;
        this.notificationTitle = notificationTitle;
        this.notificationDetails = notificationDetails;
    }

    /**
     * A method to return a list consisting of all vendor notifications.
     *
     * @return An ArrayList containing all instances of {@code VendorNotification}
     */
    public static ArrayList<VendorNotification> getVendorNotificationList() {
        return vendorNotificationList;
    }

    /**
     * A method to add vendor notifications into an overall list.
     *
     * @param notification The {@code VendorNotification} object to be added to list
     */
    public static void addToList(VendorNotification... notification) {

        // Throws an error if there is no notification or a null notification is passed as argument
        if (notification.length == 0 || Arrays.stream(notification).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Arguments should contain at least one VendorNotification object");
        }

        // Add all the notifications from the arguments into the list
        vendorNotificationList.addAll(
                Arrays.asList(notification)
        );
    }

    /**
     * A method to get notification by ID.
     *
     * @param notificationID The ID of the notification
     * @return The {@code Notification} object associated with the ID
     */
    public static VendorNotification getNotification(String notificationID) {

        // Loop through the list of notification
        for (VendorNotification notification : vendorNotificationList) {

            // Continue the loop if the notification ID does not match
            if (!notification.getNotificationID().equals(notificationID)) {
                continue;
            }

            // Return notification object if ID matches
            return notification;
        }

        // Return null if no ID matches
        return null;
    }

    /**
     * A method to create new vendor notification
     *
     * @param title       The title of the notification
     * @param description The description associated with the notification
     * @param stall       The stall that invoked the notification
     * @return True if notification is created successfully, else false
     */
    public static boolean createNewNotification(String title, String description, Stall stall) {

        // Returns false if the arguments are empty
        if (title.isBlank() || description.isBlank() || stall == null) {
            return false;
        }

        // Search for the vendors involved in the stall
        ArrayList<Vendor> vendorList = Stall.getVendors(stall);
        if (vendorList.isEmpty()) return false;

        // Create a new vendor notification object
        for (Vendor vendor : vendorList) {

            // Create notification for each vendor
            VendorNotification newNotification = new VendorNotification(
                    Notification.generateNewNotificationID(VendorNotification.class),
                    vendor,
                    LocalDateTime.now(),
                    NotificationStatus.UNREAD,
                    title,
                    description
            );

            // Add the notification to list and write to file
            VendorNotification.addToList(newNotification);
            NotificationIO.writeFile();
        }

        // Return true if notification is created successfully
        return true;
    }

    /**
     * A method to delete the notifications associated with the vendor.
     *
     * @param vendorID The ID of vendor
     * @return {@code true} if the operation is successful, else {@code false}
     */
    public static boolean deleteRunnerFromNotification(String vendorID) {

        // Return false if the input is blank
        if (vendorID.isBlank()) return false;

        // Get the list of notifications associated with the vendor
        ArrayList<VendorNotification> vendorNotification = VendorNotification.getVendorNotificationList().stream()
                .filter(notification -> notification.getVendor().getUserID().equals(vendorID))
                .collect(Collectors.toCollection(ArrayList::new));

        // Remove the notifications from the list
        vendorNotificationList.removeAll(vendorNotification);

        // Write to file
        NotificationIO.writeFile();

        // Return true for successful operation
        return true;
    }

    /**
     * A method to create vendor personal notification.
     *
     * @param title       The title of the notification
     * @param description The description for the notification
     * @param vendor      The vendor associated with the notification
     * @return {@code true} if the notification is created successfully, else {@code false}
     */
    public static boolean createNewNotification(String title, String description, Vendor vendor) {

        // Returns false if the arguments are empty
        if (title.isBlank() || description.isBlank() || vendor == null) {
            return false;
        }

        // Create notification for each vendor
        VendorNotification newNotification = new VendorNotification(
                Notification.generateNewNotificationID(VendorNotification.class),
                vendor,
                LocalDateTime.now(),
                NotificationStatus.UNREAD,
                title,
                description
        );

        // Add the notification to list and write to file
        VendorNotification.addToList(newNotification);
        NotificationIO.writeFile();

        // Return true if notification is created successfully
        return true;
    }

    /**
     * Getters and setters associated with the {@code VendorNotification} class.
     */
    @Override
    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
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
        return vendor.getUserID();
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
                "Vendor Involved: " + "\n" +
                vendor.toString() + "\n" +
                "Notification Time: " + Utility.generateString(notificationTime) + "\n" +
                "Notification Status: " + readStatus.toString() + "\n" +
                "Notification Title: " + notificationTitle + "\n" +
                "Notification Details: " + notificationDetails;
    }
}
