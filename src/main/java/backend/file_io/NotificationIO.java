package backend.file_io;

import backend.entity.Customer;
import backend.entity.DeliveryRunner;
import backend.entity.Vendor;
import backend.notification.*;
import backend.utility.Utility;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Class {@code NotificationIO} contains the methods to read and write files related to notification.
 *
 * @author Beng Rhui (TP068495)
 */
public class NotificationIO extends FileIO {

    /**
     * Fixed variable to help in coding.
     */
    public final static String NOTIFICATION_FILE_NAME = "notifications.txt";
    public final static int NUMBER_OF_INFORMATION_IN_FILE = 6;
    public final static int[] SPACING_SIZE = {5, 5, 20, 10, 50, 100};

    /**
     * A method to read notification file and instantiate different {@code Notification} objects
     */
    public static void readFile() {

        // Clear the array list before reading file
        CustomerNotification.getCustomerNotificationList().clear();
        VendorNotification.getVendorNotificationList().clear();
        DeliveryRunnerNotification.getDeliveryRunnerNotificationList().clear();

        // Obtain the list of notifications from text file
        ArrayList<String[]> notificationList = getListFromFile(NOTIFICATION_FILE_NAME);

        // Loop through all lists
        for (String[] record : notificationList) {

            // Retrieve the associated notification ID
            String ID = record[0];

            // Check the second letter of the ID and create respective notifications
            switch (ID.charAt(1)) {
                case 'V' -> createVendorNotification(record);
                case 'R' -> createDeliveryRunnerNotification(record);
                case 'C' -> createCustomerNotification(record);
            }
        }
    }

    /**
     * A method to create {@code VendorNotification} objects based on record.
     *
     * @param record The string record obtained from text file
     */
    public static void createVendorNotification(String[] record) {

        // Retrieve information from the string array
        String notificationID = record[0];
        Vendor vendor = Vendor.getVendor(record[1]);
        LocalDateTime notificationTime = Utility.changeStringToTime(record[2]);
        NotificationStatus readStatus = NotificationStatus.fromString(record[3]);
        String notificationTitle = record[4];
        String notificationDetails = record[5];

        // Create vendor notification
        VendorNotification newNotification = new VendorNotification(
                notificationID,
                vendor,
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
     *
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
     *
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

    /**
     * A method to write notification objects into file.
     */
    public static void writeFile() {

        // Combine all types of notification lists
        ArrayList<Notification> notificationList = new ArrayList<>();
        notificationList.addAll(CustomerNotification.getCustomerNotificationList());
        notificationList.addAll(DeliveryRunnerNotification.getDeliveryRunnerNotificationList());
        notificationList.addAll(VendorNotification.getVendorNotificationList());

        // Sort the notification list based on notification ID using lambda expression
        notificationList.sort(
                (notification1, notification2) ->
                        notification1.getNotificationID()
                                .compareToIgnoreCase(notification2.getNotificationID())
        );

        // Create a local variable to store all string arrays to be written to text file
        ArrayList<String[]> information = new ArrayList<>();

        // Loop through all notification
        for (Notification notification : notificationList) {

            // Add each information into the string array and append to overall list
            String[] record = new String[NUMBER_OF_INFORMATION_IN_FILE];
            record[0] = notification.getNotificationID();
            record[1] = notification.getEntityID();
            record[2] = Utility.generateString(notification.getNotificationTime());
            record[3] = notification.getReadStatus().toString();
            record[4] = notification.getNotificationTitle();
            record[5] = notification.getNotificationDetails();
            information.add(record);
        }

        // Write the information into text file
        writeListToFile(NOTIFICATION_FILE_NAME, information, SPACING_SIZE);
    }
}
