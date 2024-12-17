package backend;

import backend.entity.*;
import backend.file_io.*;
import backend.notification.*;
import backend.utility.Utility;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class {@code ReadWriteFileTest} covers the read and write operations of all text files.<br>
 * The general test process is as follows:<br>
 * 1. Modify attributes of object<br>
 * 2. Write to file<br>
 * 3. Read from file<br>
 * 4. Check if the attributes tally
 */
class ReadWriteFileTest extends BaseTest {

    /**
     * Focuses on the login credential file.
     */
    @Test
    void testLoginFile() {

        // Get current array size for admin and customer list
        int adminArraySize = Admin.getAdminList().size();
        int customerArraySize = Customer.getCustomerList().size();

        // Change login credentials
        admin1.setEmail("new-mail@123.com");
        customer1.setPassword("qwerty");

        // Write details to file
        CredentialsFileIO.writeCredentialsFile();

        // Read from other files
        UserFileIO[] fileHandlers = new UserFileIO[]{new AdminFileIO(), new CustomerFileIO()};
        Arrays.stream(fileHandlers).forEach(UserFileIO::readFile);

        // Make sure that size of Admin and Customer list matches with the size before writing files
        assertEquals(adminArraySize, Admin.getAdminList().size(), "Expected one admin after reading file");
        assertEquals(customerArraySize, Customer.getCustomerList().size(), "Expected one customer after reading file");

        // Make sure that the same admin and customer can be retrieved
        Admin retrievedAdmin = Admin.getAdmin(admin1.getUserID());
        assertNotNull(retrievedAdmin);

        Customer retrievedCustomer = Customer.getCustomer(customer1.getUserID());
        assertNotNull(retrievedCustomer);

        // Check if credentials are changed correctly
        assertEquals(admin1.getEmail(), retrievedAdmin.getEmail(), "Fail to update email");
        assertEquals(customer1.getPassword(), retrievedCustomer.getPassword(), "Fail to update password");
    }


    /**
     * Focuses on the admin file.
     */
    @Test
    void testAdminInfoFile() {

        // Get size of admin array
        int adminArraySize = Admin.getAdminList().size();

        // Modify attributes of admins
        admin1.setName("A new name");

        // Write new info to files
        AdminFileIO fileIO = new AdminFileIO();
        fileIO.writeFile();

        // Read from files
        fileIO.readFile();

        // Make sure that the size of array does not change
        assertEquals(adminArraySize, Admin.getAdminList().size(), "Expected one admin after reading file");

        // Check if the same admin can be retrieved
        Admin retrievedAdmin = Admin.getAdmin(admin1.getUserID());
        assertNotNull(retrievedAdmin);

        // Check if the changes are reflected in file
        assertEquals(admin1.getName(), retrievedAdmin.getName(), "Fail to update information to file");
    }

    /**
     * Focuses on the customer file.
     */
    @Test
    void testCustomerFile() {

        // Get the initial length of the customer list
        int customerArraySize = Customer.getCustomerList().size();

        // Modify customer attributes
        customer1.setContactNumber("013-5798648");
        customer1.setEWalletAmount(29.10);
        customer1.setAddress(
                new Address(
                        "Line 1",
                        "Line 2",
                        "12345",
                        "City 1",
                        Address.State.PERAK
                )
        );

        // Write to file
        CustomerFileIO fileIO = new CustomerFileIO();
        fileIO.writeFile();

        // Read from file
        fileIO.readFile();

        // Check if the size of customer array remains the same
        assertEquals(customerArraySize, Customer.getCustomerList().size(), "Expected one customer after reading file");

        // Check if the same customer can be retrieved
        Customer retrievedCustomer = Customer.getCustomer(customer1.getUserID());
        assertNotNull(retrievedCustomer);

        // Check if the changes are reflected into the file
        assertEquals(customer1.getContactNumber(), retrievedCustomer.getContactNumber(), "Fail to update contact number to file");
        assertEquals(customer1.getEWalletAmount(), retrievedCustomer.getEWalletAmount(), "Fail to update e-wallet amount to file");
        assertEquals(customer1.getAddress().toString(), retrievedCustomer.getAddress().toString(), "Fail to update address to file");
    }

