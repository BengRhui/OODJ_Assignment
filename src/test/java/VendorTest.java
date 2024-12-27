import backend.entity.Item;
import backend.entity.Order;
import backend.file_io.PictureIO;
import backend.notification.CustomerNotification;
import backend.notification.DeliveryRunnerNotification;
import backend.notification.Notification;
import backend.notification.VendorNotification;
import backend.utility.Utility;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class {@code VendorTest} contains the tests covering the operations involving vendors.
 *
 * @author Beng Rhui (TP068495)
 */
public class VendorTest extends BaseTest {

    /**
     * A helper method to help reset the testing environment after every case is run
     */
    private void resetTestingEnvironment() {
        clearArray();
        cleanEnvironment();
        setUpArray();
    }

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

        // Erroneous order: Order was not in waiting status (even though this should not happen)
        try {
            order1.setOrderStatus(Order.OrderStatus.RUNNER_DELIVERY);
            testCaseForAcceptingOrder(
                    order1,
                    initialCustomerNotification,
                    initialVendorNotification,
                    initialDeliveryRunnerNotification
            );

            // Fail the test if the order passes the test case
            fail("The order should return 0 when the wrong order is passed into the test.");

        } catch (AssertionError e) {
            assert true;
        }
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
        int orderTesting = order.vendorAcceptOrder();

        // Check whether order is successfully updated
        assertEquals(1, orderTesting);

        // Check whether order status is successfully modified
        switch (order.getOrderStatus()) {
            case WAITING_VENDOR_AND_RUNNER -> assertEquals(Order.OrderStatus.WAITING_RUNNER, order.getOrderStatus());
            case WAITING_VENDOR -> assertEquals(Order.OrderStatus.VENDOR_PREPARING, order.getOrderStatus());
        }

        // Get the newly included notification
        ArrayList<Notification> differentCustomerNotification = TestUtility.getDifferentNotification(
                initialCustomerList,
                TestUtility.convertToNotificationArray(CustomerNotification.getCustomerNotificationList())
        );

        ArrayList<Notification> differentVendorNotification = TestUtility.getDifferentNotification(
                initialVendorList,
                TestUtility.convertToNotificationArray(VendorNotification.getVendorNotificationList())
        );

        ArrayList<Notification> differentRunnerNotification = TestUtility.getDifferentNotification(
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
        resetTestingEnvironment();
    }

    /**
     * This test focuses on the case where a vendor rejects an order.
     */
    @Test
    void testVendorRejectOrder() {

        // Initialise the initial lists
        ArrayList<Notification> initialCustomerNotification = TestUtility.convertToNotificationArray(
                CustomerNotification.getCustomerNotificationList()
        );

        ArrayList<Notification> initialVendorNotification = TestUtility.convertToNotificationArray(
                VendorNotification.getVendorNotificationList()
        );

        ArrayList<Notification> initialRunnerNotification = TestUtility.convertToNotificationArray(
                DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
        );

        // Order 1 - Delivery
        order1.setOrderStatus(Order.OrderStatus.WAITING_VENDOR_AND_RUNNER);
        testCaseForRejectingOrder(
                order1,
                initialCustomerNotification,
                initialVendorNotification,
                initialRunnerNotification
        );

        // Order 2 - Dine in
        order2.setOrderStatus(Order.OrderStatus.WAITING_VENDOR);
        testCaseForRejectingOrder(
                order2,
                initialCustomerNotification,
                initialVendorNotification,
                initialRunnerNotification
        );

        // Order 3 - Takeaway
        order3.setOrderStatus(Order.OrderStatus.WAITING_VENDOR);
        testCaseForRejectingOrder(
                order3,
                initialCustomerNotification,
                initialVendorNotification,
                initialRunnerNotification
        );

        // Erroneous order:
        try {

            // Create an erroneous order where the status is not correct (even though this should not happen)
            order1.setOrderStatus(Order.OrderStatus.VENDOR_PREPARING);
            testCaseForRejectingOrder(
                    order1,
                    initialCustomerNotification,
                    initialVendorNotification,
                    initialRunnerNotification
            );

        } catch (AssertionError e) {

            // Test is passed if an error is thrown
            assert true;
        }
    }

