import backend.entity.*;
import backend.file_io.DeliveryRunnerFileIO;
import backend.file_io.ItemFileIO;
import backend.notification.CustomerNotification;
import backend.notification.DeliveryRunnerNotification;
import backend.notification.Notification;
import backend.notification.VendorNotification;
import backend.utility.Utility;
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

        // Check if the price is calculated correctly
        assertEquals(
                item1.getPrice(2) + item2.getPrice(5),
                Utility.getTotalAmountForCart(customer1.getCart())
        );

        // Add item 1 again
        boolean addToCart3 = customer1.addItemToCart(item1, 10);
        assertTrue(addToCart3);

        // Check if the items in cart are correct
        assertEquals(2, customer1.getCart().size());
        assertEquals(12, customer1.getCart().get(item1.getItemID()));
        assertEquals(5, customer1.getCart().get(item2.getItemID()));

        // Check if the price is calculated correctly
        assertEquals(
                item1.getPrice(12) + item2.getPrice(5),
                Utility.getTotalAmountForCart(customer1.getCart())
        );
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

        // Check if the price is calculated correctly
        assertEquals(
                item1.getPrice(3),
                Utility.getTotalAmountForCart(customer1.getCart())
        );
    }

    /**
     * This test focuses on the operation where customer changes the dining methods in cart.
     */
    @Test
    void testCustomerChangingDiningMethodsInCart() {

        // Initialize cart - does not contain delivery fees (default is dine-in)
        Map<String, Integer> cart = new HashMap<>();
        cart.put(item1.getItemID(), 3);
        customer1.setCart(cart);

        // Change to takeaway
        boolean changeToTakeaway = customer1.setDiningMethodInCart(Order.DiningType.TAKEAWAY);
        assertTrue(changeToTakeaway);

        // Make sure that the content does not change
        assertEquals(1, customer1.getCart().size());
        assertEquals(3, customer1.getCart().get(item1.getItemID()));

        // Change to delivery
        boolean changeToDelivery = customer1.setDiningMethodInCart(Order.DiningType.DELIVERY);
        assertTrue(changeToDelivery);

        // Make sure that delivery fees is added
        assertEquals(2, customer1.getCart().size());
        assertEquals(3, customer1.getCart().get(item1.getItemID()));
        assertNotNull(customer1.getCart().get(Item.deliveryFees.getItemID()));

        // Change back to dine-in
        boolean changeToDineIn = customer1.setDiningMethodInCart(Order.DiningType.DINE_IN);
        assertTrue(changeToDineIn);

        // Make sure that delivery fees is removed
        assertEquals(1, customer1.getCart().size());
        assertEquals(3, customer1.getCart().get(item1.getItemID()));
    }

    /**
     * This test focuses on the operation where the customer places order from the cart.
     */
    @Test
    void testCustomerPlaceOrder() {

        // Get initial list of orders and transactions
        ArrayList<Order> initialOrderList = new ArrayList<>(Order.getOrderList());
        ArrayList<Transaction> initialTransactionList = new ArrayList<>(Transaction.getTransactionList());

        // Get initial list of notifications
        ArrayList<Notification> initialCustomerNotification = TestUtility.convertToNotificationArray(
                CustomerNotification.getCustomerNotificationList()
        );

        ArrayList<Notification> initialVendorNotification = TestUtility.convertToNotificationArray(
                VendorNotification.getVendorNotificationList()
        );

        ArrayList<Notification> initialRunnerNotification = TestUtility.convertToNotificationArray(
                DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
        );

        // Initialize carts
        Map<String, Integer> cart = new HashMap<>();
        cart.put(item1.getItemID(), 3);
        customer1.setCart(cart);

        // Get the initial e-wallet amount
        double initialWalletAmount = customer1.getEWalletAmount();
        double cartAmount = Utility.getTotalAmountForCart(customer1.getCart());

        // Place order - dine-in
        boolean placeOrderOne = customer1.placeOrder(
                item1.getStall(),
                cart,
                Order.DiningType.DINE_IN,
                "Anything would do",
                "T031"
        );
        assertTrue(placeOrderOne);

        // Retrieve the order associated
        ArrayList<Order> differentOrder = new ArrayList<>(Order.getOrderList());
        differentOrder.removeAll(initialOrderList);

        // Make sure that only one order is created
        assertEquals(1, differentOrder.size());

        // Make sure the items in the added order is correct
        assertEquals(Order.DiningType.DINE_IN, differentOrder.getFirst().getDiningType());
        assertEquals("T031", differentOrder.getFirst().getTableNumber());
        assertEquals(3, differentOrder.getFirst().getOrderItem().get(item1));

        // Make sure that the cart is cleared after order is placed
        assertTrue(customer1.getCart().isEmpty());

        // Retrieve the newly created notifications
        ArrayList<Notification> differentCustomerNotification = TestUtility.getDifferentNotification(
                initialCustomerNotification,
                TestUtility.convertToNotificationArray(
                        CustomerNotification.getCustomerNotificationList()
                )
        );

        ArrayList<Notification> differentVendorNotification = TestUtility.getDifferentNotification(
                initialVendorNotification,
                TestUtility.convertToNotificationArray(
                        VendorNotification.getVendorNotificationList()
                )
        );

        ArrayList<Notification> differentRunnerNotification = TestUtility.getDifferentNotification(
                initialRunnerNotification,
                TestUtility.convertToNotificationArray(
                        DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
                )
        );

        // Check if notifications were created
        assertEquals(1, differentCustomerNotification.size());
        assertEquals(1, differentVendorNotification.size());
        assertEquals(0, differentRunnerNotification.size());

        // Check if notification descriptions correct
        assertEquals(
                "Your order " + differentOrder.getFirst().getOrderID() + " has been created successfully. " +
                        "An amount of RM" + String.format("%.2f", cartAmount) + " is deducted from your wallet. " +
                        "Please wait for the vendor and runner (if applicable) to accept your order.",
                differentCustomerNotification.getFirst().getNotificationDetails()
        );

        assertEquals(
                "A new order with ID " + differentOrder.getFirst().getOrderID() + " is available. You may return to the main menu to check the details.",
                differentVendorNotification.getFirst().getNotificationDetails()
        );

        // Check if the e-wallet amount is updated
        assertEquals(
                initialWalletAmount - cartAmount,
                customer1.getEWalletAmount()
        );

        // Retrieve the newly created transaction
        ArrayList<Transaction> differentTransaction = new ArrayList<>(Transaction.getTransactionList());
        differentTransaction.removeAll(initialTransactionList);

        // Check if transaction list is updated
        assertEquals(1, differentTransaction.size());

        // Check if the transaction details are correct
        assertEquals(Transaction.TransactionType.CASH_OUT, differentTransaction.getFirst().getTransactionType());
        assertEquals(Transaction.PaymentMethod.E_WALLET, differentTransaction.getFirst().getPaymentMethod());

        // Initialize carts
        cart.put(item1.getItemID(), 2);
        customer1.setCart(cart);

        // Initialize order, notification and transaction lists
        initialOrderList = new ArrayList<>(Order.getOrderList());
        initialCustomerNotification.add(differentCustomerNotification.getFirst());
        initialVendorNotification.add(differentVendorNotification.getFirst());
        initialTransactionList.add(differentTransaction.getFirst());

        // Get the price of the order
        initialWalletAmount = customer1.getEWalletAmount();
        cartAmount = Utility.getTotalAmountForCart(customer1.getCart());

        // Place order - delivery
        boolean placeOrderTwo = customer1.placeOrder(
                stall1,
                cart,
                Order.DiningType.DELIVERY,
                "No spicy please",
                null
        );
        assertTrue(placeOrderTwo);

        // Retrieve the newly created order
        differentOrder = new ArrayList<>(Order.getOrderList());
        differentOrder.removeAll(initialOrderList);

        // Check if a new order is created
        assertEquals(1, differentOrder.size());

        // Check if the order details are correct
        assertEquals(Order.DiningType.DELIVERY, differentOrder.getFirst().getDiningType());
        assertEquals(runner1.getUserID(), differentOrder.getFirst().getRunnerInCharge().getUserID());
        assertEquals(2, differentOrder.getFirst().getOrderItem().get(item1));

        // Get the newly created notifications
        differentCustomerNotification = TestUtility.getDifferentNotification(
                initialCustomerNotification,
                TestUtility.convertToNotificationArray(
                        CustomerNotification.getCustomerNotificationList()
                )
        );

        differentVendorNotification = TestUtility.getDifferentNotification(
                initialVendorNotification,
                TestUtility.convertToNotificationArray(
                        VendorNotification.getVendorNotificationList()
                )
        );

        differentRunnerNotification = TestUtility.getDifferentNotification(
                initialRunnerNotification,
                TestUtility.convertToNotificationArray(
                        DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
                )
        );

        // Make sure that the notifications are created
        assertEquals(1, differentCustomerNotification.size());
        assertEquals(1, differentVendorNotification.size());
        assertEquals(1, differentRunnerNotification.size());

        // Make sure that the description tallies
        assertEquals(
                "Your order " + differentOrder.getFirst().getOrderID() + " has been created successfully. " +
                        "An amount of RM" + String.format("%.2f", cartAmount) + " is deducted from your wallet. " +
                        "Please wait for the vendor and runner (if applicable) to accept your order.",
                differentCustomerNotification.getFirst().getNotificationDetails()
        );

        assertEquals(
                "A new order with ID " + differentOrder.getFirst().getOrderID() + " is available. You may return to the main menu to check the details.",
                differentVendorNotification.getFirst().getNotificationDetails()
        );

        assertEquals(
                "A new order with ID " + differentOrder.getFirst().getOrderID() + " is available. You may return to the main menu to check the details.",
                differentRunnerNotification.getFirst().getNotificationDetails()
        );

        // Make sure that the e-wallet amount is updated
        assertEquals(
                initialWalletAmount - cartAmount,
                customer1.getEWalletAmount()
        );

        // Retrieve the new transaction history
        differentTransaction = new ArrayList<>(Transaction.getTransactionList());
        differentTransaction.removeAll(initialTransactionList);

        // Check if transaction list is updated
        assertEquals(1, differentTransaction.size());

        // Check if the transaction details are correct
        assertEquals(Transaction.TransactionType.CASH_OUT, differentTransaction.getFirst().getTransactionType());
        assertEquals(Transaction.PaymentMethod.E_WALLET, differentTransaction.getFirst().getPaymentMethod());

        // Erroneous order - empty cart
        boolean errorOrder = customer1.placeOrder(
                stall1,
                customer1.getCart(),
                Order.DiningType.DELIVERY,
                "Hello",
                null
        );
        assertFalse(errorOrder);
    }
}
