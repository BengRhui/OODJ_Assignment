package backend.notification;

import java.time.LocalDateTime;

/**
 * Interface {@code Notification} includes the basic methods that a notification should have.
 */
public interface Notification {

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