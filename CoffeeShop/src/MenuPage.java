import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPage extends JFrame {

    public MenuPage() {
        setTitle("\u2615 Java Coffee Shop - Menu"); // ☕
        setSize(900, 680);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // NAVBAR TITLE
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(33, 24, 15));
        JLabel heading = new JLabel("\u2615 Our Coffee Menu");
        heading.setFont(new Font("Segoe UI Emoji", Font.BOLD, 28));
        heading.setForeground(new Color(255, 235, 205));
        topPanel.add(heading);
        add(topPanel, BorderLayout.NORTH);

        // MAIN MENU ITEMS
        JPanel menuPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        menuPanel.setBackground(new Color(51, 34, 26));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        String[] coffeeNames = {
                "Espresso", "Cappuccino", "Latte",
                "Americano", "Mocha", "Macchiato"
        };

        String[] imagePaths = {
                "C:\\Users\\DELL\\IdeaProjects\\CoffeeShop\\espresso.jpeg",
                "C:\\Users\\DELL\\IdeaProjects\\CoffeeShop\\cappuccino.jpeg",
                "C:\\Users\\DELL\\IdeaProjects\\CoffeeShop\\latte.jpeg",
                "C:\\Users\\DELL\\IdeaProjects\\CoffeeShop\\americano.jpeg",
                "C:\\Users\\DELL\\IdeaProjects\\CoffeeShop\\mocha.jpeg",
                "C:\\Users\\DELL\\IdeaProjects\\CoffeeShop\\macchiato.jpeg"
        };

        for (int i = 0; i < coffeeNames.length; i++) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBackground(new Color(66, 39, 20));
            card.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

            JLabel imageLabel;
            try {
                ImageIcon icon = new ImageIcon(imagePaths[i]);
                Image img = icon.getImage().getScaledInstance(220, 160, Image.SCALE_SMOOTH);
                imageLabel = new JLabel(new ImageIcon(img));
            } catch (Exception e) {
                imageLabel = new JLabel("Image not found");
                imageLabel.setForeground(Color.RED);
            }

            imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JLabel nameLabel = new JLabel(coffeeNames[i], JLabel.CENTER);
            nameLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            card.add(Box.createVerticalStrut(10));
            card.add(imageLabel);
            card.add(Box.createVerticalStrut(10));
            card.add(nameLabel);
            menuPanel.add(card);
        }

        add(menuPanel, BorderLayout.CENTER);

        // FOOTER with BACK & PLACED ORDER BUTTONS
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        footer.setBackground(new Color(33, 24, 15));

        // Back Button
        JButton backBtn = new JButton("⬅ Back to Home");
        backBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        backBtn.setBackground(new Color(66, 39, 20));
        backBtn.setForeground(new Color(255, 235, 205));
        backBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backBtn.setFocusPainted(false);
        backBtn.setBorder(BorderFactory.createLineBorder(new Color(102, 68, 40), 1));
        backBtn.addActionListener(e -> {
            dispose();
            new HomePage().setVisible(true);
        });

        // Placed Order Button
        JButton orderBtn = new JButton("🛒 Placed Order");
        orderBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        orderBtn.setBackground(new Color(102, 51, 0));
        orderBtn.setForeground(Color.WHITE);
        orderBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        orderBtn.setFocusPainted(false);
        orderBtn.setBorder(BorderFactory.createLineBorder(new Color(204, 153, 102), 1));
        orderBtn.addActionListener(e -> {
            dispose();
            new OrderPage().setVisible(true); // Make sure OrderPage exists
        });

        // Add both buttons to footer
        footer.add(backBtn);
        footer.add(orderBtn);

        add(footer, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MenuPage().setVisible(true));
    }
}