    /**
     * A method to simplify the test case {@code testVendorRejectOrder}
     *
     * @param order               The order to be rejected
     * @param initialCustomerList The initial list of customer notifications before order is cancelled
     * @param initialVendorList   The initial list of vendor notifications before order is cancelled
     * @param initialRunnerList   The initial list of delivery runner notifications before order is cancelled
     */
    private void testCaseForRejectingOrder(
            Order order,
            ArrayList<Notification> initialCustomerList,
            ArrayList<Notification> initialVendorList,
            ArrayList<Notification> initialRunnerList
    ) {

        // Order is declined
        int declineOrder = order.vendorCancelOrder();

        // Make sure that the order is declined successfully
        assertEquals(1, declineOrder);

        // Check if the status of the order has changed correctly
        assertEquals(Order.OrderStatus.CANCELLED, order.getOrderStatus());

        // Check if customer notification is created
        ArrayList<Notification> differentCustomerNotification = TestUtility.getDifferentNotification(
                initialCustomerList,
                TestUtility.convertToNotificationArray(
                        CustomerNotification.getCustomerNotificationList()
                )
        );
        assertEquals(1, differentCustomerNotification.size());

        // Check if content of customer notification is correct
        assertEquals(
                "We're sorry to inform that your order " + order.getOrderID() + " has been rejected by the vendor. Please consider placing a new order.",
                differentCustomerNotification.getFirst().getNotificationDetails()
        );

        // Check if vendor notification is created
        ArrayList<Notification> differentVendorNotification = TestUtility.getDifferentNotification(
                initialVendorList,
                TestUtility.convertToNotificationArray(
                        VendorNotification.getVendorNotificationList()
                )
        );
        assertEquals(1, differentVendorNotification.size());

        // Check if content is correct
        assertEquals(
                "You have rejected the order " + order.getOrderID() + ".",
                differentVendorNotification.getFirst().getNotificationDetails()
        );

        // If runner is involved
        if (order.getRunnerInCharge() != null) {

            // Check if runner notification is created successfully
            ArrayList<Notification> differentRunnerNotification = TestUtility.getDifferentNotification(
                    initialRunnerList,
                    TestUtility.convertToNotificationArray(
                            DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
                    )
            );
            assertEquals(1, differentRunnerNotification.size());

            // Check if content is correct
            assertEquals("The order " + order.getOrderID() + " has been cancelled due to rejection of the vendor.",
                    differentRunnerNotification.getFirst().getNotificationDetails()
            );
        }

        // Reset environment
        resetTestingEnvironment();
    }

