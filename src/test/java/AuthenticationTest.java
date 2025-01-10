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

    /**
     * Focuses on password reset password
     */
    @Test
    void testResetPassword() {

        // User who fills in the correct information
        String modifiedPassword = "New@pass123";
        assertTrue(User.resetPassword(customer1.getEmail(), modifiedPassword));   // Make sure true is returned
        assertEquals(modifiedPassword, customer1.getPassword());                  // Make sure password is changed

        // User who fills in password that does not match requirement
        String passwordNotSatisfyReq1 = "short@1";
        String passwordNotSatisfyReq2 = "ThisP@ss10rdIsTooLong";
        String passwordNotSatisfyReq3 = "N0SpecialChar";
        String passwordNotSatisfyReq4 = "NoNumbers";
        String passwordNotSatisfyReq5 = "1234567890";

        assertFalse(User.resetPassword(admin1.getEmail(), passwordNotSatisfyReq1));
        assertFalse(User.resetPassword(vendor1.getEmail(), passwordNotSatisfyReq2));
        assertFalse(User.resetPassword(runner1.getEmail(), passwordNotSatisfyReq3));
        assertFalse(User.resetPassword(manager1.getEmail(), passwordNotSatisfyReq4));
        assertFalse(User.resetPassword(customer1.getEmail(), passwordNotSatisfyReq5));

        // User who fills in the wrong email
        String wrongEmail = "invalid_email";
        assertFalse(User.resetPassword(wrongEmail, modifiedPassword));
    }

}