    /**
     * Focuses on delivery runner file
     */
    @Test
    void testRunnerFile() {

        // Get initial size of delivery runner list
        int runnerListSize = DeliveryRunner.getDeliveryRunnerList().size();

        // Change attribute
        runner1.setContactNumber("012-3456823");
        runner1.setName("Mr Sunday");

        // Write to file
        DeliveryRunnerFileIO fileIO = new DeliveryRunnerFileIO();
        fileIO.writeFile();

        // Read from file
        fileIO.readFile();

        // Check if array size remains the same
        assertEquals(runnerListSize, DeliveryRunner.getDeliveryRunnerList().size(), "Expected one DeliveryRunner after reading file");

        // Check if the same runner can be retrieved
        DeliveryRunner retrievedRunner = DeliveryRunner.getRunner(runner1.getUserID());
        assertNotNull(retrievedRunner);

        // Check if the changes are reflected in file
        assertEquals(runner1.getContactNumber(), retrievedRunner.getContactNumber(), "Fail to update contact number to file");
        assertEquals(runner1.getName(), retrievedRunner.getName(), "Fail to update name to file");
    }

    /**
     * Focuses on vendor files
     */
    @Test
    void testVendorFile() {

        // Retrieve initial size of vendor list
        int vendorListSize = Vendor.getVendorList().size();

        // Modify attributes
        Stall newStall = new Stall(
                "S002",
                "Fried Chicken store",
                new Stall.StallCategories[] {
                        Stall.StallCategories.LOCAL,
                        Stall.StallCategories.FAST_FOOD
                }
        );
        Stall.addStallToList(newStall);

        vendor1.setName("Miss Robin");
        vendor1.setStall(newStall);

        // Write to file
        VendorFileIO fileIO = new VendorFileIO();
        fileIO.writeFile();

        // Read from file
        fileIO.readFile();

        // Ensure that the new array has the same size as previous
        assertEquals(vendorListSize, Vendor.getVendorList().size(), "Expected one vendor after reading file");

        // Check if the same vendor can be retrieved
        Vendor retrievedVendor = Vendor.getVendor(vendor1.getUserID());
        assertNotNull(retrievedVendor);

        // Check if the changes are reflected into the text file
        assertEquals(vendor1.getName(), retrievedVendor.getName(), "Fail to update name to file");
        assertEquals(vendor1.getStall().toString(), retrievedVendor.getStall().toString(), "Fail to update stall to file");
    }

    /**
     * Focuses on manager file
     */
    @Test
    void testManagerFile() {

        // Get the initial array size of manager
        int managerListSize = Manager.getManagerList().size();

        // Change attributes
        manager1.setName("Ms Topaz");

        // Write to file
        ManagerFileIO fileIO = new ManagerFileIO();
        fileIO.writeFile();

        // Read from file
        fileIO.readFile();

        // Check if the array size remains
        assertEquals(managerListSize, Manager.getManagerList().size(), "Expected one manager after reading file");

        // Check if the same manager can be retrieved
        Manager retrievedManager = Manager.getManager(manager1.getUserID());
        assertNotNull(retrievedManager);

        // Check if the changes are reflected in file
        assertEquals(manager1.getName(), retrievedManager.getName(), "Fail to update name to file");
    }

    /**
     * Focuses on feedback file
     */
    @Test
    void testFeedbackFile() {

        // Obtain the initial size of the feedback array
        int feedbackListSize = Feedback.getFeedbackList().size();

        // Modify the attributes
        feedback1.setFeedbackCategory(Feedback.Category.VENDOR);
        feedback2.setRatings(2.3);
        feedback3.setOrderAssociated(order3);

        // Write to file
        FeedbackFileIO.writeFile();

        // Read from file
        FeedbackFileIO.readFile();

        // Make sure that the size of the array remains
        assertEquals(feedbackListSize, Feedback.getFeedbackList().size(), "Expected three feedback after reading file");

        // Make sure the same feedback can be retrieved from file
        Feedback retrieveFeedback1 = Feedback.getFeedback(feedback1.getFeedbackID());
        assertNotNull(retrieveFeedback1);

        Feedback retrieveFeedback2 = Feedback.getFeedback(feedback2.getFeedbackID());
        assertNotNull(retrieveFeedback2);

        Feedback retrieveFeedback3 = Feedback.getFeedback(feedback3.getFeedbackID());
        assertNotNull(retrieveFeedback3);

        // Check if the information is reflected into the text file
        assertEquals(feedback1.getFeedbackCategory().toString(), retrieveFeedback1.getFeedbackCategory().toString(), "Fail to update category to file");
        assertEquals(feedback2.getRatings(), retrieveFeedback2.getRatings(), "Fail to update ratings to file");
        assertEquals(feedback3.getOrderAssociated().toString(), retrieveFeedback3.getOrderAssociated().toString(), "Fail to update order to file");
    }

