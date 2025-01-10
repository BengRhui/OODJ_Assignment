import backend.entity.DeliveryRunner;
import backend.entity.Order;
import backend.notification.CustomerNotification;
import backend.notification.DeliveryRunnerNotification;
import backend.notification.Notification;
import backend.notification.VendorNotification;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class {@code RunnerTest} focuses on the operations that a delivery runner would perform.
 *
 * @author Beng Rhui (TP068495)
 */
public class RunnerTest extends BaseTest {

    /**
     * This test focuses on the operation where the delivery runner accepts the order.
     */
    @Test
    void runnerAcceptOrder() {

        // Get the initial list of notifications
        ArrayList<Notification> initialCustomerNotification = TestUtility.convertToNotificationArray(
                CustomerNotification.getCustomerNotificationList()
        );

        ArrayList<Notification> initialVendorNotification = TestUtility.convertToNotificationArray(
                VendorNotification.getVendorNotificationList()
        );

        ArrayList<Notification> initialRunnerNotification = TestUtility.convertToNotificationArray(
                DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
        );

        // Initialize order 1 (delivery)
        order1.setOrderStatus(Order.OrderStatus.WAITING_VENDOR_AND_RUNNER);

        // Let runner accept the order
        boolean orderBothWaitingAccepted = order1.runnerAcceptOrder();

        // Make sure that the operation is successful
        assertTrue(orderBothWaitingAccepted);

        // Make sure that the status is updated after accepting order
        assertEquals(Order.OrderStatus.WAITING_VENDOR, order1.getOrderStatus());

        // Get the new notifications generated
        ArrayList<Notification> differentCustomer = TestUtility.getDifferentNotification(
                initialCustomerNotification,
                TestUtility.convertToNotificationArray(
                        CustomerNotification.getCustomerNotificationList()
                )
        );

        ArrayList<Notification> differentVendor = TestUtility.getDifferentNotification(
                initialVendorNotification,
                TestUtility.convertToNotificationArray(
                        VendorNotification.getVendorNotificationList()
                )
        );

        ArrayList<Notification> differentRunner = TestUtility.getDifferentNotification(
                initialRunnerNotification,
                TestUtility.convertToNotificationArray(
                        DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
                )
        );

        // Make sure that notification is generated after accepting order
        assertEquals(1, differentCustomer.size());
        assertEquals(0, differentVendor.size());
        assertEquals(1, differentRunner.size());

        // Make sure that the notification contents are accurate
        assertEquals(
                "Your order " + order1.getOrderID() + " is accepted by runner " + order1.getRunnerInCharge().getName() + ". Please give us a moment for vendors to accept this order.",
                differentCustomer.getFirst().getNotificationDetails()
        );

        assertEquals(
                "You have accepted the order " + order1.getOrderID() + ". Please wait for the confirmation from the vendor side (" + order1.getOrderedStall().getStallID() + ").",
                differentRunner.getFirst().getNotificationDetails()
        );

        // Initialize the initial list
        initialCustomerNotification.add(differentCustomer.getFirst());
        initialRunnerNotification.add(differentRunner.getFirst());

        // Set order 1 to "waiting runner" status
        order1.setOrderStatus(Order.OrderStatus.WAITING_RUNNER);

        // Test runner accepting order for order 1
        boolean orderWaitingRunnerAccepted = order1.runnerAcceptOrder();
        assertTrue(orderWaitingRunnerAccepted);

        // Make sure that the order status is correct
        assertEquals(Order.OrderStatus.VENDOR_PREPARING, order1.getOrderStatus());

        // Get the new notifications
        differentCustomer = TestUtility.getDifferentNotification(
                initialCustomerNotification,
                TestUtility.convertToNotificationArray(
                        CustomerNotification.getCustomerNotificationList()
                )
        );

        differentVendor = TestUtility.getDifferentNotification(
                initialVendorNotification,
                TestUtility.convertToNotificationArray(
                        VendorNotification.getVendorNotificationList()
                )
        );

        differentRunner = TestUtility.getDifferentNotification(
                initialRunnerNotification,
                TestUtility.convertToNotificationArray(
                        DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
                )
        );

        // Make sure that the notifications are created
        assertEquals(1, differentCustomer.size());
        assertEquals(1, differentVendor.size());
        assertEquals(1, differentRunner.size());

        // Make sure that the notification description is correct
        assertEquals(
                "Your order " + order1.getOrderID() + " has been accepted by runner " + order1.getRunnerInCharge().getUserID() + ". The vendor will now start to prepare your order.",
                differentCustomer.getFirst().getNotificationDetails()
        );

        assertEquals(
                "Order " + order1.getOrderID() + " has been assigned to runner " + order1.getRunnerInCharge().getUserID() + ". You may start preparing the food.",
                differentVendor.getFirst().getNotificationDetails()
        );

        assertEquals(
                "You have accepted the order " + order1.getOrderID() + ". Please head to the stall " + order1.getOrderedStall().getStallID() + " to pick-up the order.",
                differentRunner.getFirst().getNotificationDetails()
        );

        // Erroneous order: The order should not use this method
        boolean erroneousOrder = order2.runnerAcceptOrder();
        assertFalse(erroneousOrder);
    }

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

