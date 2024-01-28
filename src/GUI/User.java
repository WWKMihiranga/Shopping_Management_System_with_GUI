package GUI;

import java.io.Serializable;

public class User implements Serializable {

    // User attributes: username, password, and userCount (for discount tracking)
    private String username;
    private String password;
    private double userCount;

    // Constructor to initialize a User object
    public User(String username, String password, double userCount) {
        this.username = username;
        this.password = password;
        this.userCount = userCount; // Initial discount is 0%
    }

    // Getter methods and Setter methods
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public double getUserCount() {return userCount;}

    public void setUserCount(int userCount) {this.userCount = userCount;}
}
