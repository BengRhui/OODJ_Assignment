import backend.entity.Item;
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
    public static ArrayList<Notification> getDifferentNotification(ArrayList<Notification> initialList, ArrayList<Notification> newList) {

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

    /**
     * A method to obtain the item objects that does not exist in the initial list.
     * @param initialList The initial list containing item objects before modification
     * @param newList The new list containing item objects after modification
     * @return An array consisting of item objects not in initial list
     */
    public static ArrayList<Item> getDifferentItem(ArrayList<Item> initialList, ArrayList<Item> newList) {

        // Declare an empty list to store different item objects
        ArrayList<Item> differentList = new ArrayList<>();

        // Loop through the items in the new list
        for (Item newItem : newList) {

            // Declare a variable to record whether item is in the list
            boolean isInList = false;

            // Loop through the initial list
            for (Item initialItem : initialList) {

                // Change boolean to true and end list if item ID matches
                if (initialItem.getItemID().equals(newItem.getItemID())) {
                    isInList = true;
                    break;
                }
            }

            // Add item to list if item is not in the list
            if (!isInList) differentList.add(newItem);
        }

        // Return the list
        return differentList;
    }
}
