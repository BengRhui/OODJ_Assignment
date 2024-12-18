import backend.entity.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class {@code AuthenticationTest} covers the tests related to log in and password reset.
 *
 * @author Beng Rhui (TP068495)
 */
public class AuthenticationTest extends BaseTest {

    /**
     * Focuses on login function
     */
    @Test
    void testLogin() {

        // Modify login from different users
        User customerLogin = User.getUser(customer1.getEmail(), customer1.getPassword());
        User adminLogin = User.getUser(admin1.getEmail(), admin1.getPassword());
        User vendorLogin = User.getUser(vendor1.getEmail(), vendor1.getPassword());
        User runnerLogin = User.getUser(runner1.getEmail(), runner1.getPassword());
        User managerLogin = User.getUser(manager1.getEmail(), manager1.getPassword());

        // Prepare a few cases with wrong credentials
        User wrongEmail = User.getUser("wrong_email", customer1.getPassword());
        User wrongPassword = User.getUser(runner1.getEmail(), "wrong_password");

        // Check if the correct user type is retrieved
        assertInstanceOf(Customer.class, customerLogin, "Customer is unable to login.");
        assertInstanceOf(Admin.class, adminLogin, "Admin is unable to login.");
        assertInstanceOf(Vendor.class, vendorLogin, "Vendor is unable to login.");
        assertInstanceOf(DeliveryRunner.class, runnerLogin, "Runner is unable to login.");
        assertInstanceOf(Manager.class, managerLogin, "Manager is unable to login.");

        // Make sure that null object is produced for the wrong credentials
        assertNull(wrongEmail, "User can login with the wrong email.");
        assertNull(wrongPassword, "User can login with the wrong password.");
    }

}
