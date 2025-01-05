import backend.entity.Order;
import backend.notification.CustomerNotification;
import backend.notification.DeliveryRunnerNotification;
import backend.notification.Notification;
import backend.notification.VendorNotification;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static backend.entity.Order.OrderStatus.CANCELLED;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class {@code CustomerTest} focuses on the operations performed by customers in real-life when using the system.
 *
 * @author Beng Rhui (TP068495)
 */
public class CustomerTest extends BaseTest {

    /**
     * This test focuses on the operation where the customer cancels an order.
     */
    @Test
    void testCustomerCancelOrder() {

        // Retrieve initial notification lists
        ArrayList<Notification> initialCustomer = TestUtility.convertToNotificationArray(
                CustomerNotification.getCustomerNotificationList()
        );

        ArrayList<Notification> initialVendor = TestUtility.convertToNotificationArray(
                VendorNotification.getVendorNotificationList()
        );

        ArrayList<Notification> initialRunner = TestUtility.convertToNotificationArray(
                DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
        );

        // Set up order 1 for testing purpose (runner involved)
        order1.setOrderStatus(Order.OrderStatus.WAITING_VENDOR);

        // Attempt to cancel the order
        boolean order1Cancelled = order1.customerCancelOrder();

        // Check if the order is cancelled successfully
        assertTrue(order1Cancelled);

        // Check if the status of the order is changed to "cancelled"
        assertEquals(CANCELLED, order1.getOrderStatus());

        // Retrieve the newly created notifications
        ArrayList<Notification> differentCustomer = TestUtility.getDifferentNotification(
                initialCustomer,
                TestUtility.convertToNotificationArray(
                        CustomerNotification.getCustomerNotificationList()
                )
        );

        ArrayList<Notification> differentVendor = TestUtility.getDifferentNotification(
                initialVendor,
                TestUtility.convertToNotificationArray(
                        VendorNotification.getVendorNotificationList()
                )
        );

        ArrayList<Notification> differentRunner = TestUtility.getDifferentNotification(
                initialRunner,
                TestUtility.convertToNotificationArray(
                        DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
                )
        );

        // Check if only one notification is created for each category
        assertEquals(1, differentCustomer.size());
        assertEquals(1, differentVendor.size());
        assertEquals(1, differentRunner.size());

        // Check if the contents of the notification is correct
        assertEquals(
                "Your order " + order1.getOrderID() + " has been cancelled successfully. The order records can be found in the Order History page.",
                differentCustomer.getFirst().getNotificationDetails()
        );

        assertEquals(
                "Order " + order1.getOrderID() + " has been cancelled by the customer.",
                differentVendor.getFirst().getNotificationDetails()
        );

        assertEquals(
                "The customer has cancelled the order " + order1.getOrderID() + ".",
                differentRunner.getFirst().getNotificationDetails()
        );

        // Initialize the notification list for another testing
        initialCustomer.add(differentCustomer.getFirst());
        initialVendor.add(differentVendor.getFirst());
        initialRunner.add(differentRunner.getFirst());

        // Set up order 2 for testing purpose (runner not involved)
        order2.setOrderStatus(Order.OrderStatus.WAITING_VENDOR);

        // Attempt to cancel the order
        boolean order2Cancelled = order2.customerCancelOrder();

        // Check if the order is cancelled successfully
        assertTrue(order2Cancelled);

        // Check if the status of the order is changed to "cancelled"
        assertEquals(Order.OrderStatus.CANCELLED, order2.getOrderStatus());

        // Retrieve the newly created notifications
        differentCustomer = TestUtility.getDifferentNotification(
                initialCustomer,
                TestUtility.convertToNotificationArray(
                        CustomerNotification.getCustomerNotificationList()
                )
        );

        differentVendor = TestUtility.getDifferentNotification(
                initialVendor,
                TestUtility.convertToNotificationArray(
                        VendorNotification.getVendorNotificationList()
                )
        );

        differentRunner = TestUtility.getDifferentNotification(
                initialRunner,
                TestUtility.convertToNotificationArray(
                        DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
                )
        );

        // Check if only one notification is created for each category (except runner who is not involved)
        assertEquals(1, differentCustomer.size());
        assertEquals(1, differentVendor.size());
        assertEquals(0, differentRunner.size());

        // Check if the contents of the notification is correct
        assertEquals(
                "Your order " + order2.getOrderID() + " has been cancelled successfully. The order records can be found in the Order History page.",
                differentCustomer.getFirst().getNotificationDetails()
        );

        assertEquals(
                "Order " + order2.getOrderID() + " has been cancelled by the customer.",
                differentVendor.getFirst().getNotificationDetails()
        );

        // Erroneous order 3 (status does not match)
        boolean errorOrder = order3.customerCancelOrder();
        assertFalse(errorOrder);
    }
}
