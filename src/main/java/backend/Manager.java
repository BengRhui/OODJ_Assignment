package backend;

import java.util.ArrayList;

/**
 * Class {@code Manager} is used to store information of the users with Manager position.
 *
 * @author Beng Rhui (TP068495)
 */
public class Manager extends User {

    /**
     * An ArrayList is included to store all {@code Manager} objects.
     */
    private final static ArrayList<Manager> managerList = new ArrayList<>();

    /**
     * Constructor used to instantiate the {@code Manager} objects.
     *
     * @param userID   Identifier of the manager
     * @param email    Email used to log into to the system
     * @param password Password used to log into the system
     * @param name     Real-world name of managers
     */
    public Manager(String userID, String email, String password, String name) {
        super(userID, email, password, name);
        addManager(this);
    }

    /**
     * A method to retrieve a list containing all objects of {@code Manager} class.
     *
     * @return An ArrayList with all managers.
     */
    public static ArrayList<Manager> getManagerList() {
        return managerList;
    }

    /**
     * A method to add an instance of {@code Manager} class into the overall list
     *
     * @param manager The {@code Manager} that will be added into a list
     */
    public static void addManager(Manager manager) {
        managerList.add(manager);
    }

    /**
     * A method to print out all the information of the {@code Manager} object.
     *
     * @return A string representation of {@code Manager}
     */
    @Override
    public String toString() {
        return "Manager ID: " + super.userID + "\n" +
                "Manager Email: " + super.email + "\n" +
                "Manager Password: " + super.password + "\n" +
                "Manager Name: " + super.name;
    }
}
