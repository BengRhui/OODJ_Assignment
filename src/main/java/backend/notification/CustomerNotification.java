package backend.notification;

import backend.entity.Customer;
import backend.file_io.NotificationIO;
import backend.utility.Utility;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Class {@code CustomerNotification} represents the notification that customers will receive in the system.
 *
 * @author Beng Rhui (TP068495)
 */
public class CustomerNotification implements Notification {

    /**
     * Attributes for {@code CustomerNotification} class.<br>
     * An overall list containing all {@code CustomerNotification} objects is included.
     */
    private final static ArrayList<CustomerNotification> customerNotificationList = new ArrayList<>();
    private String notificationID;
    private Customer customer;
    private LocalDateTime notificationTime;
    private NotificationStatus readStatus;
    private String notificationTitle;
    private String notificationDetails;

    /**
     * Constructor to instantiate {@code CustomerNotification} objects.
     *
     * @param notificationID      The ID of the notification
     * @param customer            The customer associated with the notification
     * @param notificationTime    The time when the notification is created
     * @param readStatus          Status to record if customer has read the notification
     * @param notificationTitle   The title of the notification
     * @param notificationDetails The description of the notification
     */
    public CustomerNotification(String notificationID, Customer customer, LocalDateTime notificationTime,
                                NotificationStatus readStatus, String notificationTitle, String notificationDetails) {
        this.notificationID = notificationID;
        this.customer = customer;
        this.notificationTime = notificationTime;
        this.readStatus = readStatus;
        this.notificationTitle = notificationTitle;
        this.notificationDetails = notificationDetails;
    }

    /**
     * A method to retrieve the list containing all customer notifications.
     *
     * @return An ArrayList consisting of all {@code CustomerNotification} instances
     */
    public static ArrayList<CustomerNotification> getCustomerNotificationList() {
        return customerNotificationList;
    }

    /**
     * A method to delete the notifications associated with the customer.
     *
     * @param customerID The ID of the customer
     * @return {@code true} if the operation is successful, else {@code false}
     */
    public static boolean deleteCustomerFromNotification(String customerID) {

        // Return false if the input is blank
        if (customerID.isBlank()) return false;

        // Get the list of notifications associated with the customer
        ArrayList<CustomerNotification> customerNotification = CustomerNotification.getCustomerNotificationList().stream()
                .filter(notification -> notification.getCustomer().getUserID().equals(customerID))
                .collect(Collectors.toCollection(ArrayList::new));

        // Remove the notifications from the list
        customerNotificationList.removeAll(customerNotification);

        // Write to file
        NotificationIO.writeFile();

        // Return true for successful operation
        return true;
    }

    /**
     * A method to add customer notifications into an overall list.
     *
     * @param notification The {@code CustomerNotification} objects to be added to list
     */
    public static void addToList(CustomerNotification... notification) {

        // Throws an error if there is no notification or a null notification is passed as argument
        if (notification.length == 0 || Arrays.stream(notification).anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException("Arguments should contain at least one CustomerNotification object");
        }

        // Add all the notifications from the arguments into the list
        customerNotificationList.addAll(
                Arrays.asList(notification)
        );
    }

    /**
     * A method to get notification by ID.
     *
     * @param notificationID The ID of the notification
     * @return The {@code Notification} object associated with the ID
     */
    public static CustomerNotification getNotification(String notificationID) {

        // Loop through the list of notification
        for (CustomerNotification notification : customerNotificationList) {

            // Continue the loop if the notification ID does not match
            if (!notification.getNotificationID().equals(notificationID)) {
                continue;
            }

            // Return notification object if ID matches
            return notification;
        }

        // Return null if no ID matches
        return null;
    }

    /**
     * A method to create new customer notification
     *
     * @param title       The title of the notification
     * @param description The description associated with the notification
     * @param customer    The customer associated with the notification
     * @return True if notification is created successfully, else false
     */
    public static boolean createNewNotification(String title, String description, Customer customer) {

        // Returns false if the arguments are empty
        if (title.isBlank() || description.isBlank() || customer == null) {
            return false;
        }

        // Create a new customer notification object
        CustomerNotification newNotification = new CustomerNotification(
                Notification.generateNewNotificationID(CustomerNotification.class),
                customer,
                LocalDateTime.now(),
                NotificationStatus.UNREAD,
                title,
                description
        );

        // Add the notification to list and write to file, then return true to indicate success creation
        CustomerNotification.addToList(newNotification);
        NotificationIO.writeFile();
        return true;
    }

    /**
     * A method to retrieve the list of notifications related to a customer.
     *
     * @param customer The customer involved in the notification
     * @return The filtered notification list
     */
    public static ArrayList<Notification> getNotificationList(Customer customer) {

        // Retrieve the list of customer notifications
        ArrayList<CustomerNotification> notificationList = new ArrayList<>(CustomerNotification.getCustomerNotificationList());

        // Filter the notification list based on customer ID then arrange them in descending order based on time
        return notificationList.stream()
                .filter(notification -> notification.customer.getUserID().equals(customer.getUserID()))
                .sorted(Comparator.comparing(CustomerNotification::getNotificationTime).reversed())
                .collect(Collectors.toCollection(ArrayList::new));
    }

    /**
     * Getters and setters for {@code CustomerNotification} class.
     */
    @Override
    public String getNotificationID() {
        return notificationID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public LocalDateTime getNotificationTime() {
        return notificationTime;
    }

    @Override
    public NotificationStatus getReadStatus() {
        return readStatus;
    }

    public void setReadStatus(NotificationStatus readStatus) {
        this.readStatus = readStatus;
    }

    @Override
    public String getNotificationTitle() {
        return notificationTitle;
    }

    @Override
    public String getNotificationDetails() {
        return notificationDetails;
    }

    /**
     * A method to return the ID related to the associated entity
     *
     * @return The ID of the entity
     */
    @Override
    public String getEntityID() {
        return customer.getUserID();
    }

    /**
     * A method to print out the full information for customer notifications.
     *
     * @return String representation of the {@code CustomerNotification} object
     */
    @Override
    public String toString() {
        return "Notification ID: " + notificationID + "\n" +
                "Customer Involved: " + "\n" +
                customer.toString() + "\n" +
                "Notification Time: " + Utility.generateString(notificationTime) + "\n" +
                "Notification Status: " + readStatus.toString() + "\n" +
                "Notification Title: " + notificationTitle + "\n" +
                "Notification Details: " + notificationDetails;
    }

    /**
     * A method to switch the status of notifications from "unread" to "read".
     */
    @Override
    public void markAsRead() {

        // Change the status
        this.readStatus = NotificationStatus.READ;

        // Write to file
        NotificationIO.writeFile();
    }
}