    /**
     * This test focuses on the operation where the runner declines an order.
     */
    @Test
    void runnerDeclineOrder() {

        // Create new runner for testing purposes
        DeliveryRunner runner2 = new DeliveryRunner(
                "R002",
                "ratio_veritas@starrail.com",
                "ratio123",
                "Dr Veritas Ratio",
                "012-9361842"
        );
        DeliveryRunner.addToRunnerList(runner2);
        DeliveryRunner.initializeAvailabilityList();

        // Get initial notification lists
        ArrayList<Notification> initialCustomer = TestUtility.convertToNotificationArray(
                CustomerNotification.getCustomerNotificationList()
        );

        ArrayList<Notification> initialRunner = TestUtility.convertToNotificationArray(
                DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
        );

        // Set status of order 1 (delivery) to waiting for runner
        order1.setOrderStatus(Order.OrderStatus.WAITING_RUNNER);

        // Reject order 1
        boolean rejectOrderFirstTime = order1.runnerRejectOrder();

        // Check if the operation is successful
        assertTrue(rejectOrderFirstTime);

        // Check if the status remains
        assertEquals(Order.OrderStatus.WAITING_RUNNER, order1.getOrderStatus());

        // Check if order 1 is assigned to another runner (runner2 in this case)
        assertEquals(runner2, order1.getRunnerInCharge());

        // Get the created notification list
        ArrayList<Notification> differentCustomer = TestUtility.getDifferentNotification(
                initialCustomer,
                TestUtility.convertToNotificationArray(
                        CustomerNotification.getCustomerNotificationList()
                )
        );

        ArrayList<Notification> differentRunner = TestUtility.getDifferentNotification(
                initialRunner,
                TestUtility.convertToNotificationArray(
                        DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
                )
        );

        // Check if notification is produced for runner, and if notification remains for customer
        assertEquals(0, differentCustomer.size());
        assertEquals(1, differentRunner.size());

        // Check if the notification description tallies
        assertEquals(
                "You have declined the order " + order1.getOrderID() + ". The order is now passed to another runner.",
                differentRunner.getFirst().getNotificationDetails()
        );

        // Reject order 1 again
        boolean rejectOrderSecondTime = order1.runnerRejectOrder();

        // Make sure that the operation is successful
        assertTrue(rejectOrderSecondTime);

        // Check if the order is changed to "pending change" (no runners are available anymore)
        assertEquals(Order.OrderStatus.PENDING_CHANGE, order1.getOrderStatus());

        // Initiate notification list
        initialRunner.add(differentRunner.getFirst());

        // Get the new notification
        differentCustomer = TestUtility.getDifferentNotification(
                initialCustomer,
                TestUtility.convertToNotificationArray(
                        CustomerNotification.getCustomerNotificationList()
                )
        );

        differentRunner = TestUtility.getDifferentNotification(
                initialRunner,
                TestUtility.convertToNotificationArray(
                        DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
                )
        );

        // Check if one notification is produced for each notification type
        assertEquals(1, differentCustomer.size());
        assertEquals(1, differentRunner.size());

        // Check if the notification description is correct
        assertEquals(
                "We regret to inform that your order " + order1.getOrderID() + " cannot be delivered as no runners were available to deliver it. " +
                        "Please change your dining method or cancel your order and try again later.",
                differentCustomer.getFirst().getNotificationDetails()
        );

        assertEquals(
                "You have declined the order " + order1.getOrderID() + ".",
                differentRunner.getFirst().getNotificationDetails()
        );

        // Erroneous order: the method should not be used here
        boolean errorOrder = order2.runnerRejectOrder();
        assertFalse(errorOrder);
    }
}
