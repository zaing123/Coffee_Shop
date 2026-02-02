import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {

    private JTextField emailField;
    private JPasswordField passwordField;

    public LoginPage() {
        setTitle("Coffee Shop - Login");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Main panel with split layout
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        // Left panel with coffee image
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(new ImageIcon("C:\\Users\\DELL\\IdeaProjects\\CoffeeShop\\coffee2.jpeg")); // ← Change image path
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);
        imageLabel.setOpaque(true);
        imageLabel.setBackground(Color.BLACK); // fallback background
        mainPanel.add(imageLabel);

        // Right panel (form)
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(232, 215, 182)); // Latte beige
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(50, 40, 50, 40));

        // Title
        JLabel title = new JLabel("☕ Login to Coffee Shop");
        title.setFont(new Font("Segoe UI Emoji", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(new Color(78, 52, 46));
        formPanel.add(title);
        formPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        // Email field
        emailField = new JTextField();
        formPanel.add(createLabeledField("Email Address", emailField));
        formPanel.add(Box.createRigidArea(new Dimension(0, 20)));

        // Password field
        passwordField = new JPasswordField();
        formPanel.add(createLabeledPassword("Password", passwordField));
        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        // Login Button
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(102, 51, 0));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginButton.setFocusPainted(false);
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        loginButton.addActionListener(e -> {
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter both email and password ☕");
            } else {
                JOptionPane.showMessageDialog(this, "Login Successful!");
                this.dispose();
                new HomePage().setVisible(true); // Replace with your HomePage
            }
        });

        formPanel.add(loginButton);

        mainPanel.add(formPanel);
        setContentPane(mainPanel);
    }

    private JPanel createLabeledField(String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Georgia", Font.BOLD, 14));
        label.setForeground(new Color(78, 52, 46));

        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        styleField(field);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(field);

        return panel;
    }

    private JPanel createLabeledPassword(String labelText, JPasswordField field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Georgia", Font.BOLD, 14));
        label.setForeground(new Color(78, 52, 46));

        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        styleField(field);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(field);

        return panel;
    }

    private void styleField(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 15));
        field.setForeground(new Color(78, 52, 46));
        field.setBackground(Color.WHITE);
        field.setCaretColor(new Color(78, 52, 46));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(111, 78, 55), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPage().setVisible(true));
    }
}
