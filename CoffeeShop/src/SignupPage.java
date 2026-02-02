import javax.swing.*;
import java.awt.*;

public class SignupPage extends JFrame {

    private JTextField nameField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public SignupPage() {
        setTitle("Coffee Shop - Sign Up");
        setSize(600, 750);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        BackgroundPanel background = new BackgroundPanel("C:\\Users\\DELL\\IdeaProjects\\CoffeeShop\\coffee.jpeg");
        background.setLayout(new BorderLayout());

        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 60, 40, 60));

        JLabel title = new JLabel("☕ Join Our Coffee Family", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI Emoji", Font.BOLD, 28));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(title);

        // New subtitle label
        JLabel subtitle = new JLabel("Create your Account", SwingConstants.CENTER);
        subtitle.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 18));
        subtitle.setForeground(Color.WHITE);
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(subtitle);
        formPanel.add(Box.createRigidArea(new Dimension(0, 40)));

        nameField = new JTextField();
        formPanel.add(createLabeledField("Full Name", nameField));
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        emailField = new JTextField();
        formPanel.add(createLabeledField("Email Address", emailField));
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        passwordField = new JPasswordField();
        formPanel.add(createLabeledPassword("Password", passwordField));
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));

        confirmPasswordField = new JPasswordField();
        formPanel.add(createLabeledPassword("Confirm Password", confirmPasswordField));
        formPanel.add(Box.createRigidArea(new Dimension(0, 30)));

        background.add(formPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(0, 60, 30, 60));

        JButton signupButton = new JButton("Sign Up");
        signupButton.setBackground(new Color(102, 51, 0));
        signupButton.setForeground(Color.WHITE);
        signupButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        signupButton.setFocusPainted(false);
        signupButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        signupButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        signupButton.addActionListener(e -> {
            String name = nameField.getText();
            String email = emailField.getText();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());

            if (name.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields ☕");
            } else if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(this, "Passwords do not match!");
            } else {
                JOptionPane.showMessageDialog(this, "Account Created Successfully!");
                this.dispose();
                new LoginPage().setVisible(true); // Ensure LoginPage.java exists
            }
        });

        bottomPanel.add(signupButton);
        background.add(bottomPanel, BorderLayout.SOUTH);

        setContentPane(background);
    }

    // Reusable labeled field method
    private JPanel createLabeledField(String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Georgia", Font.BOLD, 16));
        label.setForeground(new Color(245, 245, 220)); // Beige

        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        styleField(field);

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(field);

        return panel;
    }

    private JPanel createLabeledPassword(String labelText, JPasswordField field) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Georgia", Font.BOLD, 16));
        label.setForeground(new Color(245, 245, 220)); // Beige

        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        styleField(field);

        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 5)));
        panel.add(field);

        return panel;
    }

    private void styleField(JTextField field) {
        field.setFont(new Font("SansSerif", Font.PLAIN, 15));
        field.setForeground(new Color(78, 52, 46));
        field.setBackground(new Color(251, 238, 224));
        field.setCaretColor(new Color(78, 52, 46));
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(111, 78, 55), 2),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SignupPage().setVisible(true));
    }
}
