import backend.entity.*;
import backend.notification.CustomerNotification;
import backend.notification.DeliveryRunnerNotification;
import backend.notification.Notification;
import backend.notification.VendorNotification;
import backend.utility.Utility;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class {@code AdminTest} focuses on the operations that an admin would perform.
 *
 * @author Beng Rhui (TP068495)
 */
public class AdminTest extends BaseTest {

    /**
     * This method focuses on the operation where an admin creates a customer
     */
    @Test
    void testAdminCreateCustomer() {

        // Get initial customer list
        ArrayList<Customer> initialCustomerList = new ArrayList<>(Customer.getCustomerList());

        // Prepare information obtained from GUI (contains erroneous information)
        String ID = Customer.generateNewID();
        String name = "Black Swan";
        String contactNumber = "12-8557";
        String addressLine1 = "1, Mirror Street";
        String addressLine2 = "The Garden of Recollection";
        String postcode = "45678";
        String state = "Terengganu";
        String city = "Kuala Terengganu";
        String email = "firefly@hunters";
        char[] password = "black123swan".toCharArray();
        char[] confirmPassword = "Black@123swan".toCharArray();

        // Run the loop for 5 times
        for (int i = 0; i < 6; i++) {

            // Try to create a customer account based on these values
            int createCustomerStatus = Customer.createNewCustomer(
                    ID,
                    name,
                    contactNumber,
                    addressLine1,
                    addressLine2,
                    postcode,
                    state,
                    city,
                    email,
                    password,
                    confirmPassword
            );

            // Use a switch-case expression to help proceed to each case
            switch (i) {

                // First case: Email is not in correct format
                case 0 -> {
                    assertEquals(-1, createCustomerStatus);
                    email = "firefly@hunters.abc";
                }

                // Second case: Email is used by other users
                case 1 -> {
                    assertEquals(-2, createCustomerStatus);
                    email = "blackswan@garden.my";
                }

                // Third case: Password does not meet requirement
                case 2 -> {
                    assertEquals(-3, createCustomerStatus);
                    password = "BlackSwan@123".toCharArray();
                }

                // Fourth case: Passwords do not match
                case 3 -> {
                    assertEquals(-4, createCustomerStatus);
                    confirmPassword = "BlackSwan@123".toCharArray();
                }

                // Fifth case: Contact number is not valid
                case 4 -> {
                    assertEquals(-5, createCustomerStatus);
                    contactNumber = "05-1849845";
                }

                // Finally, successfully created customer
                case 5 -> assertEquals(1, createCustomerStatus);
            }
        }

        // Retrieve the newly created customer
        ArrayList<Customer> differentCustomer = Customer.getCustomerList();
        differentCustomer.removeAll(initialCustomerList);

        // Make sure that only one new customer is created
        assertEquals(1, differentCustomer.size());

        // Check if the correct customer is created
        Customer newCustomer = differentCustomer.getFirst();
        assertEquals(name, newCustomer.getName());
        assertEquals(contactNumber, newCustomer.getContactNumber());
        assertEquals(email, newCustomer.getEmail());
    }

