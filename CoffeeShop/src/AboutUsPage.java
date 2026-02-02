import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class AboutUsPage extends JFrame {

    public AboutUsPage() {
        setTitle("☕ Java Coffee Shop - About Us");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // MAIN SPLIT PANEL (LEFT IMAGE + RIGHT TEXT)
        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        // LEFT PANEL - IMAGE
        JPanel leftPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon icon = new ImageIcon("C:\\Users\\DELL\\IdeaProjects\\CoffeeShop\\coffee1.jpeg"); // Update path
                    Image img = icon.getImage();
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    g.setColor(Color.GRAY);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };

        // RIGHT PANEL - TEXT CONTENT
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(new Color(255, 248, 230)); // light cream
        rightPanel.setLayout(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        // Heading
        JLabel heading = new JLabel("About Our Coffee Shop", SwingConstants.CENTER);
        heading.setFont(new Font("Georgia", Font.BOLD, 24));
        heading.setForeground(new Color(101, 67, 33));
        heading.setBorder(BorderFactory.createEmptyBorder(10, 0, 20, 0));

        // Justified paragraph using JTextPane
        JTextPane aboutText = new JTextPane();
        aboutText.setContentType("text/html");
        aboutText.setText(
                "<html><body style='font-family: SansSerif; font-size: 14px; color: #502800; text-align: justify;'>"
                        + "<p><b>Welcome!</b><br>At <b>Java Coffee Shop</b>, we blend technology and tradition to serve the perfect cup. "
                        + "Born from a love of code and coffee, our café is a haven for developers, students, and coffee lovers alike.</p>"
                        + "<p>From bold espressos to creamy lattes, every drink we serve is crafted with care. "
                        + "We believe coffee isn’t just a beverage—it’s an experience, a pause, a recharge.</p>"
                        + "<p>Thank you for choosing us. Your support means the world, and we look forward to brewing more memories together!</p>"
                        + "</body></html>");
        aboutText.setEditable(false);
        aboutText.setOpaque(false);

        // Footer with back button
        JPanel footer = new JPanel();
        footer.setBackground(new Color(255, 248, 230));

        JButton backButton = new JButton("⬅ Back to Home");
        backButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        backButton.setBackground(new Color(102, 51, 0));
        backButton.setForeground(Color.WHITE);
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
        backButton.addActionListener((ActionEvent e) -> {
            dispose();
            new HomePage().setVisible(true);
        });

        footer.add(backButton);

        // Assemble right panel
        rightPanel.add(heading, BorderLayout.NORTH);
        rightPanel.add(aboutText, BorderLayout.CENTER);
        rightPanel.add(footer, BorderLayout.SOUTH);

        // Add panels to main
        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        add(mainPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AboutUsPage().setVisible(true));
    }
}
