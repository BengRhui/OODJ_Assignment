package backend.entity;

import java.util.ArrayList;

/**
 * Class {@code User} is used as a parent class consisting of the general attributes for other
 * user-related subclasses.
 *
 * @author Beng Rhui (TP068495)
 */
public class User {

    /**
     * The attributes for {@code User} class. <br>
     * An overall list that contains all instances of the {@code User} class is also included.
     */
    private static final ArrayList<User> userList = new ArrayList<>();
    protected String userID;
    protected String email;
    protected String password;
    protected String name;

    /**
     * Constructor for {@code User} class.
     *
     * @param userID   The ID for user.
     * @param email    The email for user that is used to log into the system.
     * @param password The password for user that is used to log into the system.
     * @param name     The real-world name for user.
     */
    public User(String userID, String email, String password, String name) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    /**
     * A method to return the list containing all {@code User} objects.
     *
     * @return An ArrayList containing all instances of {@code User} objects.
     */
    public static ArrayList<User> getUserList() {

        // Clear the user list
        userList.clear();

        // Add all the users into user list and return it
        userList.addAll(Admin.getAdminList());
        userList.addAll(Customer.getCustomerList());
        userList.addAll(DeliveryRunner.getDeliveryRunnerList());
        userList.addAll(Manager.getManagerList());
        userList.addAll(Vendor.getVendorList());
        return userList;
    }

    /**
     * A method to get {@code User} object during system login by providing email and password as credentials.
     *
     * @param email    The email of the user
     * @param password The password of the user
     * @return A {@code User} object if user and password matches, else return null.
     */
    public static User getUser(String email, String password) {

        // Loop through the list of users
        for (User user : getUserList()) {

            // Continue loop if email and password does not match
            if (user.getEmail().equalsIgnoreCase(email) && user.getPassword().equals(password)) {
                return user;
            }
        }

        // Return null if there's no matching credentials
        return null;
    }

    /**
     * A method to validate password
     *
     * @param password The string password provided
     * @return True if password matches requirement, false otherwise
     */
    public static boolean validatePassword(String password) {

        // Check if the password length matches requirements
        boolean correctPasswordLength = password.length() >= 8 && password.length() <= 20;

        // Check if password contains alphabets and digits
        boolean containsAlphabets = false;
        boolean containsDigits = false;

        for (char character : password.toCharArray()) {
            if (Character.isLetter(character)) containsAlphabets = true;
            if (Character.isDigit(character)) containsDigits = true;
            if (containsAlphabets && containsDigits) break;
        }

        // Check if password contains special characters
        boolean containsSpecialCharacters = password.matches(".*[!@#$%^&*(),.?\":{}|<>].*");

        // Return true if all values are met
        return correctPasswordLength && containsAlphabets && containsDigits && containsSpecialCharacters;
    }

    /**
     * A method to reset user password
     *
     * @param email       The email of the user
     * @param newPassword The new password of the user
     * @return {@code 1} is the password resets successfully<br>
     * {@code 0} if email is not available<br>
     * {@code -1} if password does not meet requirements
     */
    public static int resetPassword(String email, String newPassword) {

        // Return false if the new password does not match
        if (!validatePassword(newPassword)) return -1;

        // Loop through the list of users
        for (User user : getUserList()) {

            // Continue the loop if email does not match
            if (!user.getEmail().equalsIgnoreCase(email)) {
                continue;
            }

            // Set the new password
            user.setPassword(newPassword.strip());
            return 1;
        }

        // Return false if there is no matching email
        return 0;
    }

    /**
     * A method to check the availability of email.
     *
     * @param email The new email to be registered / updated
     * @return {@code true} if the email can be used, {@code false} otherwise
     */
    public static boolean isEmailAvailable(String email) {
        return getUserList().stream()                                           // Get overall user list
                .noneMatch(user -> user.getEmail().equalsIgnoreCase(email));    // Check if there is a match for the inputted email
    }

    /**
     * A method to check the format of an email to see if the email is valid.
     *
     * @param email The email to be checked
     * @return {@code true} if the email format is valid, {@code false} otherwise
     */
    public static boolean checkEmailFormat(String email) {

        // Return false if the email is empty
        if (email.isBlank()) return false;

        // Get the index for "@" and "."
        int indexForAt = email.indexOf('@');
        int indexForDot = email.lastIndexOf('.');

        // If both symbols are not found, return false
        if (indexForAt == -1 || indexForDot == -1) return false;

        // If "." is in front of "@", return false
        if (indexForDot < indexForAt) return false;

        // If "." is immediately after "@", return false (e.g. abc@.com is not valid)
        if (email.contains("@.")) return false;

        // If "@" appears for more than two times, return false
        if (indexForAt != email.lastIndexOf('@')) return false;

        // If there is no character in front of "@" or less than 2 characters behind of ".", return false
        if (indexForAt == 0 || indexForDot >= email.length() - 2) return false;

        // If there exist two dots and the dots are next to each other, return false
        if (email.contains("..")) return false;

        // If the part after "@" has special characters, return false
        boolean partAfterAtHasSpecial = email.split("@")[1].matches(".*[!#$%^&*(),?\":{}|<>_].*");

        // This marks the end of email validation
        return !partAfterAtHasSpecial;
    }

    /**
     * A method to check the format of the contact number
     *
     * @param contactNumber The contact number to be checked
     * @return {@code true} if the contact number is valid, else {@code false}
     */
    public static boolean checkContactNumberFormat(String contactNumber) {

        // Checks if the contact number matches the format of "0_-_______" or "0__-________"
        return contactNumber.matches("^0[0-9]{1,2}-[0-9]{7,8}$");
    }

    /**
     * Getters and setters method for {@code User} class.<br>
     * The attributes involved include:<br>
     * - {@code userID}<br>
     * - {@code email}<br>
     * - {@code password}<br>
     * - {@code name}<br>
     */
    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Prints the information associated with a {@code User} object.
     *
     * @return A string representation of {@code User} object.
     */
    @Override
    public String toString() {
        return "User ID:" + userID + "\n" +
                "Email: " + email + "\n" +
                "Password: " + password;
    }
}