    @Test
    void testAdminUpdateCustomer() {

        // Get initial list of notifications
        ArrayList<Notification> initialCustomerList = TestUtility.convertToNotificationArray(
                CustomerNotification.getCustomerNotificationList()
        );

        // Initialize information for testing
        String customerName = customer1.getName();
        String contactNumber = "12-345";
        String addressLine1 = customer1.getAddress().getAddressLine1();
        String addressLine2 = customer1.getAddress().getAddressLine2();
        String postcode = customer1.getAddress().getPostcode();
        String state = customer1.getAddress().getState().toString();
        String city = customer1.getAddress().getCity();
        String email = "abc@123";
        char[] password = "Abc123".toCharArray();
        char[] confirmPassword = "Abc@123".toCharArray();

        // Start loop to validate different attributes
        for (int i = 0; i < 7; i++) {

            // Call the modify customer function on customer 1
            int createCustomerStatus = customer1.modifyCustomer(
                    customerName,
                    contactNumber,
                    addressLine1,
                    addressLine2,
                    postcode,
                    state,
                    city,
                    email,
                    password,
                    confirmPassword
            );

            // Use switch statement to go through each validation stage
            switch (i) {

                // Case 1: Email is not in the correct format
                case 0 -> {
                    assertEquals(-1, createCustomerStatus);
                    email = "firefly@hunters.abc";
                }

                // Case 2: Email is unavailable
                case 1 -> {
                    assertEquals(-2, createCustomerStatus);
                    email = customer1.getEmail();
                }

                // Case 3: Use back own email
                case 2 -> assertNotEquals(-2, createCustomerStatus);

                // Case 4: Password does not meet requirement
                case 3 -> {
                    assertEquals(-3, createCustomerStatus);
                    password = "Abc@1234".toCharArray();
                }

                // Case 5: Password does not match
                case 4 -> {
                    assertEquals(-4, createCustomerStatus);
                    confirmPassword = "Abc@1234".toCharArray();
                }

                // Case 6: Contact number format is incorrect
                case 5 -> {
                    assertEquals(-5, createCustomerStatus);
                    contactNumber = "05-1849845";
                }

                // Finally information is modified successfully
                case 6 -> assertEquals(1, createCustomerStatus);
            }
        }

        // Check if the information is updated correctly
        assertEquals("05-1849845", customer1.getContactNumber());
        assertEquals("Abc@1234", customer1.getPassword());

        // Check if notification is created
        ArrayList<Notification> differentNotification = TestUtility.getDifferentNotification(
                initialCustomerList,
                TestUtility.convertToNotificationArray(
                        CustomerNotification.getCustomerNotificationList()
                )
        );
        assertEquals(1, differentNotification.size());

        // Check if the correct description is produced
        Notification createdNotification = differentNotification.getFirst();
        assertEquals(
                "Your personal information has been updated successfully.",
                createdNotification.getNotificationDetails()
        );
    }

    /**
     * This test focuses on the operation where admin deletes a customer account.
     */
    @Test
    void testAdminDeleteCustomer() {

        // Make sure that customer 1 is in the list
        boolean inList = Customer.getCustomerList().contains(customer1);
        assertTrue(inList);

        // Delete customer
        boolean deleteSuccessful = customer1.deleteCustomer();
        assertTrue(deleteSuccessful);

        // Make sure that the customer is not in the list anymore
        boolean getCustomer = Customer.getCustomerList().contains(customer1);
        assertFalse(getCustomer);

        // Make sure that the notification, order, transaction and feedback list does not have the customer anymore
        boolean notificationListHasCustomer = CustomerNotification.getCustomerNotificationList().stream()
                .anyMatch(notification -> notification.getCustomer().getUserID().equals(customer1.getUserID()));
        assertFalse(notificationListHasCustomer);

        boolean orderListHasCustomer = Order.getOrderList().stream()
                .anyMatch(order -> order.getOrderingCustomer() != null && order.getOrderingCustomer().getUserID().equals(customer1.getUserID()));
        assertFalse(orderListHasCustomer);

        boolean transactionListHasCustomer = Transaction.getTransactionList().stream()
                .anyMatch(transaction -> transaction.getCustomer().getUserID().equals(customer1.getUserID()));
        assertFalse(transactionListHasCustomer);

        boolean feedbackListHasCustomer = Feedback.getFeedbackList().stream()
                .anyMatch(feedback -> feedback.getCustomerAssociated() != null && feedback.getCustomerAssociated().getUserID().equals(customer1.getUserID()));
        assertFalse(feedbackListHasCustomer);

        // Make sure that the function cannot be used on customer who is not exist
        boolean error = customer1.deleteCustomer();
        assertFalse(error);
    }

