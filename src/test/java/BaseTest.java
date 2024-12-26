import backend.entity.*;
import backend.file_io.*;
import backend.notification.CustomerNotification;
import backend.notification.DeliveryRunnerNotification;
import backend.notification.NotificationStatus;
import backend.notification.VendorNotification;
import backend.utility.Utility;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class {@code ReadWriteFileTest} covers the read and write operations of all text files.
 *
 * @author Beng Rhui (TP068495)
 */
public class BaseTest {

    /**
     * A list of objects to be initialized in testing
     */
    static final String TESTING_FILE_PATH = "src/test/resources/text_file/";
    static final String TESTING_PICTURE_PATH = "src/test/resources/picture/";

    static Address address1;
    static Stall stall1;

    static Admin admin1;
    static Manager manager1;
    static Customer customer1;
    static Vendor vendor1;
    static DeliveryRunner runner1;

    static Feedback feedback1, feedback2, feedback3;
    static Item item1;
    static Order order1, order2, order3;
    static Transaction transaction1, transaction2;

    static CustomerNotification customerNotification1, customerNotification2;
    static DeliveryRunnerNotification runnerNotification1;
    static VendorNotification vendorNotification1;

    /**
     * Clean up all the resources created during testing
     */
    @AfterAll
    static void cleanEnvironment() {

        // Retrieve the names of the file
        String[] fileNames = new String[]{
                AdminFileIO.ADMIN_FILE_NAME,
                CredentialsFileIO.CREDENTIALS_FILE_NAME,
                CustomerFileIO.CUSTOMER_FILE_NAME,
                DeliveryRunnerFileIO.RUNNER_FILE_NAME,
                FeedbackFileIO.FEEDBACK_FILE_NAME,
                ItemFileIO.ITEM_FILE_NAME,
                ManagerFileIO.MANAGER_FILE_NAME,
                NotificationIO.NOTIFICATION_FILE_NAME,
                OrderFileIO.ORDER_FILE_NAME,
                StallFileIO.STALL_FILE_NAME,
                TransactionFileIO.TRANSACTION_FILE_NAME,
                VendorFileIO.VENDOR_FILE_NAME
        };

        // Loop through each file name
        for (String fileName : fileNames) {
            try {
                Path path = Paths.get(TESTING_FILE_PATH, fileName);        // Get the relative path and delete the files
                Files.deleteIfExists(path);
            } catch (IOException e) {
                System.out.println("Error deleting file: " + fileName);    // Display error if file fails to be deleted
            }
        }

        // Delete any additional pictures created
        File[] pictureDirectory = new File(TESTING_PICTURE_PATH).listFiles();
        assertNotNull(pictureDirectory);
        for (File file : pictureDirectory) {
            if (file.getName().contains("test_picture")) {
                continue;
            }
            assertTrue(file.delete());
        }
    }

    /**
     * Set directory and test if the system is able to retrieve file from the directory
     */
    @BeforeAll
    static void setDirectory() {

        // Set file path to test resources text_file file
        FileIO.setParentPathToFile(TESTING_FILE_PATH);

        // Test if file can be written from file path by reading a preset file from test resources folder
        try (BufferedReader reader = new BufferedReader(new FileReader(TESTING_FILE_PATH + "exist_file.txt"))) {
            String line = reader.readLine();
            assertEquals(
                    "Hi, this file is used to test if JUnit is able to detect this file during testing.",
                    line
            );
        } catch (IOException e) {
            fail("The test case is not able to access the files under test resources -> text_file folder.");
        }


        // Set directory to store pictures
        PictureIO.setParentPathToItemDirectory(TESTING_PICTURE_PATH);
        PictureIO.setParentPathToStoreDirectory(TESTING_PICTURE_PATH);

        // Set up the file directory
        File[] pictureDirectory = new File(TESTING_PICTURE_PATH).listFiles();

        // Test if the picture file can be detected
        try {
            assertNotNull(pictureDirectory);
            assertEquals("test_picture.jpg", pictureDirectory[0].getName());
        } catch (AssertionError e) {
            fail("The test case is not able to access the picture file under test resources -> picture folder.");
        }
    }

    /**
     * Removes the objects in arrays after each testing to avoid duplication
     */
    @AfterEach
    void clearArray() {

        // Cleans all lists
        Admin.getAdminList().clear();
        Customer.getCustomerList().clear();
        DeliveryRunner.getDeliveryRunnerList().clear();
        Manager.getManagerList().clear();
        Vendor.getVendorList().clear();

        Feedback.getFeedbackList().clear();
        Item.setItemList(new ArrayList<>(List.of(Item.deliveryFees)));
        Order.getOrderList().clear();
        Stall.getStallList().clear();
        Transaction.getTransactionList().clear();

        CustomerNotification.getCustomerNotificationList().clear();
        VendorNotification.getVendorNotificationList().clear();
        DeliveryRunnerNotification.getDeliveryRunnerNotificationList().clear();
    }