    /**
     * This test focuses on the case where the vendor updates the status of the order that is being accepted.
     */
    @Test
    void testVendorUpdateOrderStatus() {

        // Obtain the initial lists of notifications
        ArrayList<Notification> initialCustomerList = TestUtility.convertToNotificationArray(
                CustomerNotification.getCustomerNotificationList()
        );

        ArrayList<Notification> initialVendorList = TestUtility.convertToNotificationArray(
                VendorNotification.getVendorNotificationList()
        );

        ArrayList<Notification> initialRunnerList = TestUtility.convertToNotificationArray(
                DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
        );

        // Order 1: Delivery - start from "preparing" status and change to "ready for pickup"
        order1.setOrderStatus(Order.OrderStatus.VENDOR_PREPARING);
        ArrayList<Notification> order1Notification = testCaseForUpdatingOrder(
                order1,
                Order.OrderStatus.READY_FOR_PICK_UP,
                initialCustomerList,
                initialVendorList,
                initialRunnerList
        );

        // Check customer notification for order 1
        assertNotNull(order1Notification.getFirst());
        assertEquals(
                "Your order " + order1.getOrderID() + " is ready. Runner " + order1.getRunnerInCharge().getName() + " will pick-up your order shortly.",
                order1Notification.getFirst().getNotificationDetails()
        );

        // Check vendor notification for order 1
        assertNotNull(order1Notification.get(1));
        assertEquals(
                "You have marked the order " + order1.getOrderID() + " as 'Ready for Pickup'.",
                order1Notification.get(1).getNotificationDetails()
        );

        // Check runner notification for order 1
        assertNotNull(order1Notification.getLast());
        assertEquals(
                "The order " + order1.getOrderID() + " is ready at stall " + order1.getOrderedStall() + ". Please proceed to the stall to collect it.",
                order1Notification.getLast().getNotificationDetails()
        );

        // Order 2: Dine In - start from "ready for pickup" to "completed"
        order2.setOrderStatus(Order.OrderStatus.READY_FOR_PICK_UP);
        ArrayList<Notification> order2Notification = testCaseForUpdatingOrder(
                order2,
                Order.OrderStatus.COMPLETED,
                initialCustomerList,
                initialVendorList,
                initialRunnerList
        );

        // Check customer notification for order 2
        assertNotNull(order2Notification.getFirst());
        assertEquals(
                "You have successfully received your order " + order2.getOrderID() + ". Enjoy your meal!",
                order2Notification.getFirst().getNotificationDetails()
        );

        // Check vendor notification for order 2
        assertNotNull(order2Notification.get(1));
        assertEquals(
                "The order " + order2.getOrderID() + " is received by customer.",
                order2Notification.get(1).getNotificationDetails()
        );

        // Check runner notification for order 2 - should be null since order 2 is a dine-in order
        assertNull(order2Notification.getLast());

        // Order 3: Takeaway - start from "ready for pickup" to "prepared"
        order3.setOrderStatus(Order.OrderStatus.READY_FOR_PICK_UP);
        ArrayList<Notification> order3Notification = testCaseForUpdatingOrder(
                order3,
                Order.OrderStatus.VENDOR_PREPARING,
                initialCustomerList,
                initialVendorList,
                initialRunnerList
        );

        // Check customer notification for order 3
        assertNotNull(order3Notification.getFirst());
        assertEquals(
                "Your order " + order3.getOrderID() + " has been reverted to 'Preparing' status by the vendor. We will notify you once it's ready.",
                order3Notification.getFirst().getNotificationDetails()
        );

        // Check vendor notification for order 3
        assertNotNull(order3Notification.get(1));
        assertEquals(
                "You have reverted the status of order " + order3.getOrderID() + " to 'Preparing'. Please update the status again once the preparation is complete.",
                order3Notification.get(1).getNotificationDetails()
        );

        // Check runner notification for order 3 - should be null since order 3 is a takeaway order
        assertNull(order3Notification.getLast());

        // Erroneous order
        order1.setOrderStatus(Order.OrderStatus.COMPLETED);
        int errorOrder = order1.vendorUpdateOrderStatus(Order.OrderStatus.VENDOR_PREPARING);
        assertEquals(0, errorOrder);

        // Order with the same status
        order2.setOrderStatus(Order.OrderStatus.READY_FOR_PICK_UP);
        int sameStatus = order2.vendorUpdateOrderStatus(Order.OrderStatus.READY_FOR_PICK_UP);
        assertEquals(0, sameStatus);
    }

