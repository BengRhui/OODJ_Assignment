package backend;

/**
 * Enum {@code NotificationStatus} represents the different types of status in a notification.
 */
public enum NotificationStatus {

    /**
     * Default states for {@code NotificationStatus}
     */
    READ, UNREAD;

    /**
     * A method to print out the status of notification
     *
     * @return String representation of the notification status
     */
    public String toString() {
        return switch (this) {
            case READ -> "Read";
            case UNREAD -> "Unread";
        };
    }
}


