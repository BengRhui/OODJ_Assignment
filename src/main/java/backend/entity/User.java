package backend.entity;

import java.util.ArrayList;

/**
 * Class {@code User} is used as a parent class consisting of the general attributes for other
 * user-related subclasses.
 *
 * @author Beng Rhui (TP068495)
 */
public class User {

    private static final ArrayList<User> userList = new ArrayList<>();
    /**
     * The attributes for {@code User} class. <br>
     * An overall list that contains all instances of the {@code User} class is also included.
     */
    protected String userID;
    protected String email;
    protected String password;
    protected String name;

    /**
     * Constructor for {@code User} class.
     *
     * @param userID   The ID for user.
     * @param email    The email for user that is used to login to the system.
     * @param password The password for user that is used to login to the system.
     * @param name     The real-world name for user.
     */
    public User(String userID, String email, String password, String name) {
        this.userID = userID;
        this.email = email;
        this.password = password;
        this.name = name;
    }

    /**
     * A method to add instances of the {@code User} class into the {@code userList} array.
     *
     * @param user The {@code User} object to be added into the ArrayList
     */
    public static void addUserToList(User user) {
        userList.add(user);
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