    /**
     * A method to reduce the code complexity of the test case {@code testVendorUpdateOrderStatus}
     *
     * @param order               The order that the vendor changes status
     * @param status              The status to be changed to
     * @param initialCustomerList The initial list of customer notifications
     * @param initialVendorList   The initial list of vendor notifications
     * @param initialRunnerList   The initial list of delivery runner notifications
     * @return An array of the created notifications in the format of [customer, vendor, runner]
     */
    private ArrayList<Notification> testCaseForUpdatingOrder(
            Order order,
            Order.OrderStatus status,
            ArrayList<Notification> initialCustomerList,
            ArrayList<Notification> initialVendorList,
            ArrayList<Notification> initialRunnerList
    ) {

        // Update status of order
        int updateOrder = order.vendorUpdateOrderStatus(status);

        // Make sure that the status is updated successfully
        assertEquals(1, updateOrder);

        // Check if the correct status is set
        assertEquals(status, order.getOrderStatus());

        // Retrieve the difference in notifications
        ArrayList<Notification> differentCustomerNotification = TestUtility.getDifferentNotification(
                initialCustomerList,
                TestUtility.convertToNotificationArray(
                        CustomerNotification.getCustomerNotificationList()
                )
        );

        ArrayList<Notification> differentVendorNotification = TestUtility.getDifferentNotification(
                initialVendorList,
                TestUtility.convertToNotificationArray(
                        VendorNotification.getVendorNotificationList()
                )
        );

        ArrayList<Notification> differentRunnerNotification = TestUtility.getDifferentNotification(
                initialRunnerList,
                TestUtility.convertToNotificationArray(
                        DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
                )
        );

        // Make sure that no repeating notifications are created per order
        assertTrue(differentCustomerNotification.size() < 2);
        assertTrue(differentVendorNotification.size() < 2);
        assertTrue(differentRunnerNotification.size() < 2);

        // Set the list as null if it does not have any elements
        if (differentCustomerNotification.isEmpty()) differentCustomerNotification = null;
        if (differentVendorNotification.isEmpty()) differentVendorNotification = null;
        if (differentRunnerNotification.isEmpty()) differentRunnerNotification = null;

        // Retrieve the notification array - format: customer, vendor, runner
        ArrayList<Notification> differentNotification = new ArrayList<>();
        differentNotification.add(differentCustomerNotification == null ? null : differentCustomerNotification.getFirst());
        differentNotification.add(differentVendorNotification == null ? null : differentVendorNotification.getFirst());
        differentNotification.add(differentRunnerNotification == null ? null : differentRunnerNotification.getFirst());

        // Reset environment and return list
        resetTestingEnvironment();
        return differentNotification;
    }

    /**
     * This test focuses on the process where the vendors upload their profile picture to the system
     */
    @Test
    void testVendorUploadBackgroundPicture() {

        // Get the picture to be uploaded
        File backgroundPicture = new File(TESTING_PICTURE_PATH + "empty_picture.jpg");

        // Upload picture as background for vendor 1
        boolean backgroundUpload = PictureIO.uploadVendorBackground(backgroundPicture, vendor1);

        // Make sure that the process is successful
        assertTrue(backgroundUpload);

        // Make sure that the file can be retrieved, and the name is correct
        File uploadedBackgroundPicture = PictureIO.retrieveBackgroundPicture(vendor1);
        String expectedPictureName = vendor1.getStall().getStallID() + "_background";
        String actualPictureName = uploadedBackgroundPicture.getName().split("\\.")[0];

        assertNotNull(uploadedBackgroundPicture);
        assertEquals(expectedPictureName, actualPictureName);

        // Test if the vendor does not update any files, and make sure that the process is successful
        boolean noBackground = PictureIO.uploadVendorBackground(null, vendor1);
        assertTrue(noBackground);

        // Attempt to retrieve for the background picture, but the file should not exist. Should get empty picture instead
        File retrieveNoBackground = PictureIO.retrieveBackgroundPicture(vendor1);
        assertEquals("empty_picture.jpg", retrieveNoBackground.getName());
    }

    @Test
    void testVendorAddingItem() {

        // Get the list of items before adding item
        ArrayList<Item> itemList = new ArrayList<>(Item.getItemList());

        // Perform operation of adding items
        boolean successfulAdded = Item.addNewVendorItem(
                "A new item",
                20.5,
                "This is just an ordinary item.",
                new File("src/test/resources/picture/empty_picture.jpg"),
                vendor1
        );

        // Make sure that the operation is successful
        assertTrue(successfulAdded);

        // Check if a new item is inserted into the item list
        ArrayList<Item> newItemList = TestUtility.getDifferentItem(itemList, Item.getItemList());
        assertEquals(1, newItemList.size());

        // Check if the correct item is added to the list by checking description
        Item addedItem = newItemList.getFirst();
        assertNotNull(addedItem);
        assertEquals("This is just an ordinary item.", addedItem.getDescription());

        // Save the new list to the initial list for further testing purpose
        itemList = new ArrayList<>(Item.getItemList());

        // Check if item is created with null inputs
        boolean nullInput = Item.addNewVendorItem(
                " ",
                10,
                " ",
                null,
                null
        );
        assertFalse(nullInput);

        // Check if item is created with repeating names
        boolean repeatedName = Item.addNewVendorItem(
                "A New Item",
                10.3,
                "This is another ordinary item.",
                new File("src/test/resources/picture/test_picture.jpg"),
                vendor1
        );
        assertFalse(repeatedName);

        // Check if item is created with an empty picture
        boolean emptyPicture = Item.addNewVendorItem(
                "Another Item",
                10.9,
                "This is the third item to be added.",
                null,
                vendor1
        );
        assertTrue(emptyPicture);

        // Make sure that the newly added item is in the list
        ArrayList<Item> itemWithoutPictureList = TestUtility.getDifferentItem(itemList, Item.getItemList());
        assertEquals(1, itemWithoutPictureList.size());

        // Retrieve the newly added item
        Item itemWithoutPicture = itemWithoutPictureList.getFirst();
        assertNotNull(itemWithoutPicture);

        // Check if the picture for the item is blank picture
        File testBlankPicture = PictureIO.retrieveItemPicture(itemWithoutPicture);
        assertNotNull(testBlankPicture);
        assertEquals("empty_picture.jpg", testBlankPicture.getName());
    }