    /**
     * Focuses on item file
     */
    @Test
    void testItemFile() {

        // Get the initial size of array
        int itemListSize = Item.getItemList().size();

        // Modify attributes
        Stall newStall = new Stall(
                "S002",
                "ABC Fish",
                new Stall.StallCategories[]{
                        Stall.StallCategories.CHINESE,
                        Stall.StallCategories.DESSERT
                }
        );
        Stall.addStallToList(newStall);

        item1.setDescription("ABC Red Beans");
        item1.setStall(newStall);

        // Write to file
        ItemFileIO.writeFile();

        // Read from file
        ItemFileIO.readFile();

        // Check current array size with initial size
        assertEquals(itemListSize, Item.getItemList().size(), "Expected three items after reading file");

        // Check if the same objects can be retrieved from file
        Item retrieveItem1 = Item.getItem(item1.getItemID());
        assertNotNull(retrieveItem1);

        // Check if the changes are reflected into file
        assertEquals(item1.getDescription(), retrieveItem1.getDescription(), "Fail to update description to file");
        assertEquals(item1.getStall().toString(), retrieveItem1.getStall().toString(), "Fail to update stall to file");
    }

    /**
     * Focuses on notification file
     */
    @Test
    void testNotificationFile() {

        // Get the initial size of array
        int notificationListSize = CustomerNotification.getCustomerNotificationList().size() +
                VendorNotification.getVendorNotificationList().size() +
                DeliveryRunnerNotification.getDeliveryRunnerNotificationList().size();

        // Modify attributes
        Customer newCustomer = new Customer(
                "C002",
                "cus2@yahoo.com",
                "cusPassword2",
                "Customer 2",
                "014-8928491",
                new Address("Line 1",
                        "Line 2",
                        "12345",
                        "Malacca City",
                        Address.State.MELAKA),
                165.40,
                "No note for me"
        );
        Customer.addToCustomerList(newCustomer);

        customerNotification1.setCustomer(newCustomer);
        customerNotification2.setReadStatus(NotificationStatus.UNREAD);
        vendorNotification1.setNotificationDetails("Hello");
        runnerNotification1.setNotificationTitle("A New Title");

        // Write to file
        NotificationIO.writeFile();

        // Read from file
        NotificationIO.readFile();

        // Check current array size with initial size
        assertEquals(
                notificationListSize,
                CustomerNotification.getCustomerNotificationList().size() +
                        VendorNotification.getVendorNotificationList().size() +
                        DeliveryRunnerNotification.getDeliveryRunnerNotificationList().size(),
                "Expected four notifications after reading file"
        );

        // Check if the same objects can be retrieved from file
        CustomerNotification retrievedCustomerNotification1 = CustomerNotification.getNotification(customerNotification1.getNotificationID());
        assertNotNull(retrievedCustomerNotification1);

        CustomerNotification retrievedCustomerNotification2 = CustomerNotification.getNotification(customerNotification2.getNotificationID());
        assertNotNull(retrievedCustomerNotification2);

        VendorNotification retrievedVendorNotification = VendorNotification.getNotification(vendorNotification1.getNotificationID());
        assertNotNull(retrievedVendorNotification);

        DeliveryRunnerNotification retrievedRunnerNotification = DeliveryRunnerNotification.getNotification(runnerNotification1.getNotificationID());
        assertNotNull(retrievedRunnerNotification);

        // Check if the changes are reflected into file
        assertEquals(customerNotification1.getCustomer().toString(), retrievedCustomerNotification1.getCustomer().toString(), "Fail to update customer to file");
        assertEquals(customerNotification2.getReadStatus(), retrievedCustomerNotification2.getReadStatus(), "Fail to update read status to file");
        assertEquals(vendorNotification1.getNotificationDetails(), retrievedVendorNotification.getNotificationDetails(), "Fail to update notification details to file");
        assertEquals(runnerNotification1.getNotificationTitle(), retrievedRunnerNotification.getNotificationTitle(), "Fail to update title to file");
    }

