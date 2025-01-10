package backend.notification;

import java.util.Arrays;

/**
 * Enum {@code NotificationStatus} represents the different types of status in a notification.
 *
 * @author Beng Rhui (TP068495)
 */
public enum NotificationStatus {

    /**
     * Default states for {@code NotificationStatus}
     */
    READ, UNREAD;

    /**
     * A method to get notification status from text.
     *
     * @param status The string representing the read status
     * @return The status in {@code NotificationStatus} class
     */
    public static NotificationStatus fromString(String status) {
        return Arrays.stream(NotificationStatus.values())                    // Get all fields
                .filter(field -> field.toString().equalsIgnoreCase(status))  // Compare input with strings from toString
                .findFirst()                                                 // Find the first occurrence
                .orElse(null);                                         // Return null if nothing is found
    }

    /**
     * A method to print out the status of notification
     *
     * @return String representation of the notification status
     */
    @Override
    public String toString() {
        return switch (this) {
            case READ -> "Read";
            case UNREAD -> "Unread";
        };
    }
}


