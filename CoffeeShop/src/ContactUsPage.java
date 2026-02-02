import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ContactUsPage extends JFrame {

    public ContactUsPage() {
        setTitle("Contact Us - Java Coffee Shop");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // SPLIT: Image Left | Form Right
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        // LEFT PANEL - Background Image
        JPanel leftPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon icon = new ImageIcon("C:\\Users\\DELL\\IdeaProjects\\CoffeeShop\\coffeeshop.jpeg");
                    Image img = icon.getImage();
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };

        // RIGHT PANEL - Softer Coffee Form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));
        formPanel.setBackground(new Color(255, 244, 230)); // soft latte

        JLabel heading = new JLabel("Get in Touch");
        heading.setFont(new Font("Georgia", Font.BOLD, 26));
        heading.setForeground(new Color(101, 67, 33)); // coffee brown
        heading.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextField nameField = createField("Your Name");
        JTextField emailField = createField("Your Email");

        JTextArea messageArea = new JTextArea(5, 20);
        messageArea.setLineWrap(true);
        messageArea.setWrapStyleWord(true);
        messageArea.setFont(new Font("SansSerif", Font.PLAIN, 14));
        messageArea.setBackground(new Color(255, 250, 240)); // cream
        messageArea.setBorder(BorderFactory.createLineBorder(new Color(204, 170, 136)));

        JScrollPane scrollPane = new JScrollPane(messageArea);

        JLabel messageLabel = new JLabel("Your Message:");
        messageLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
        messageLabel.setForeground(new Color(101, 67, 33));

        JButton sendButton = new JButton("Send Message");
        sendButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sendButton.setBackground(new Color(176, 120, 80)); // cappuccino
        sendButton.setForeground(Color.WHITE);
        sendButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        sendButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        sendButton.setFocusPainted(false);

        sendButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(ContactUsPage.this,
                        "Thank you for contacting us, " + nameField.getText() + "!",
                        "Message Sent", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Add components
        formPanel.add(heading);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(nameField);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(emailField);
        formPanel.add(Box.createVerticalStrut(10));
        formPanel.add(messageLabel);
        formPanel.add(scrollPane);
        formPanel.add(Box.createVerticalStrut(20));
        formPanel.add(sendButton);

        mainPanel.add(leftPanel);
        mainPanel.add(formPanel);

        add(mainPanel);
    }

    private JTextField createField(String placeholder) {
        JTextField field = new JTextField();
        field.setMaximumSize(new Dimension(Integer.MAX_VALUE, 30));
        field.setFont(new Font("SansSerif", Font.PLAIN, 14));
        field.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(204, 170, 136)), placeholder));
        field.setBackground(new Color(255, 250, 240)); // cream
        return field;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ContactUsPage().setVisible(true));
    }
}
