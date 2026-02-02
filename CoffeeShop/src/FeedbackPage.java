import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class FeedbackPage extends JFrame {

    public FeedbackPage() {
        setTitle("☕ Feedback - Java Coffee Shop");
        setSize(700, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Use your background image path here
        BackgroundPanel background = new BackgroundPanel("C:\\Users\\DELL\\IdeaProjects\\CoffeeShop\\coffee.jpeg");
        background.setLayout(new BorderLayout());

        // Header with emoji font
        JLabel header = new JLabel("☕ We Value Your Feedback", JLabel.CENTER);
        header.setFont(new Font("Segoe UI Emoji", Font.BOLD, 32));
        header.setForeground(Color.WHITE);
        header.setBorder(BorderFactory.createEmptyBorder(30, 10, 20, 10));
        background.add(header, BorderLayout.NORTH);

        // Form Panel
        JPanel formPanel = new JPanel();
        formPanel.setOpaque(false);
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 120, 30, 120));

        JTextField nameField = createTextField("Your Name");
        JTextField emailField = createTextField("Your Email");
        JTextArea feedbackArea = createTextArea("Write your feedback here...");

        JButton submitBtn = new JButton("Send Feedback");
        styleButton(submitBtn);

        formPanel.add(nameField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(emailField);
        formPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        formPanel.add(new JScrollPane(feedbackArea));
        formPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        formPanel.add(submitBtn);

        background.add(formPanel, BorderLayout.CENTER);

        // Back Button
        JButton backBtn = new JButton("⬅ Back to Home");
        styleButton(backBtn);
        backBtn.setFont(new Font("SansSerif", Font.PLAIN, 15));
        backBtn.setBackground(new Color(160, 110, 90));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setOpaque(false);
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));
        bottomPanel.add(backBtn);
        background.add(bottomPanel, BorderLayout.SOUTH);

        // Action Listeners
        submitBtn.addActionListener(e -> {
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String feedback = feedbackArea.getText().trim();

            if (name.isEmpty() || email.isEmpty() || feedback.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all fields.", "⚠️ Incomplete", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Thank you for your feedback, " + name + "!", "✔️ Submitted", JOptionPane.INFORMATION_MESSAGE);
                nameField.setText("");
                emailField.setText("");
                feedbackArea.setText("");
            }
        });

        backBtn.addActionListener(e -> {
            dispose();
            new HomePage().setVisible(true);
        });

        setContentPane(background);
    }

    // Reusable styled text field
    private JTextField createTextField(String placeholder) {
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));
        field.setFont(new Font("SansSerif", Font.PLAIN, 16));
        field.setBorder(BorderFactory.createTitledBorder(placeholder));
        field.setBackground(new Color(255, 255, 255, 210)); // semi-transparent
        return field;
    }

    // Reusable styled text area
    private JTextArea createTextArea(String placeholder) {
        JTextArea area = new JTextArea(6, 20);
        area.setFont(new Font("SansSerif", Font.PLAIN, 16));
        area.setWrapStyleWord(true);
        area.setLineWrap(true);
        area.setBorder(BorderFactory.createTitledBorder(placeholder));
        area.setBackground(new Color(255, 255, 255, 210)); // semi-transparent
        return area;
    }

    // Reusable styled button
    private void styleButton(JButton btn) {
        btn.setFont(new Font("SansSerif", Font.BOLD, 18));
        btn.setBackground(new Color(102, 51, 0));
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
    }

    // Background image panel
    class BackgroundPanel extends JPanel {
        private Image image;

        public BackgroundPanel(String path) {
            try {
                image = ImageIO.read(new File(path));
            } catch (IOException e) {
                System.out.println("Image load failed: " + e.getMessage());
            }
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    // Main Method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FeedbackPage().setVisible(true));
    }
}