    /**
     * This test focuses on the operation where admin tops up the customer's e-wallet.
     */
    @Test
    void testAdminTopUpCustomerWallet() {

        // Get initial e-wallet amount
        double initialAmount = customer1.getEWalletAmount();

        // Get initial customer notification list
        ArrayList<Notification> initialCustomerList = TestUtility.convertToNotificationArray(
                CustomerNotification.getCustomerNotificationList()
        );

        // Get initial transaction list
        ArrayList<Transaction> initialTransactionList = new ArrayList<>(Transaction.getTransactionList());

        // Perform top up
        boolean topUpStatus = customer1.topUpWallet(10.1264, "QR Payment");

        // Check if top up is successful
        assertTrue(topUpStatus);

        // Check if top up amount tallies
        assertEquals(initialAmount + 10.13, customer1.getEWalletAmount());

        // Check if new notification is created
        ArrayList<Notification> differentCustomerList = TestUtility.getDifferentNotification(
                initialCustomerList,
                TestUtility.convertToNotificationArray(
                        CustomerNotification.getCustomerNotificationList()
                )
        );
        assertEquals(1, differentCustomerList.size());

        // Check if the description is correct
        Notification newNotification = differentCustomerList.getFirst();
        assertEquals(
                "Thank you for paying using QR Payment. RM10.13 has been successfully topped up to your e-wallet.",
                newNotification.getNotificationDetails()
        );

        // Retrieve the newly created transaction record
        ArrayList<Transaction> newTransactionList = new ArrayList<>(Transaction.getTransactionList());
        newTransactionList.removeAll(initialTransactionList);

        // Check if transaction is created
        assertEquals(1, newTransactionList.size());

        // Check the contents of the transaction
        assertEquals(
                customer1,
                newTransactionList.getFirst().getCustomer()
        );

        assertEquals(
                Transaction.PaymentMethod.QR_PAYMENT,
                newTransactionList.getFirst().getPaymentMethod()
        );

        // Erroneous input: wrong value inserted into method
        boolean wrongInput = customer1.topUpWallet(-100, "Cash");
        assertFalse(wrongInput);

        // Erroneous input: wrong payment method passed into method
        wrongInput = customer1.topUpWallet(100, "Wrong method");
        assertFalse(wrongInput);
    }

    /**
     * This test focuses on the operation where the admin creates an admin account.
     */
    @Test
    void testAdminCreateVendor() {

        // Get the initial list of vendors
        ArrayList<Vendor> initialVendorList = new ArrayList<>(Vendor.getVendorList());

        // Prepare information for new vendor (with erroneous information)
        String vendorID = Vendor.generateNewID();
        String vendorName = "General Jin Yuan";
        String stallName = "Not Available Store";
        String email = "jinyuan@123";
        char[] password = "12345".toCharArray();
        char[] confirmPassword = "Abc@1234".toCharArray();

        // Start a loop
        for (int i = 0; i < 6; i++) {

            // Try to create a vendor account
            int createAccountStatus = Vendor.createNewVendor(
                    vendorID,
                    vendorName,
                    stallName,
                    email,
                    password,
                    confirmPassword
            );

            // Use a switch statement to handle different cases
            switch (i) {

                // First case: Email is not in correct format
                case 0 -> {
                    assertEquals(-1, createAccountStatus);
                    email = "firefly@hunters.abc";
                }

                // Second case: Email is used by other user
                case 1 -> {
                    assertEquals(-2, createAccountStatus);
                    email = "jinyuan@mail.com.my";
                }

                // Third case: Password does not meet requirements
                case 2 -> {
                    assertEquals(-3, createAccountStatus);
                    password = "ABc@1234".toCharArray();
                }

                // Fourth case: Passwords do not match
                case 3 -> {
                    assertEquals(-4, createAccountStatus);
                    password = "Abc@1234".toCharArray();
                }

                // Fifth case: Store is not found
                case 4 -> {
                    assertEquals(-5, createAccountStatus);
                    stallName = stall1.getStallName();
                }

                // Finally the account is created
                case 5 -> assertEquals(1, createAccountStatus);
            }
        }

        // Check if the new vendor is created
        ArrayList<Vendor> differentVendor = Vendor.getVendorList();
        differentVendor.removeAll(initialVendorList);
        assertEquals(1, differentVendor.size());

        // Check if the details are correct
        assertEquals(stall1, differentVendor.getFirst().getStall());
        assertEquals("jinyuan@mail.com.my", differentVendor.getFirst().getEmail());
        assertEquals("Abc@1234", differentVendor.getFirst().getPassword());
    }

