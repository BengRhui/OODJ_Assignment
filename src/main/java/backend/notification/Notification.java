package backend.notification;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Interface {@code Notification} includes the basic methods that a notification should have.
 */
public interface Notification {

    /**
     * A method to generate new notification ID based on notification class
     *
     * @param clazz The class type of the notification
     * @param <T>   Used to declare that the method can receive any types of class
     * @return The new notification ID
     */
    static <T> String generateNewNotificationID(Class<T> clazz) {

        // Declare variables to store prefix, index and the notification list to be looped
        final String prefix;
        int index = 1;
        final ArrayList<? extends Notification> notificationList;

        // Obtain and assign different prefix and index based on the types of notification class
        switch (clazz.getSimpleName()) {

            // When class is delivery runner notification
            case "DeliveryRunnerNotification" -> {
                prefix = "ND";
                notificationList = DeliveryRunnerNotification.getDeliveryRunnerNotificationList();
            }

            // When class is vendor notification
            case "VendorNotification" -> {
                prefix = "NV";
                notificationList = VendorNotification.getVendorNotificationList();
            }

            // When class is customer notification
            case "CustomerNotification" -> {
                prefix = "NC";
                notificationList = CustomerNotification.getCustomerNotificationList();
            }

            // Throw an exception if the class is not a notification class
            default -> throw new IllegalStateException("Unexpected class: " + clazz.getSimpleName());
        }

        // Start an infinite loop
        while (true) {

            // Generate ID based on current index and prefix
            String generatedID = String.format("%s%03d", prefix, index);

            // Check if the ID has been used
            boolean idHasBeenUsed = notificationList.stream()                                           // Get notification list
                    .anyMatch(notification -> notification.getNotificationID().equals(generatedID));    // Find if the generated ID matches existing ID

            // If the ID is not used, then return the ID
            if (!idHasBeenUsed) return generatedID;

            // If the ID is used, increment index and continue the loop
            index++;
        }
    }

    /**
     * Basic methods in {@code Notification} interface
     */
    String getMessage();

    void markAsRead();

    String getNotificationID();

    String getEntityID();

    LocalDateTime getNotificationTime();

    NotificationStatus getReadStatus();

    String getNotificationTitle();

    String getNotificationDetails();
}