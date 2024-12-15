package backend.file_io;

import backend.entity.Customer;
import backend.entity.DeliveryRunner;
import backend.entity.Stall;
import backend.notification.*;
import backend.utility.Utility;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class {@code NotificationIO} contains the methods to read and write files related to notification.
 * @author Beng Rhui (TP068495)
 */
public class NotificationIO extends FileIO {

    /**
     * Fixed variable to help in coding.
     */
    private final static String NOTIFICATION_FILE_NAME = "notifications.txt";

    /**
     * A method to read notification file and instantiate different {@code Notification} objects
     */
    public static void readFile() {

        // Obtain the list of notifications from text file
        ArrayList<String[]> notificationList = getListFromFile(NOTIFICATION_FILE_NAME);

        // Loop through all lists
        for (String[] record : notificationList) {

            // Retrieve the associated object ID (stall, runner or customer)
            String ID = record[1];

            // Check the first letter of the ID and create respective notifications
            switch (ID.charAt(0)) {
                case 'S' -> createVendorNotification(record);
                case 'R' -> createDeliveryRunnerNotification(record);
                case 'C' -> createCustomerNotification(record);
            }
        }
    }

    /**
     * A method to create {@code VendorNotification} objects based on record.
     * @param record The string record obtained from text file
     */
    public static void createVendorNotification(String[] record) {

        // Retrieve information from the string array
        String notificationID = record[0];
        Stall stall = Stall.getStall(record[1]);
        LocalDateTime notificationTime = Utility.changeStringToTime(record[2]);
        NotificationStatus readStatus = NotificationStatus.fromString(record[3]);
        String notificationTitle = record[4];
        String notificationDetails = record[5];

        // Create vendor notification
        VendorNotification newNotification = new VendorNotification(
                notificationID,
                stall,
                notificationTime,
                readStatus,
                notificationTitle,
                notificationDetails
        );

        // Add the notification to list
        VendorNotification.addToList(newNotification);
    }

    /**
     * A method to create {@code DeliveryRunnerNotification} based on string record
     * @param record The string record obtained from text file
     */
    public static void createDeliveryRunnerNotification(String[] record) {

        // Retrieve information from string array
        String notificationID = record[0];
        DeliveryRunner runner = DeliveryRunner.getRunner(record[1]);
        LocalDateTime notificationTime = Utility.changeStringToTime(record[2]);
        NotificationStatus readStatus = NotificationStatus.fromString(record[3]);
        String notificationTitle = record[4];
        String notificationDetails = record[5];

        // Create delivery runner notification
        DeliveryRunnerNotification newNotification = new DeliveryRunnerNotification(
                notificationID,
                runner,
                notificationTime,
                readStatus,
                notificationTitle,
                notificationDetails
        );

        // Add notification to list
        DeliveryRunnerNotification.addToList(newNotification);
    }

    /**
     * A method to create {@code CustomerNotification} based on the string record
     * @param record The string record obtained from text file
     */
    public static void createCustomerNotification(String[] record) {

        // Retrieve information from string array
        String notificationID = record[0];
        Customer customer = Customer.getCustomer(record[1]);
        LocalDateTime notificationTime = Utility.changeStringToTime(record[2]);
        NotificationStatus readStatus = NotificationStatus.fromString(record[3]);
        String notificationTitle = record[4];
        String notificationDetails = record[5];

        // Create customer notification
        CustomerNotification newNotification = new CustomerNotification(
                notificationID,
                customer,
                notificationTime,
                readStatus,
                notificationTitle,
                notificationDetails
        );

        // Add notification to list
        CustomerNotification.addToList(newNotification);
    }
}
