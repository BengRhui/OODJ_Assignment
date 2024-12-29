import backend.entity.Customer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void testCreateCustomer() {

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
}
