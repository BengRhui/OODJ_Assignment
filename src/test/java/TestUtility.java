import backend.notification.Notification;

import java.util.ArrayList;

/**
 * The {@code TestUtility} class is used to store functions that helps to simplify coding in the other testing classes.
 *
 * @author Beng Rhui (TP068495)
 */
public class TestUtility {

    /**
     * A method to retrieve the different notifications in a new notification list
     *
     * @param initialList The initial list before modification takes place
     * @param newList     The list after modification takes place
     * @return An array consisting of the notification not in the initial list
     */
    public static ArrayList<Notification> getDifferent(ArrayList<Notification> initialList, ArrayList<Notification> newList) {

        // Declare empty list to accommodate the different notifications
        ArrayList<Notification> differentList = new ArrayList<>();

        // Loop through the new notification list: Check if the new notification is in the initial list
        for (Notification newNotification : newList) {

            // Declare a boolean variable to check if the notification is in the initial list
            boolean isInList = false;

            // Loop through the initial list
            for (Notification initialNotification : initialList) {

                // If the notification ID matches, skip the current notification
                if (initialNotification.getNotificationID().equals(newNotification.getNotificationID())) {
                    isInList = true;
                    break;
                }
            }

            // Add to list if the notification is not in the list
            if (!isInList) differentList.add(newNotification);
        }

        // Return the list containing new notifications
        return differentList;
    }

    /**
     * A method to convert different types of notification array (customer notification etc.) to generic notification array
     *
     * @param notifications The notification array of specific type
     * @return The notification array of generic type
     */
    public static ArrayList<Notification> convertToNotificationArray(ArrayList<? extends Notification> notifications) {
        return new ArrayList<>(
                notifications.stream()                                        // Pass the array as a stream
                        .map(notification -> (Notification) notification)     // Convert each element to a notification item
                        .toList()                                             // Return back to list
        );
    }
}
