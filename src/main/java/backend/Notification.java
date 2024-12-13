package backend;

/**
 * Interface {@code Notification} includes the basic methods that a notification should have.
 */
public interface Notification {

    /**
     * Basic methods in {@code Notification} interface
     */
    String getMessage();

    void markAsRead();

}