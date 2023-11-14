import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MainApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("User Registration");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 300);

            JPanel panel = new JPanel();
            JButton registerButton = new JButton("Register");
            JButton exitButton = new JButton("Exit");

            registerButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Call a method to handle registration
                    registerUser();
                }
            });

            exitButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Close the application
                    System.exit(0);
                }
            });

            panel.add(registerButton);
            panel.add(exitButton);
            frame.add(panel);
            frame.setVisible(true);
        });
    }

    private static void registerUser() {
        // Display a form or dialog to collect user details
        // For simplicity, you can use JOptionPane for input
        String id = JOptionPane.showInputDialog("Enter ID:");
        String name = JOptionPane.showInputDialog("Enter Name:");
        String gender = JOptionPane.showInputDialog("Enter Gender (Male/Female):");
        String address = JOptionPane.showInputDialog("Enter Address:");
        String contact = JOptionPane.showInputDialog("Enter Contact:");

        // Call a method to store the user details in the database
        storeInDatabase(id, name, gender, address, contact);
    }

    private static void storeInDatabase(String id, String name, String gender, String address, String contact) {
        // JDBC connection and database insertion code
        String url = "jdbc:sqlite:userdata.db";

        try (Connection connection = DriverManager.getConnection(url)) {
            String query = "INSERT INTO users (id, name, gender, address, contact) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, id);
                statement.setString(2, name);
                statement.setString(3, gender);
                statement.setString(4, address);
                statement.setString(5, contact);

                statement.executeUpdate();
                JOptionPane.showMessageDialog(null, "User registered successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
