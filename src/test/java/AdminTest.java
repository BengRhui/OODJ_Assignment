import backend.entity.Customer;
import backend.entity.Vendor;
import backend.notification.CustomerNotification;
import backend.notification.Notification;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

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
}