    /**
     * This test focuses on the operation where admin changes the details of a vendor account.
     */
    @Test
    void testAdminModifyVendorAccount() {

        // Get initial notification list
        ArrayList<Notification> initialNotification = TestUtility.convertToNotificationArray(
                VendorNotification.getVendorNotificationList()
        );

        // Prepare information to modify
        String vendorName = vendor1.getName();
        String stallName = "Not Available Store";
        String email = "wrong_email";
        char[] password = "incorrect_password".toCharArray();
        char[] confirmPassword = "Abc@1234".toCharArray();

        // Start the loop
        for (int i = 0; i < 7; i++) {

            // Perform modification
            int modifyVendor = vendor1.modifyVendor(
                    vendorName,
                    stallName,
                    email,
                    password,
                    confirmPassword
            );

            // Use switch statement to go through each case
            switch (i) {

                // Case 1: Email is not in correct format
                case 0 -> {
                    assertEquals(-1, modifyVendor);
                    email = "firefly@hunters.abc";
                }

                // Case 2: Email is not available
                case 1 -> {
                    assertEquals(-2, modifyVendor);
                    email = vendor1.getEmail();
                }

                // Case 3: The same email can be used
                case 2 -> {
                    assertNotEquals(-2, modifyVendor);
                    email = "abc@123.com";
                }

                // Case 4: Password does not meet requirement
                case 3 -> {
                    assertEquals(-3, modifyVendor);
                    password = "ABc@1234".toCharArray();
                }

                // Case 5: Passwords do not match with each other
                case 4 -> {
                    assertEquals(-4, modifyVendor);
                    password = "Abc@1234".toCharArray();
                }

                // Case 6: Stall cannot be retrieved based on name
                case 5 -> {
                    assertEquals(-5, modifyVendor);
                    stallName = stall1.getStallName();
                }

                // Finally, the details are modified successfully
                case 6 -> assertEquals(1, modifyVendor);
            }
        }

        // Check if the details are modified correctly
        assertEquals(stall1, vendor1.getStall());
        assertEquals(email, vendor1.getEmail());
        assertEquals(Utility.generateString(password), vendor1.getPassword());

        // Check if a new notification is produced
        ArrayList<Notification> differentNotification = TestUtility.getDifferentNotification(
                initialNotification,
                TestUtility.convertToNotificationArray(
                        VendorNotification.getVendorNotificationList()
                )
        );
        assertEquals(1, differentNotification.size());

        // Check if the vendor ID and description produced is correct
        assertEquals(vendor1.getUserID(), differentNotification.getFirst().getEntityID());
        assertEquals(
                "Your personal information has been updated.",
                differentNotification.getFirst().getNotificationDetails()
        );
    }

