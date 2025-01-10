package backend.notification;

import java.time.LocalDateTime;

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

        // Declare variables to store prefix and index
        final String prefix;
        final int index;

        // Obtain and assign different prefix and index based on the types of notification class
        switch (clazz.getSimpleName()) {

            // When class is delivery runner notification
            case "DeliveryRunnerNotification" -> {
                prefix = "ND";
                index = DeliveryRunnerNotification.getDeliveryRunnerNotificationList().size();
            }

            // When class is vendor notification
            case "VendorNotification" -> {
                prefix = "NV";
                index = VendorNotification.getVendorNotificationList().size();
            }

            // When class is customer notification
            case "CustomerNotification" -> {
                prefix = "NC";
                index = CustomerNotification.getCustomerNotificationList().size();
            }

            // Throw an exception if the class is not a notification class
            default -> throw new IllegalStateException("Unexpected class: " + clazz.getSimpleName());
        }

        // Format the string with prefix and three-digit numbers before returning it
        return String.format("%s%03d", prefix, index + 1);
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