    @Test
    void testVendorModifyItem() {

        // Item 1 is in the list, so get the ID for item 1
        String itemID = item1.getItemID();

        // Declare attributes to be modified
        String newName = "An ordinary item";
        double newPrice = 100.35;
        String newDescription = "A weird item with a weird price.";
        File newPicture = new File(TESTING_PICTURE_PATH + "empty_picture.jpg");

        // The new picture should have a new name
        String newPictureName = item1.getStall().getStallID() + "_" + item1.getItemID();

        // Change attributes of item 1
        boolean modifyItem = item1.modifyItemDetails(
                newName,
                newPrice,
                newDescription,
                newPicture
        );
        assertTrue(modifyItem);

        // Make sure that the attributes are changed correctly
        Item modifiedItem = Item.getItem(itemID);
        assertNotNull(modifiedItem);
        assertEquals(newName, modifiedItem.getItemName());
        assertEquals(newPrice, modifiedItem.getPrice());
        assertEquals(newDescription, modifiedItem.getDescription());

        // Make sure that the picture is changed correctly
        File modifiedItemPicture = PictureIO.retrieveItemPicture(item1);
        assertNotNull(modifiedItemPicture);

        File[] directory = new File(TESTING_PICTURE_PATH).listFiles();
        assertEquals(
                Utility.retrieveFileWithoutExtension(directory, newPictureName),
                modifiedItemPicture
        );

        // Try to change the attributes back to its name
        boolean ownName = item1.modifyItemDetails(
                item1.getItemName().toUpperCase(),
                item1.getPrice(),
                item1.getDescription(),
                newPicture
        );
        assertTrue(ownName);

        // Try to change the attributes to the name of another item
        boolean existingName = item1.modifyItemDetails(
                "DELIVERY FEES",
                item1.getPrice(),
                item1.getDescription(),
                newPicture
        );
        assertFalse(existingName);

        // Try to not include picture when modifying item 1
        boolean noPicture = item1.modifyItemDetails(
                item1.getItemName(),
                item1.getPrice(),
                item1.getDescription(),
                null
        );
        assertTrue(noPicture);

        // Make sure that the empty picture is retrieved when no picture is added for item
        File blankPicture = PictureIO.retrieveItemPicture(item1);
        assertNotNull(blankPicture);
        assertEquals("empty_picture.jpg", blankPicture.getName());
    }

    @Test
    void testVendorDeleteItem() {

        // Obtain the initial information for item 1
        ArrayList<Item> initialArray = new ArrayList<>(Item.getItemList());
        int initialSize = initialArray.size();
        String pictureName = item1.getStall().getStallID() + "_" + item1.getItemID();

        // Delete item 1
        boolean deleteStatus = item1.deleteItem();
        assertTrue(deleteStatus);

        // Make sure that the array size reduces
        assertEquals(initialSize - 1, Item.getItemList().size());

        // Make sure that the item cannot be retrieved from the list anymore
        assertNull(Item.getItem(item1.getItemID()));

        // Make sure that the picture of item 1 is deleted
        File[] directory = new File(TESTING_PICTURE_PATH).listFiles();
        File itemPicture = Utility.retrieveFileWithoutExtension(directory, pictureName);
        assertNull(itemPicture);
    }
}