    /**
     * The test focuses on the operation where the admin deletes a vendor account.
     */
    @Test
    void testAdminDeleteVendor() {

        // Get initial notification list
        ArrayList<Notification> initialNotification = TestUtility.convertToNotificationArray(
                VendorNotification.getVendorNotificationList()
        );

        // Make sure that the notification list contains the vendor
        boolean notificationHasVendor = initialNotification.stream()
                .anyMatch(notification -> notification.getEntityID().equals(vendor1.getUserID()));
        assertTrue(notificationHasVendor);

        // Make sure that the vendor is in the list
        boolean vendorInList = Vendor.getVendorList().contains(vendor1);
        assertTrue(vendorInList);

        // Try to delete a vendor account and make sure that the operation is successful
        boolean deleteVendor = vendor1.deleteVendor();
        assertTrue(deleteVendor);

        // Make sure that the vendor is not in the list anymore
        vendorInList = Vendor.getVendorList().contains(vendor1);
        assertFalse(vendorInList);

        // Make sure that the notifications related to the vendor is deleted
        ArrayList<Notification> newNotificationList = TestUtility.convertToNotificationArray(
                VendorNotification.getVendorNotificationList()
        );
        notificationHasVendor = newNotificationList.stream()
                .anyMatch(notification -> notification.getEntityID().equals(vendor1.getUserID()));
        assertFalse(notificationHasVendor);

        // Try to perform the method on the same vendor again
        deleteVendor = vendor1.deleteVendor();
        assertFalse(deleteVendor);
    }

    /**
     * This test focuses on the operation where the admin adds a new delivery runner account.
     */
    @Test
    void testAdminAddRunner() {

        // Get the initial list of runners
        ArrayList<DeliveryRunner> initialRunnerList = new ArrayList<>(DeliveryRunner.getDeliveryRunnerList());

        // Prepare data for creating a new account
        String runnerID = DeliveryRunner.generateNewID();
        String name = "Master Yan Qin";
        String contactNumber = "12-345";
        String email = "yanqin@luofu";
        char[] password = "12345".toCharArray();
        char[] confirmPassword = "Abc@1234".toCharArray();

        // Start a loop
        for (int i = 0; i < 6; i++) {

            // Try to add account
            int createRunner = DeliveryRunner.createNewRunner(
                    runnerID,
                    name,
                    contactNumber,
                    email,
                    password,
                    confirmPassword
            );

            // Use a switch statement to handle different cases
            switch (i) {

                // Case 1: Email is not in correct format (-1)
                case 0 -> {
                    assertEquals(-1, createRunner);
                    email = "firefly@hunters.abc";
                }

                // Case 2: Email is used by another user (-2)
                case 1 -> {
                    assertEquals(-2, createRunner);
                    email = "yanqin@luofu.com";
                }

                // Case 3: Password does not meet requirement (-3)
                case 2 -> {
                    assertEquals(-3, createRunner);
                    password = "ABc@1234".toCharArray();
                }

                // Case 4: Password does not match with confirm password (-4)
                case 3 -> {
                    assertEquals(-4, createRunner);
                    password = "Abc@1234".toCharArray();
                }

                // Case 5: Contact number is not in correct format (-5)
                case 4 -> {
                    assertEquals(-5, createRunner);
                    contactNumber = "012-8461045";
                }

                // Finally the account is created successfully
                case 5 -> assertEquals(1, createRunner);
            }
        }

        // Retrieve the list containing new runner
        ArrayList<DeliveryRunner> differentRunner = new ArrayList<>(DeliveryRunner.getDeliveryRunnerList());
        boolean ableToRemove = differentRunner.removeAll(initialRunnerList);
        assertTrue(ableToRemove);

        // Make sure that there is only one new runner created
        assertEquals(1, differentRunner.size());

        // Retrieve the newly added runner
        DeliveryRunner newRunner = differentRunner.getFirst();

        // Make sure that the details are correct
        assertEquals(
                "012-8461045",
                newRunner.getContactNumber()
        );

        assertEquals(
                "yanqin@luofu.com",
                newRunner.getEmail()
        );

        assertEquals(
                "Abc@1234",
                newRunner.getPassword()
        );
    }

