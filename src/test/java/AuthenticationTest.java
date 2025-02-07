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
        assertEquals(1, User.resetPassword(customer1.getEmail(), modifiedPassword));   // Make sure true is returned
        assertEquals(modifiedPassword, customer1.getPassword());                  // Make sure password is changed

        // User who fills in password that does not match requirement
        String passwordNotSatisfyReq1 = "short@1";
        String passwordNotSatisfyReq2 = "ThisP@ss10rdIsTooLong";
        String passwordNotSatisfyReq3 = "N0SpecialChar";
        String passwordNotSatisfyReq4 = "NoNumbers";
        String passwordNotSatisfyReq5 = "1234567890";

        assertEquals(-1, User.resetPassword(admin1.getEmail(), passwordNotSatisfyReq1));
        assertEquals(-1,User.resetPassword(vendor1.getEmail(), passwordNotSatisfyReq2));
        assertEquals(-1,User.resetPassword(runner1.getEmail(), passwordNotSatisfyReq3));
        assertEquals(-1,User.resetPassword(manager1.getEmail(), passwordNotSatisfyReq4));
        assertEquals(-1,User.resetPassword(customer1.getEmail(), passwordNotSatisfyReq5));

        // User who fills in the wrong email
        String wrongEmail = "invalid_email";
        assertEquals(0, User.resetPassword(wrongEmail, modifiedPassword));
    }

    @Test
    void testEmailFormat() {

        // List of emails in the correct format
        String[] correctList = new String[]{
                "test@example.com",
                "user.name@domain.co",
                "first.last@sub.domain.com",
                "user+name@domain.org",
                "name123@domain.com",
                "name@subdomain.domain.com",
                "john.doe@company.co.uk",
                "email@valid-domain.net"
        };

        // List of emails in the wrong format
        String[] wrongList = new String[]{
                "",
                "test@.com",
                "test@com",
                "@example.com",
                "username@domain.",
                "username@domain..com",
                "username@.domain.com",
                "user@com",
                "user@domain.c",
                "us@er@domain.com",
                "user@domain,com",
                "user..name@domain.com",
                "user@domain.c@om",
                "user@domain_com.com",
                "user@domain#example.com",
                "@example.com",
                "random@1.11"
        };

        // Check if the method is able to identify correct and wrong emails
        for (String email : correctList) assertTrue(User.checkEmailFormat(email));
        for (String email : wrongList) assertFalse(User.checkEmailFormat(email));
    }
}
