import backend.entity.Feedback;
import backend.entity.Item;
import backend.entity.Order;
import backend.entity.Stall;
import backend.file_io.ItemFileIO;
import backend.notification.CustomerNotification;
import backend.notification.DeliveryRunnerNotification;
import backend.notification.Notification;
import backend.notification.VendorNotification;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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
        assertEquals(Order.OrderStatus.CANCELLED, order1.getOrderStatus());

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

    /**
     * This test focuses on the operation where the customer changes the dining method due to insufficient delivery runners.
     */
    @Test
    void testCustomerChangeDiningMethod() {

        // Get the initial list of notifications
        ArrayList<Notification> initialCustomer = TestUtility.convertToNotificationArray(
                CustomerNotification.getCustomerNotificationList()
        );

        ArrayList<Notification> initialVendor = TestUtility.convertToNotificationArray(
                VendorNotification.getVendorNotificationList()
        );

        ArrayList<Notification> initialRunner = TestUtility.convertToNotificationArray(
                DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
        );

        // Prepare order 2 - used to cancel the order
        order2.setDiningType(Order.DiningType.DELIVERY);
        order2.setOrderStatus(Order.OrderStatus.PENDING_CHANGE);

        // Cancel the order for order 2
        boolean cancelOrder = order2.customerChangeDiningStatus(Order.DiningType.DELIVERY);

        // Make sure that the operation is successful
        assertTrue(cancelOrder);

        // Make sure that the information is changed correctly
        assertEquals(Order.OrderStatus.CANCELLED, order2.getOrderStatus());

        // Retrieve the newly created notification
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

        // Make sure that the relevant notifications are created
        assertEquals(1, differentCustomer.size());
        assertEquals(1, differentVendor.size());
        assertEquals(0, differentRunner.size());

        // Make sure that the description produced is correct
        assertEquals(
                "Your order " + order2.getOrderID() + " has been cancelled successfully. The order records can be found in the Order History page.",
                differentCustomer.getFirst().getNotificationDetails()
        );

        assertEquals(
                "Order " + order2.getOrderID() + " has been cancelled by the customer.",
                differentVendor.getFirst().getNotificationDetails()
        );

        // Initialize the initial notification list for another test
        initialCustomer.add(differentCustomer.getFirst());
        initialVendor.add(differentVendor.getFirst());

        // Prepare order 3 - used to change the dining method
        order3.setDiningType(Order.DiningType.DELIVERY);
        order3.setOrderStatus(Order.OrderStatus.PENDING_CHANGE);

        // Change the dining type to dine in (a pop-up should be displayed to ask user to key in table number)
        order3.setTableNumber("T003");
        boolean changeToDineIn = order3.customerChangeDiningStatus(Order.DiningType.DINE_IN);

        // Make sure that the operation is performed successfully
        assertTrue(changeToDineIn);

        // Make sure that the correct details are modified
        assertEquals(Order.DiningType.DINE_IN, order3.getDiningType());

        // Retrieve the newly created notification
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

        // Check if the correct number of notification is produced
        assertEquals(1, differentCustomer.size());
        assertEquals(1, differentVendor.size());
        assertEquals(0, differentRunner.size());

        // Make sure that the contents of the notification is correct
        assertEquals(
                "You have changed the dining method for order " + order3.getOrderID() + " to " + order3.getOrderStatus() + ". Please wait for the vendor to accept your order.",
                differentCustomer.getFirst().getNotificationDetails()
        );

        assertEquals(
                "The dining method for order " + order3.getOrderID() + " has been changed. Please return to the main page to re-accept the order if you wish to proceed with the order.",
                differentVendor.getFirst().getNotificationDetails()
        );
    }

    /**
     * This method focuses on whether the system would display the correct stalls when customer select stall categories.
     */
    @Test
    void testCustomerGetInformation() {

        // Part 1: Test if the stall can be retrieved based on category
        // Stall 1 is Chinese and Local. Trying to retrieve the stall
        ArrayList<Stall> stallList = Stall.filterStall(Stall.StallCategories.CHINESE);

        // Make sure that only one stall is retrieved
        assertEquals(1, stallList.size());

        // Make sure that the retrieved stall is stall 1
        assertEquals(stall1, stallList.getFirst());

        // Now try to retrieve a stall that is not relevant
        stallList = Stall.filterStall(Stall.StallCategories.HALAL);

        // Make sure that no store is retrieved
        assertEquals(0, stallList.size());

        // Part 2: Test if the items of a selected stall can be displayed
        // Item 1 is in stall 1. Try to retrieve the item
        ArrayList<Item> itemList = Item.getItemList(stall1);

        // Make sure that there is only one item retrieved
        assertEquals(1, itemList.size());

        // Make sure that the item retrieved is item 1
        assertEquals(item1, itemList.getFirst());

        // Part 3: Check if the ratings obtained are accurate (feedback 3: rating of 1.5)
        // Check if the correct feedback can be retrieved - feedback 3
        assertEquals(1, Feedback.getFeedbackList(stall1).size());
        assertEquals(feedback3, Feedback.getFeedbackList(stall1).getFirst());

        // Check if the ratings detail are correct
        assertEquals(1, stall1.getFeedbackCount());
        assertEquals(feedback3.getRatings(), stall1.getOverallRatings());
    }

    /**
     * This test focuses on the operation where the customer adds item into cart.
     */
    @Test
    void testCustomerAddingToCart() {

        // Get the initial cart and make sure that there is nothing inside
        Map<String, Integer> customerCart = customer1.getCart();
        assertEquals(0, customerCart.size());

        // Add an item into the cart
        boolean addToCart1 = customer1.addItemToCart(item1, 2);
        assertTrue(addToCart1);

        // Check if the correct item is added to cart
        Integer quantityOfItem = customer1.getCart().get(item1.getItemID());
        assertNotNull(quantityOfItem);
        assertEquals(2, quantityOfItem);

        // Create another item to be used for testing
        Item item2 = new Item(Item.generateItemID(), "Milk tea", stall1, 7.00, "Healthy milk tea for children");
        Item.addItemToList(item2);
        ItemFileIO.writeFile();

        // Add the item to cart
        boolean addToCart2 = customer1.addItemToCart(item2, 5);
        assertTrue(addToCart2);

        // Check if the items in cart are correct
        assertEquals(2, customer1.getCart().size());
        assertEquals(2, customer1.getCart().get(item1.getItemID()));
        assertEquals(5, customer1.getCart().get(item2.getItemID()));

        // Add item 1 again
        boolean addToCart3 = customer1.addItemToCart(item1, 10);
        assertTrue(addToCart3);

        // Check if the items in cart are correct
        assertEquals(2, customer1.getCart().size());
        assertEquals(12, customer1.getCart().get(item1.getItemID()));
        assertEquals(5, customer1.getCart().get(item2.getItemID()));
    }

    /**
     * This test focuses on the operation where the customer removes an item from the cart
     */
    @Test
    void testCustomerRemovingFromCart() {

        // Create an additional item to be used for testing
        Item item2 = new Item(Item.generateItemID(), "Milk tea", stall1, 7.00, "Healthy milk tea for children");
        Item.addItemToList(item2);
        ItemFileIO.writeFile();

        // Initialize the cart
        Map<String, Integer> initialCart = new HashMap<>();
        initialCart.put(item1.getItemID(), 3);
        initialCart.put(item2.getItemID(), 2);
        customer1.setCart(initialCart);

        // Customer remove the additional item from the cart
        boolean removeFromCart = customer1.removeItemFromCart(item2);
        assertTrue(removeFromCart);

        // Check if the cart contents are correct
        assertEquals(1, customer1.getCart().size());
        assertEquals(3, customer1.getCart().get(item1.getItemID()));
        assertNull(customer1.getCart().get(item2.getItemID()));
    }
}