    /**
     * This method focuses on the operation where the admin modifies the delivery runner account.
     */
    @Test
    void testAdminModifyRunner() {

        // Get initial notification list
        ArrayList<Notification> initialNotification = TestUtility.convertToNotificationArray(
                DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
        );

        // Prepare data to be modified
        String name = runner1.getName();
        String contactNumber = "12-345";
        String email = "abc123";
        char[] password = "12345".toCharArray();
        char[] confirmPassword = "Abc@1234".toCharArray();

        // Start a loop
        for (int i = 0; i < 7; i++) {

            // Perform modification
            int modifyRunner = runner1.modifyRunner(
                    name,
                    contactNumber,
                    email,
                    password,
                    confirmPassword
            );

            // Use a switch statement to handle different cases
            switch (i) {

                // Case 1: Email is not in the correct format
                case 0 -> {
                    assertEquals(-1, modifyRunner);
                    email = "trailblazer@woohoo.my";
                }

                // Case 2: Email is not available
                case 1 -> {
                    assertEquals(-2, modifyRunner);
                    email = runner1.getEmail();
                }

                // Case 3: Own email can be used
                case 2 -> {
                    assertNotEquals(-2, modifyRunner);
                    email = "abc@123.com";
                }

                // Case 4: Password does not meet requirement
                case 3 -> {
                    assertEquals(-3, modifyRunner);
                    password = "ABc@1234".toCharArray();
                }

                // Case 5: Password does not match with confirm password
                case 4 -> {
                    assertEquals(-4, modifyRunner);
                    password = "Abc@1234".toCharArray();
                }

                // Case 6: Contact number does not match format
                case 5 -> {
                    assertEquals(-5, modifyRunner);
                    contactNumber = "012-8461045";
                }

                // Finally the account is modified successfully
                case 6 -> assertEquals(1, modifyRunner);
            }
        }

        // Check if the information is modified correctly
        assertEquals(
                "012-8461045",
                runner1.getContactNumber()
        );

        assertEquals(
                "abc@123.com",
                runner1.getEmail()
        );

        assertEquals(
                "Abc@1234",
                runner1.getPassword()
        );

        // Retrieve the created notification
        ArrayList<Notification> differentNotification = TestUtility.getDifferentNotification(
                initialNotification,
                TestUtility.convertToNotificationArray(
                        DeliveryRunnerNotification.getDeliveryRunnerNotificationList()
                )
        );

        // Make sure that only one notification is created
        assertEquals(1, differentNotification.size());

        // Make sure that the notification is correct
        assertEquals(
                "Your personal information has been updated.",
                differentNotification.getFirst().getNotificationDetails()
        );

        assertEquals(
                runner1.getUserID(),
                differentNotification.getFirst().getEntityID()
        );
    }

