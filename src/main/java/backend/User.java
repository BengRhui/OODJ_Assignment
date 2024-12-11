package backend;

import java.util.ArrayList;

public class User {

    private String userID;
    private String email;
    private String password;

    private static final ArrayList<User> userList = new ArrayList<>();

    public User(String userID, String email, String password) {
        this.userID = userID;
        this.email = email;
        this.password = password;
    }

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

    public static void addUser(User user) {
        userList.add(user);
    }

    public static ArrayList<User> getUserList() {
        return userList;
    }

    @Override
    public String toString() {
        return "User ID:" + userID + "\n" +
                "Email: " + email + "\n" +
                "Password: " + password;
    }
}
