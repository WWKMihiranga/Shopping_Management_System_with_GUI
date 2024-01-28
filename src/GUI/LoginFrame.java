package GUI;

import Main.ShoppingManager;
import Main.WestminsterShoppingManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// LoginFrame class for handling user login
public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private WestminsterShoppingManager shoppingApp;
    // Reference to the main application class

    private static String userName1, password1;

    // Getter and setter methods
    public static String getUserName1() {
        return userName1;
    }

    public static void setUserName1(String userName1) {
        LoginFrame.userName1 = userName1;
    }

    public static String getPassword1() {
        return password1;
    }

    public static void setPassword1(String password1) {
        LoginFrame.password1 = password1;
    }

    // Constructor for LoginFrame
    public LoginFrame(WestminsterShoppingManager shoppingApp) {
        this.shoppingApp = shoppingApp;

        setTitle("Login");
        setSize(300, 150);
        setResizable(false);

        // Creating components for the login panel
        JPanel panel = new JPanel();
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        JButton loginButton = new JButton("Login");

        // ActionListener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                User user = UserData.getUser(username, password);

                // Validate user login
                if (user != null) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login successful !!");
                    userName1=username;
                    password1=password;

                    // Show the main application window and dispose of the login frame
                    shoppingApp.showMainWindow();
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid username or password. Try again.");
                }
            }
        });

        // Adding components to the panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        add(panel);
    }
}