    /**
     * This test focuses on the operation where the admin deletes a delivery runner account.
     */
    @Test
    void testAdminDeleteRunner() {

        // Initialize the availability list for runner
        DeliveryRunner.initializeAvailabilityList();

        // Make sure that the runner is in the availability list
        Map<String, Boolean> initialAvailabilityList = DeliveryRunner.getAvailabilityList();
        Boolean isRunnerInAvailability = initialAvailabilityList.get(runner1.getUserID());
        assertNotNull(isRunnerInAvailability);

        // Get initial size of notifications
        int initialNotificationSize = DeliveryRunnerNotification.getDeliveryRunnerNotificationList().size();

        // Make sure that the notification list has notifications associated with runner
        boolean runnerInNotification = DeliveryRunnerNotification.getDeliveryRunnerNotificationList().stream()
                .anyMatch(notification -> notification.getRunner().getUserID().equals(runner1.getUserID()));
        assertTrue(runnerInNotification);

        // Make sure that there is at least an order associated with runner
        boolean runnerInOrder = Order.getOrderList().stream()
                .anyMatch(order -> order.getRunnerInCharge().getUserID().equals(runner1.getUserID()));
        assertTrue(runnerInOrder);

        // Make sure that the runner is in the list
        boolean runnerInList = DeliveryRunner.getDeliveryRunnerList().contains(runner1);
        assertTrue(runnerInList);

        // Delete the runner
        boolean deleteOperation = runner1.deleteRunner();
        assertTrue(deleteOperation);

        // Make sure that the runner is not in the list anymore
        runnerInList = DeliveryRunner.getDeliveryRunnerList().contains(runner1);
        assertFalse(runnerInList);

        // Make sure that the size of the notification list decreases
        int currentNotificationSize = DeliveryRunnerNotification.getDeliveryRunnerNotificationList().size();
        assertTrue(currentNotificationSize < initialNotificationSize);

        // Make sure that the notifications associated with the runners are not found anymore
        runnerInNotification = DeliveryRunnerNotification.getDeliveryRunnerNotificationList().stream()
                .anyMatch(notification -> notification.getRunner().getUserID().equals(runner1.getUserID()));
        assertFalse(runnerInNotification);

        // Make sure that the orders associated are turned to null
        runnerInOrder = Order.getOrderList().stream()
                .anyMatch(order -> order.getRunnerInCharge() != null && order.getRunnerInCharge().getUserID().equals(runner1.getUserID()));
        assertFalse(runnerInOrder);

        // Make sure that the availability list is also updated, where the runner is not in the list anymore
        Map<String, Boolean> currentAvailabilityList = DeliveryRunner.getAvailabilityList();
        isRunnerInAvailability = currentAvailabilityList.get(runner1.getUserID());
        assertNull(isRunnerInAvailability);
    }

    /**
     * This test focuses on the operation where the admin creates a new stall.
     */
    @Test
    void testAdminCreateStall() {

        // Get initial list of stalls
        ArrayList<Stall> initialStallList = new ArrayList<>(Stall.getStallList());

        // Prepare data to create stall
        String stallID = Stall.generateNewID();
        String stallName = stall1.getStallName();
        String[] stallCategories = new String[]{"Invalid category"};

        // Start a loop
        for (int i = 0; i < 3; i++) {

            // Create the stall
            int createStall = Stall.createNewStall(
                    stallID,
                    stallName,
                    stallCategories
            );

            // Use a switch statement to handle different cases
            switch (i) {

                // Case 1: Name is used by other stalls
                case 0 -> {
                    assertEquals(-1, createStall);
                    stallName = "A unique name";
                }

                // Case 2: Category is invalid
                case 1 -> {
                    assertEquals(-2, createStall);
                    stallCategories = new String[]{"Western", "Local", "Halal"};
                }

                // Finally the store is created
                case 2 -> assertEquals(1, createStall);
            }
        }

        // Retrieve the stall
        ArrayList<Stall> differentStall = new ArrayList<>(Stall.getStallList());
        differentStall.removeAll(initialStallList);

        // Check if the stall is added into list
        assertEquals(1, differentStall.size());

        // Check if the stall name is correct
        assertEquals(
                "A unique name",
                differentStall.getFirst().getStallName()
        );

        // Check if the categories are correct
        Stall.StallCategories[] expectedCategories = new Stall.StallCategories[]{
                Stall.StallCategories.WESTERN,
                Stall.StallCategories.LOCAL,
                Stall.StallCategories.HALAL
        };
        Arrays.sort(expectedCategories);
        assertArrayEquals(expectedCategories, differentStall.getFirst().getStallCategories());
    }