    /**
     * Focuses on order file
     */
    @Test
    void testOrderFile() {

        // Get the initial size of array
        int orderListSize = Order.getOrderList().size();

        // Modify attributes
        order1.setOrderedDate(Utility.changeStringToTime("18/12/2024 00:13:15"));
        order2.setDiningType(Order.DiningType.DELIVERY);
        order3.setNoteToVendor("No note for me");

        // Write to file
        OrderFileIO.writeFile();

        // Read from file
        OrderFileIO.readFile();

        // Check current array size with initial size
        assertEquals(orderListSize, Order.getOrderList().size(), "Expected three orders after reading file");

        // Check if the same objects can be retrieved from file
        Order retrievedOrder1 = Order.getOrder(order1.getOrderID());
        assertNotNull(retrievedOrder1);

        Order retrievedOrder2 = Order.getOrder(order2.getOrderID());
        assertNotNull(retrievedOrder2);

        Order retrievedOrder3 = Order.getOrder(order3.getOrderID());
        assertNotNull(retrievedOrder3);

        // Check if the changes are reflected into file
        assertEquals(order1.getOrderedDate(), retrievedOrder1.getOrderedDate(), "Fail to update ordered date to file");
        assertEquals(order2.getDiningType(), retrievedOrder2.getDiningType(), "Fail to update dining type to file");
        assertEquals(order3.getNoteToVendor(), retrievedOrder3.getNoteToVendor(), "Fail to update note to file");
    }

    /**
     * Focuses on stall file
     */
    @Test
    void testStallFile() {

        // Get the initial size of array
        int stallListSize = Stall.getStallList().size();

        // Modify attributes
        stall1.setStallName("New Stall");
        stall1.setStallCategories(
                new Stall.StallCategories[] {
                        Stall.StallCategories.JAPANESE,
                        Stall.StallCategories.DESSERT
                });

        // Write to file
        StallFileIO.writeFile();

        // Read from file
        StallFileIO.readFile();

        // Check current array size with initial size
        assertEquals(stallListSize, Stall.getStallList().size(), "Expected one stall after reading file");

        // Check if the same objects can be retrieved from file
        Stall retrievedStall = Stall.getStall(stall1.getStallID());
        assertNotNull(retrievedStall);

        // Check if the changes are reflected into file
        assertEquals(stall1.getStallName(), retrievedStall.getStallName(), "Fail to update stall name to file");
        assertEquals(Arrays.toString(stall1.getStallCategories()), Arrays.toString(retrievedStall.getStallCategories()), "Fail to update stall categories to file");
    }

    /**
     * Focuses on transaction file
     */
    @Test
    void testTransactionFile() {

        // Get the initial size of array
        int transactionListSize = Transaction.getTransactionList().size();

        // Modify attributes
        transaction1.setPaymentMethod(Transaction.PaymentMethod.CASH);
        transaction2.setTransactionTime(Utility.changeStringToTime("18/12/2024 00:35:12"));

        // Write to file
        TransactionFileIO.writeFile();

        // Read from file
        TransactionFileIO.readFile();

        // Check current array size with initial size
        assertEquals(transactionListSize, Transaction.getTransactionList().size(), "Expected two transactions after reading file");

        // Check if the same objects can be retrieved from file
        Transaction retrievedTransaction1 = Transaction.getTransaction(transaction1.getTransactionID());
        assertNotNull(retrievedTransaction1);

        Transaction retrievedTransaction2 = Transaction.getTransaction(transaction2.getTransactionID());
        assertNotNull(retrievedTransaction2);

        // Check if the changes are reflected into file
        assertEquals(transaction1.getPaymentMethod(), retrievedTransaction1.getPaymentMethod(), "Fail to update payment method to file");
        assertEquals(Utility.generateString(transaction2.getTransactionTime()), Utility.generateString(retrievedTransaction2.getTransactionTime()), "Fail to update transaction time to file");
    }
}