    /**
     * Initialize information for testing purposes
     */
    @BeforeEach
    void setUpArray() {

        // Initialize variables for testing
        address1 = new Address(
                "123, Reception Centre",
                "Base Zone",
                "45678",
                "Herta Space Station",
                Address.State.SELANGOR
        );

        stall1 = new Stall(
                "S001",
                "The Sleepless Earl",
                new Stall.StallCategories[]{
                        Stall.StallCategories.CHINESE,
                        Stall.StallCategories.LOCAL
                }
        );
        Stall.addStallToList(stall1);

        admin1 = new Admin(
                "A001",
                "kafka@mail.com",
                "kafka123",
                "Admin Kafka"
        );
        Admin.addToAdminList(admin1);

        manager1 = new Manager(
                "M001",
                "silverwolf@123.com",
                "sw123",
                "Manager SilverWolf"
        );
        Manager.addManagerToList(manager1);

        customer1 = new Customer(
                "C001",
                "trailblazer@woohoo.my",
                "tbz123",
                "Customer Trailblazer",
                "012-3456789",
                address1,
                102.70,
                "I'm a baseball bat!"
        );
        Customer.addToCustomerList(customer1);

        vendor1 = new Vendor(
                "V001",
                "jade@ipc.com",
                "jadeInIpc10",
                "Vendor Jade",
                stall1
        );
        Vendor.addToVendorList(vendor1);

        runner1 = new DeliveryRunner(
                "R001",
                "firefly@hunters.abc",
                "ff123",
                "Firefly",
                "019-8765432"
        );
        DeliveryRunner.addToRunnerList(runner1);

        item1 = new Item(
                "I002",
                "Oak Cake Rolls",
                stall1,
                36,
                "A sweet cake"
        );
        Item.addItemToList(item1);

        HashMap<Item, Integer> itemCollection = new HashMap<>();
        itemCollection.put(Item.deliveryFees, 1);
        itemCollection.put(item1, 2);

        order1 = new Order(
                "ORD00001",
                customer1,
                stall1,
                runner1,
                Order.DiningType.DELIVERY,
                null,
                "Less spicy please.",
                120,
                Utility.changeStringToTime("16/12/2024 23:03:10"),
                Order.OrderStatus.RUNNER_DELIVERY,
                itemCollection
        );

        order2 = new Order(
                "ORD00002",
                customer1,
                stall1,
                null,
                Order.DiningType.DINE_IN,
                "T001",
                "No chilly",
                30,
                Utility.changeStringToTime("17/12/2024 03:14:05"),
                Order.OrderStatus.VENDOR_PREPARING,
                itemCollection
        );

        order3 = new Order(
                "ORD00003",
                customer1,
                stall1,
                null,
                Order.DiningType.TAKEAWAY,
                null,
                "Zero spicy please.",
                10.95,
                Utility.changeStringToTime("17/12/2024 10:42:20"),
                Order.OrderStatus.READY_FOR_PICK_UP,
                itemCollection
        );
        Order.addToOrderList(order1, order2, order3);

        feedback1 = new Feedback(
                "F001",
                Feedback.Category.DELIVERY_RUNNER,
                order1,
                4.5,
                "Good feedback 1",
                "Very good runner",
                "Thank you sir"
        );

        feedback2 = new Feedback(
                "F002",
                Feedback.Category.SYSTEM,
                null,
                3.5,
                "Moderate feedback 2",
                "Moderate good system",
                null
        );

        feedback3 = new Feedback(
                "F003",
                Feedback.Category.VENDOR,
                order2,
                1.5,
                "Bad feedback 3",
                "Very bad vendor",
                "Sorry, but I can't do anything though."
        );
        Feedback.addToFeedbackList(feedback1, feedback2, feedback3);

        transaction1 = new Transaction(
                "TRANS001",
                customer1,
                Utility.changeStringToTime("17/12/2024 13:09:54"),
                12,
                Transaction.TransactionType.CASH_OUT,
                Transaction.PaymentMethod.E_WALLET
        );

        transaction2 = new Transaction(
                "TRANS002",
                customer1,
                Utility.changeStringToTime("17/12/2024 13:13:40"),
                100,
                Transaction.TransactionType.CASH_IN,
                Transaction.PaymentMethod.QR_PAYMENT
        );
        Transaction.addToList(transaction1, transaction2);

        customerNotification1 = new CustomerNotification(
                "NC001",
                customer1,
                Utility.changeStringToTime("17/12/2024 13:22:23"),
                NotificationStatus.UNREAD,
                "Top Up Successful",
                "You have successfully topped up RM100 into your account."
        );

        customerNotification2 = new CustomerNotification(
                "NC002",
                customer1,
                Utility.changeStringToTime("17/12/2024 13:25:20"),
                NotificationStatus.READ,
                "Payment Successful",
                "You have paid RM12 for order ORD0001 via the e-wallet system."
        );
        CustomerNotification.addToList(customerNotification1, customerNotification2);

        runnerNotification1 = new DeliveryRunnerNotification(
                "NR001",
                runner1,
                Utility.changeStringToTime("17/12/2024 13:27:02"),
                NotificationStatus.UNREAD,
                "Delivery Request Accepted",
                "You have accepted the delivery request for order ORD0001."
        );
        DeliveryRunnerNotification.addToList(runnerNotification1);

        vendorNotification1 = new VendorNotification(
                "NV001",
                vendor1,
                Utility.changeStringToTime("17/12/2024 13:28:54"),
                NotificationStatus.READ,
                "Update Order Status",
                "You have updated the status of order ORD0001 to 'Ready for Pick Up'."
        );
        VendorNotification.addToList(vendorNotification1);

        // Write objects into files to make object and file consistent
        // Read files is not needed as we have already initialized all the lists
        StallFileIO.writeFile();
        CredentialsFileIO.writeCredentialsFile();

        UserFileIO[] fileIOHandlers = new UserFileIO[]{
                new AdminFileIO(),
                new ManagerFileIO(),
                new CustomerFileIO(),
                new VendorFileIO(),
                new DeliveryRunnerFileIO()
        };
        Arrays.stream(fileIOHandlers).forEach(UserFileIO::writeFile);

        FeedbackFileIO.writeFile();
        ItemFileIO.writeFile();
        OrderFileIO.writeFile();
        TransactionFileIO.writeFile();
        NotificationIO.writeFile();
    }
}