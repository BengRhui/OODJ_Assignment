import backend.entity.Order;
import backend.notification.CustomerNotification;
import backend.notification.DeliveryRunnerNotification;
import backend.notification.Notification;
import backend.notification.VendorNotification;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class {@code RunnerTest} focuses on the operations that a delivery runner would perform.
 *
 * @author Beng Rhui (TP068495)
 */
public class RunnerTest extends BaseTest {

    /**
     * This test focuses on the operation where the runner updates the status of an order.
     */
    @Test
    void runnerUpdateOrderStatus() {

        // Get initial notification list
        ArrayList<Notification> initialCustomerList = TestUtility.convertToNotificationArray(
                CustomerNotification.getCustomerNotificationList()
        );

        ArrayList<Notification> initialVendorList = TestUtility.convertToNotificationArray(
                VendorNotification.getVendorNotificationList()
        );

        ArrayList<Notification> initialRunnerList = TestUtility.convertToNotificationArray(
                DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
        );

        // Order 1: Delivery - set status to either vendor preparing or ready for pickup
        order1.setOrderStatus(Order.OrderStatus.VENDOR_PREPARING);

        // Mark order as runner delivering
        int pickedUpStatus = order1.runnerUpdateOrderStatus(Order.OrderStatus.RUNNER_DELIVERY);

        // Check if operation is performed successfully
        assertEquals(1, pickedUpStatus);

        // Check if the status is updated correctly
        assertEquals(Order.OrderStatus.RUNNER_DELIVERY, order1.getOrderStatus());

        // Get the new notification lists
        ArrayList<Notification> newCustomerList = TestUtility.convertToNotificationArray(
                CustomerNotification.getCustomerNotificationList()
        );

        ArrayList<Notification> newVendorList = TestUtility.convertToNotificationArray(
                VendorNotification.getVendorNotificationList()
        );

        ArrayList<Notification> newRunnerList = TestUtility.convertToNotificationArray(
                DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
        );

        // Compare difference between the initial and new lists
        ArrayList<Notification> differentCustomer = TestUtility.getDifferentNotification(initialCustomerList, newCustomerList);
        ArrayList<Notification> differentVendor = TestUtility.getDifferentNotification(initialVendorList, newVendorList);
        ArrayList<Notification> differentRunner = TestUtility.getDifferentNotification(initialRunnerList, newRunnerList);

        // Make sure that only one notification is produced
        assertEquals(1, differentCustomer.size());
        assertEquals(1, differentVendor.size());
        assertEquals(1, differentRunner.size());

        // Check if the notification description is correct
        String customerDescription = differentCustomer.getFirst().getNotificationDetails();
        assertEquals(
                "Runner " + order1.getRunnerInCharge().getUserID() + " is on his/her way to deliver your order " + order1.getOrderID() + ". Get ready to collect your meals in a while!",
                customerDescription
        );

        String vendorDescription = differentVendor.getFirst().getNotificationDetails();
        assertEquals(
                "The order " + order1.getOrderID() + " is picked up by runner " + order1.getRunnerInCharge().getUserID() + ".",
                vendorDescription
        );

        String runnerDescription = differentRunner.getFirst().getNotificationDetails();
        assertEquals(
                "You have picked up order " + order1.getOrderID() + ". Please proceed to deliver it to customer in a safely manner.",
                runnerDescription
        );

        // Initialize back the initial lists
        initialCustomerList.add(differentCustomer.getFirst());
        initialVendorList.add(differentVendor.getFirst());
        initialRunnerList.add(differentRunner.getFirst());

        // Change runner delivering to completed
        int orderComplete = order1.runnerUpdateOrderStatus(Order.OrderStatus.COMPLETED);

        // Check if the update is successful
        assertEquals(1, orderComplete);

        // Check if the status is updated correctly
        assertEquals(Order.OrderStatus.COMPLETED, order1.getOrderStatus());

        // Check if notifications are created
        differentCustomer = TestUtility.getDifferentNotification(
                initialCustomerList,
                TestUtility.convertToNotificationArray(
                        CustomerNotification.getCustomerNotificationList()
                )
        );

        differentVendor = TestUtility.getDifferentNotification(
                initialVendorList,
                TestUtility.convertToNotificationArray(
                        VendorNotification.getVendorNotificationList()
                )
        );

        differentRunner = TestUtility.getDifferentNotification(
                initialRunnerList,
                TestUtility.convertToNotificationArray(
                        DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
                )
        );

        // Make sure that only one notification is produced
        assertEquals(1, differentCustomer.size());
        assertEquals(1, differentVendor.size());
        assertEquals(1, differentRunner.size());

        // Check if notification details match
        assertEquals(
                "Your order " + order1.getOrderID() + " has been delivered. Enjoy your meal!",
                differentCustomer.getFirst().getNotificationDetails()
        );

        assertEquals(
                "You have successfully delivered the order " + order1.getOrderID() + ".",
                differentVendor.getFirst().getNotificationDetails()
        );

        assertEquals(
                "Runner " + order1.getRunnerInCharge().getUserID() + " has successfully delivered the order " + order1.getOrderID() + " to the customer.",
                differentRunner.getFirst().getNotificationDetails()
        );

        // Erroneous order - Order 2: Dine in - cannot use this function since delivery runner is not involved
        int errorOrder = order2.runnerUpdateOrderStatus(Order.OrderStatus.RUNNER_DELIVERY);
        assertEquals(0, errorOrder);
    }
}