    /**
     * This test focuses on the operation where the admin updates the information of a stall.
     */
    @Test
    void testAdminUpdateStall() {

        // Create another stall so that we can test for the validation
        Stall anotherStall = new Stall("S002", "A new stall", new Stall.StallCategories[]{Stall.StallCategories.INDIAN, Stall.StallCategories.LOCAL});
        Stall.addStallToList(anotherStall);

        // Get initial vendor notification list (Note: vendor 1 is in stall 1)
        ArrayList<Notification> initialNotificationList = TestUtility.convertToNotificationArray(
                VendorNotification.getVendorNotificationList()
        );

        // Get data to update stall
        String name = "A new stall";
        String[] category = {"Invalid category"};

        // Start a loop
        for (int i = 0; i < 4; i++) {

            // Modify the stall
            int modifyStall = stall1.modifyStall(
                    name,
                    category
            );

            // Use switch statement to go through each stage
            switch (i) {

                // Case 1: Name is used by other stalls
                case 0 -> {
                    assertEquals(-1, modifyStall);
                    name = stall1.getStallName();
                }

                // Case 2: Own name can be used
                case 1 -> {
                    assertNotEquals(-1, modifyStall);
                    name = "A unique name";
                }

                // Case 2: Invalid category exists
                case 2 -> {
                    assertEquals(-2, modifyStall);
                    category = new String[]{"Malay"};
                }

                // Finally the information can be modified successfully
                case 3 -> assertEquals(1, modifyStall);
            }
        }

        // Check if the correct information is applied
        assertEquals(
                "A unique name",
                stall1.getStallName()
        );

        assertArrayEquals(
                new Stall.StallCategories[]{Stall.StallCategories.MALAY},
                stall1.getStallCategories()
        );

        // Check if any new notification is created
        ArrayList<Notification> differentNotification = TestUtility.getDifferentNotification(
                initialNotificationList,
                TestUtility.convertToNotificationArray(
                        VendorNotification.getVendorNotificationList()
                )
        );
        assertEquals(1, differentNotification.size());

        // Check if the description of the new notification is correct
        assertEquals(
                "The stall information has been updated successfully.",
                differentNotification.getFirst().getNotificationDetails()
        );
    }

    @Test
    void testAdminDeleteStall() {

        // Make sure that stall 1 is in the stall list
        boolean stallInList = Stall.getStallList().contains(stall1);
        assertTrue(stallInList);

        // Make sure that there are items associated with the stall
        boolean itemInList = Item.getItemList().stream()
                .anyMatch(item -> item.getStall() != null && item.getStall().getStallID().equals(stall1.getStallID()));
        assertTrue(itemInList);

        // Make sure that at least one order is associated with the stall
        boolean orderInList = Order.getOrderList().stream()
                .anyMatch(order -> order.getOrderedStall().getStallID().equals(stall1.getStallID()));
        assertTrue(orderInList);

        // Try to remove stall 1 (fail coz vendor 1 is still associated with stall 1)
        int deleteStall = stall1.deleteStall();
        assertEquals(0, deleteStall);

        // Remove the vendor
        boolean deleteVendor = vendor1.deleteVendor();
        assertTrue(deleteVendor);

        // Try to remove stall 1 again (fail coz the items are still associated with the orders)
        deleteStall = stall1.deleteStall();
        assertEquals(-1, deleteStall);

        // Change the associated orders to completed
        order2.setOrderStatus(Order.OrderStatus.COMPLETED);
        order3.setOrderStatus(Order.OrderStatus.COMPLETED);

        // Try to remove stall 1 again
        deleteStall = stall1.deleteStall();
        assertEquals(1, deleteStall);

        // Check if stall 1 is in the list
        stallInList = Stall.getStallList().contains(stall1);
        assertFalse(stallInList);

        // Try to remove stall 1 once again (fail coz stall 1 is not in the list anymore)
        deleteStall = stall1.deleteStall();
        assertEquals(-3, deleteStall);

        // Check if the items associated with stall 1 still exists
        itemInList = Item.getItemList().stream()
                .anyMatch(item -> item.getStall() != null && item.getStall().getStallID().equals(stall1.getStallID()));
        assertFalse(itemInList);

        // Check if the stall attribute for orders is changed to null
        orderInList = Order.getOrderList().stream()
                .anyMatch(order -> order.getOrderedStall() != null && order.getOrderedStall().getStallID().equals(stall1.getStallID()));
        assertFalse(orderInList);
    }
}
