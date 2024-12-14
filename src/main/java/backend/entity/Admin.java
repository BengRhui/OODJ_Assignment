package backend.entity;

import java.util.ArrayList;

/**
 * Class {@code Admin} represents users with the Admin role in the system.<br>
 * This class is inherited from the {@code User} class with the following attributes from the class:
 *
 * @author Beng Rhui (TP068495)
 */
public class Admin extends User {

    /**
     * A list that contains all instances of the {@code Admin} class.
     */
    private final static ArrayList<Admin> adminList = new ArrayList<>();

    /**
     * Constructor for the {@code Admin} class.
     *
     * @param userID   The ID of the admin
     * @param email    The email of the admin that is used to log into the system
     * @param password The password used to log into the system
     * @param name     The real-world name of the admin
     */
    public Admin(String userID, String email, String password, String name) {
        super(userID, email, password, name);
    }

    /**
     * A method to return a list containing all objects from the {@code Admin} class.
     *
     * @return ArrayList with all {@code Admin} instances
     */
    public static ArrayList<Admin> getAdminList() {
        return adminList;
    }

    /**
     * A method to add an {@code Admin} object into an overall list.
     */
    public static void addToAdminList(Admin admin) {
        adminList.add(admin);
    }

    /**
     * A method to print out information about the {@code Admin} object.
     *
     * @return A string representation of the {@code Admin} object
     */
    @Override
    public String toString() {
        return "Admin ID: " + super.userID + "\n" +
                "Admin Email: " + super.email + "\n" +
                "Admin Password: " + super.password + "\n" +
                "Admin Name: " + super.name;
    }
}
