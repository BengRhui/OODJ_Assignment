import backend.entity.Order;
import backend.notification.CustomerNotification;
import backend.notification.DeliveryRunnerNotification;
import backend.notification.Notification;
import backend.notification.VendorNotification;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test class {@code VendorTest} contains the tests covering the operations involving vendors.
 *
 * @author Beng Rhui (TP068495)
 */
public class VendorTest extends BaseTest {

    /**
     * Test focusing on accepting order from customer
     */
    @Test
    void testVendorAcceptOrder() {

        // Get initial notification lists
        ArrayList<Notification> initialCustomerNotification = TestUtility.convertToNotificationArray(
                CustomerNotification.getCustomerNotificationList()
        );

        ArrayList<Notification> initialVendorNotification = TestUtility.convertToNotificationArray(
                VendorNotification.getVendorNotificationList()
        );

        ArrayList<Notification> initialDeliveryRunnerNotification = TestUtility.convertToNotificationArray(
                DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
        );

        // Order 1: Delivery - start from waiting vendor and runner status
        order1.setOrderStatus(Order.OrderStatus.WAITING_VENDOR_AND_RUNNER);
        testCaseForAcceptingOrder(
                order1,
                initialCustomerNotification,
                initialVendorNotification,
                initialDeliveryRunnerNotification
        );

        // Order 2: Dine In - start from waiting vendor status
        order2.setOrderStatus(Order.OrderStatus.WAITING_VENDOR);
        testCaseForAcceptingOrder(
                order2,
                initialCustomerNotification,
                initialVendorNotification,
                initialDeliveryRunnerNotification
        );

        // Order 3: Takeaway - start from waiting vendor status
        order3.setOrderStatus(Order.OrderStatus.WAITING_VENDOR);
        testCaseForAcceptingOrder(
                order3,
                initialCustomerNotification,
                initialVendorNotification,
                initialDeliveryRunnerNotification
        );
    }

    /**
     * A private method aims to reduce code complexity for test case {@code testVendorAcceptOrder}
     *
     * @param order               The order object associated
     * @param initialCustomerList The initial customer list before order is placed
     * @param initialVendorList   The initial vendor list before order is placed
     * @param initialRunnerList   The initial delivery runner list before order is placed
     */
    private void testCaseForAcceptingOrder(
            Order order,
            ArrayList<Notification> initialCustomerList,
            ArrayList<Notification> initialVendorList,
            ArrayList<Notification> initialRunnerList
    ) {

        // Accept the order
        int orderTesting = order.acceptOrder();

        // Check whether order is successfully updated
        assertEquals(1, orderTesting);

        // Check whether order status is successfully modified
        switch (order.getOrderStatus()) {
            case WAITING_VENDOR_AND_RUNNER -> assertEquals(Order.OrderStatus.WAITING_RUNNER, order.getOrderStatus());
            case WAITING_VENDOR -> assertEquals(Order.OrderStatus.VENDOR_PREPARING, order.getOrderStatus());
        }

        // Get the newly included notification
        ArrayList<Notification> differentCustomerNotification = TestUtility.getDifferent(
                initialCustomerList,
                TestUtility.convertToNotificationArray(CustomerNotification.getCustomerNotificationList())
        );

        ArrayList<Notification> differentVendorNotification = TestUtility.getDifferent(
                initialVendorList,
                TestUtility.convertToNotificationArray(VendorNotification.getVendorNotificationList())
        );

        ArrayList<Notification> differentRunnerNotification = TestUtility.getDifferent(
                initialRunnerList,
                TestUtility.convertToNotificationArray(DeliveryRunnerNotification.getDeliveryRunnerNotificationList())
        );

        // Make sure that a new customer notification is created
        assertEquals(1, differentCustomerNotification.size());

        // Make sure that a new vendor notification is created
        assertEquals(1, differentVendorNotification.size());

        // Make sure that a new runner notification is created, except for 1 (waiting vendor and runner) that does not have a runner
        switch (order.getOrderStatus()) {
            case WAITING_VENDOR_AND_RUNNER -> assertEquals(0, differentRunnerNotification.size());
            case WAITING_VENDOR -> assertEquals(1, differentRunnerNotification.size());
        }

        // Make sure that the correct notification is produced
        switch (order.getOrderStatus()) {
            case WAITING_VENDOR_AND_RUNNER -> {

                // Customer notification
                assertEquals(
                        "Your order " + order.getOrderID() + " has been accepted by vendor. We're currently assigning a delivery runner to pick it up.",
                        differentCustomerNotification.getFirst().getNotificationDetails()
                );

                // Vendor notification
                assertEquals(
                        "The order " + order.getOrderID() + " has been accepted. A delivery runner will be assigned shortly to pick it up.",
                        differentVendorNotification.getFirst().getNotificationDetails()
                );
            }

            case WAITING_VENDOR -> {

                // Customer
                assertEquals("Your order " + order.getOrderID() + " is now being prepared by our vendor.",
                        differentCustomerNotification.getFirst().getNotificationDetails()
                );

                // Vendor
                assertEquals("Order " + order.getOrderID() + " has been updated to preparing.",
                        differentVendorNotification.getLast().getNotificationDetails()
                );

                // Runner notification
                assertEquals(
                        "The order " + order.getOrderID() + " is now being prepared. Please be ready to pick up at stall " + order.getOrderedStall().getStallID() + ".",
                        differentRunnerNotification.getFirst().getNotificationDetails()
                );
            }
        }

        // Reset environment
        clearArray();
        cleanEnvironment();
        setUpArray();
    }
}
