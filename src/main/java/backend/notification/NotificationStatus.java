package backend.notification;

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
        return switch (status.toLowerCase()) {
            case "read" -> READ;
            case "unread" -> UNREAD;
            default -> null;
        };
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


