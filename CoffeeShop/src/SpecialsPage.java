import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class SpecialsPage extends JFrame {

    private List<OrderItem> cartItems = new ArrayList<>();

    public SpecialsPage() {
        setTitle("☕ Java Coffee Shop - Specials");
        setSize(900, 650);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(33, 24, 15));
        JLabel heading = new JLabel("🌟 Today's Specials");
        heading.setFont(new Font("Segoe UI Emoji", Font.BOLD, 28));
        heading.setForeground(new Color(255, 235, 205));
        topPanel.add(heading);
        add(topPanel, BorderLayout.NORTH);

        // Specials content panel
        JPanel specialsPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        specialsPanel.setBackground(new Color(51, 34, 26));
        specialsPanel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));

        String[] names = {
                "Vanilla Cold Brew",
                "Pumpkin Spice Latte",
                "Salted Caramel Mocha",
                "Double Shot Espresso"
        };

        String[] desc = {
                "Refreshing cold brew with sweet vanilla syrup.",
                "Autumn favorite with pumpkin, spices, and milk.",
                "Rich mocha with a swirl of salted caramel.",
                "Extra strong espresso to wake you up!"
        };

        String[] imagePaths = {
                "C:\\Users\\DELL\\IdeaProjects\\CoffeeShop\\vanilla.jpeg",
                "C:\\Users\\DELL\\IdeaProjects\\CoffeeShop\\pumpkin.jpeg",
                "C:\\Users\\DELL\\IdeaProjects\\CoffeeShop\\caramel.jpeg",
                "C:\\Users\\DELL\\IdeaProjects\\CoffeeShop\\espresso.jpeg"
        };

        double[] prices = {3.99, 4.75, 5.25, 3.25};

        for (int i = 0; i < names.length; i++) {
            JPanel card = new JPanel();
            card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
            card.setBackground(new Color(66, 39, 20));
            card.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            card.setPreferredSize(new Dimension(220, 330)); // Increased height to fit all content

// Image
            JLabel imgLabel;
            try {
                ImageIcon icon = new ImageIcon(imagePaths[i]);
                Image img = icon.getImage().getScaledInstance(160, 90, Image.SCALE_SMOOTH); // Reduced image height
                imgLabel = new JLabel(new ImageIcon(img));
            } catch (Exception e) {
                imgLabel = new JLabel("Image Not Found");
                imgLabel.setForeground(Color.RED);
            }
            imgLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

// Name
            JLabel nameLabel = new JLabel(names[i]);
            nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
            nameLabel.setForeground(Color.WHITE);
            nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

// Price
            JLabel priceLabel = new JLabel("Special: $" + prices[i]);
            priceLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
            priceLabel.setForeground(Color.YELLOW);
            priceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

// Centered description
            JLabel descLabel = new JLabel(desc[i], SwingConstants.CENTER);
            descLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
            descLabel.setForeground(new Color(230, 230, 230));
            descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            descLabel.setFont(new Font("SansSerif", Font.ITALIC, 12));
            descLabel.setForeground(new Color(230, 230, 230));
            descLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

// Select button
            JButton orderBtn = new JButton("Select");
            orderBtn.setBackground(new Color(153, 102, 51));
            orderBtn.setForeground(Color.WHITE);
            orderBtn.setFont(new Font("SansSerif", Font.BOLD, 13));
            orderBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
            orderBtn.setMaximumSize(new Dimension(100, 30));

            int index = i;
            orderBtn.addActionListener(e -> {
                OrderItem item = new OrderItem(names[index], 1, prices[index]);
                cartItems.add(item);
                JOptionPane.showMessageDialog(this,
                        item.getName() + " added to your cart!",
                        "Item Selected",
                        JOptionPane.INFORMATION_MESSAGE);
            });

// Add all components with spacing
            card.add(Box.createVerticalStrut(10));
            card.add(imgLabel);
            card.add(Box.createVerticalStrut(8));
            card.add(nameLabel);
            card.add(Box.createVerticalStrut(4));
            card.add(priceLabel);
            card.add(Box.createVerticalStrut(4));
            card.add(descLabel);
            card.add(Box.createVerticalStrut(10));
            card.add(orderBtn);
            card.add(Box.createVerticalStrut(10)); // Ensure spacing below button

            specialsPanel.add(card);

        }

        add(specialsPanel, BorderLayout.CENTER);

        // Footer
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 15));
        footer.setBackground(new Color(33, 24, 15));

        JButton backBtn = new JButton("⬅ Back to Menu");
        styleButton(backBtn);
        backBtn.addActionListener(e -> {
            dispose();
            new MenuPage().setVisible(true);
        });

        JButton checkoutBtn = new JButton("🧾 Checkout");
        styleButton(checkoutBtn);
        checkoutBtn.addActionListener((ActionEvent e) -> {
            dispose();
            new CartPage(cartItems).setVisible(true);
        });

        footer.add(backBtn);
        footer.add(checkoutBtn);
        add(footer, BorderLayout.SOUTH);
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setBackground(new Color(102, 51, 0));
        btn.setForeground(Color.WHITE);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SpecialsPage().setVisible(true));
    }
}
