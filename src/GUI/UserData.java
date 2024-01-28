package GUI;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// UserData class manages user data, including loading, saving, and user-related operations
public class UserData {
    private static ArrayList<User> users = new ArrayList<>();

    static {
        loadUsersFromFile();
    }

    // Load user data from the "user.txt" file
    private static void loadUsersFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("user.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String username = parts[0].trim();
                    String password = parts[1].trim();
                    double userCount = Double.parseDouble(parts[2].trim());

                    User user = new User(username, password, userCount);
//                    user.setDiscount(discount);
                    users.add(user);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No user data found. Starting with an empty user list.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save user data to the "user.txt" file
    static void saveUsersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("user.txt"))) {
            for (User user : users) {
                String line = user.getUsername() + "," + user.getPassword() + "," + user.getUserCount();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Add a new user to the user list and save to the file
    public static void addUser(User user) {
        users.add(user);
        saveUsersToFile();
    }

    // Validate a user based on the provided username and password
    public static User getUser(String username, String password) {
        for (User user : users) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    // Check if the provided username is available for registration
    public boolean registrationValidation(String input) {
        boolean ok = true; // Assume the input is valid until proven otherwise
        for (User user : users) {
            if (input.equals(user.getUsername())) {
                ok = false;
                break;
            }
        }
        return ok;
    }


}


