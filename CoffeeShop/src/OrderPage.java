import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class OrderPage extends JFrame {

    private JPanel orderSummaryPanel;
    private List<OrderItem> orderedItems = new ArrayList<>();
    private String[] coffeeNames = {
            "Espresso", "Cappuccino", "Latte",
            "Americano", "Mocha", "Macchiato"
    };
    private String[] imagePaths = {
            "C:\\Users\\DELL\\Downloads\\espresso.jpeg",
            "C:\\Users\\DELL\\Downloads\\cappuccino.jpeg",
            "C:\\Users\\DELL\\Downloads\\latte.jpeg",
            "C:\\Users\\DELL\\Downloads\\americano.jpeg",
            "C:\\Users\\DELL\\Downloads\\mocha.jpeg",
            "C:\\Users\\DELL\\Downloads\\macchiato.jpeg"
    };
    private double[] prices = {3.0, 4.0, 4.5, 3.5, 5.0, 4.8};

    public OrderPage() {
        setTitle("☕ Java Coffee Shop - Order");
        setSize(1000, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // NAVBAR
        JPanel navbar = new JPanel(new FlowLayout(FlowLayout.CENTER));
        navbar.setBackground(new Color(33, 24, 15));
        JLabel title = new JLabel("☕ Place Your Order");
        title.setFont(new Font("Segoe UI Emoji", Font.BOLD, 26));
        title.setForeground(Color.WHITE);
        navbar.add(title);
        add(navbar, BorderLayout.NORTH);

        // MAIN PANEL
        JPanel mainPanel = new JPanel(new BorderLayout());

        // MENU ITEMS PANEL
        JPanel menuPanel = new JPanel(new GridLayout(2, 3, 20, 20));
        menuPanel.setBackground(new Color(51, 34, 26));
        menuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (int i = 0; i < coffeeNames.length; i++) {
            JPanel card = createCoffeeCard(i);
            menuPanel.add(card);
        }

        mainPanel.add(menuPanel, BorderLayout.CENTER);

        // ORDER SUMMARY PANEL (Right)
        orderSummaryPanel = new JPanel();
        orderSummaryPanel.setLayout(new BoxLayout(orderSummaryPanel, BoxLayout.Y_AXIS));
        orderSummaryPanel.setPreferredSize(new Dimension(250, 0));
        orderSummaryPanel.setBackground(new Color(66, 39, 20));
        orderSummaryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Title with emoji
        JLabel orderLabel = new JLabel("🧾 Order Summary");
        orderLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        orderLabel.setForeground(Color.WHITE);
        orderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        orderSummaryPanel.add(orderLabel);
        orderSummaryPanel.add(Box.createVerticalStrut(10));

        // Placeholder for dynamic content
        mainPanel.add(orderSummaryPanel, BorderLayout.EAST);
        add(mainPanel, BorderLayout.CENTER);

        // Add checkout button at the bottom of orderSummaryPanel
        orderSummaryPanel.add(Box.createVerticalGlue());
        JButton cartBtn = new JButton("🛒 Checkout");
        cartBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        cartBtn.setBackground(new Color(204, 102, 0));
        cartBtn.setForeground(Color.WHITE);
        cartBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cartBtn.setFocusPainted(false);
        cartBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        cartBtn.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
        cartBtn.addActionListener(e -> {
            dispose();
            new CartPage(orderedItems).setVisible(true);
        });
        orderSummaryPanel.add(cartBtn);
        orderSummaryPanel.add(Box.createVerticalStrut(10));
    }

    private JPanel createCoffeeCard(int index) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(new Color(66, 39, 20));
        card.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));

        JLabel imageLabel;
        try {
            ImageIcon icon = new ImageIcon(imagePaths[index]);
            Image img = icon.getImage().getScaledInstance(200, 140, Image.SCALE_SMOOTH);
            imageLabel = new JLabel(new ImageIcon(img));
        } catch (Exception e) {
            imageLabel = new JLabel("Image not found");
            imageLabel.setForeground(Color.RED);
        }

        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel nameLabel = new JLabel(coffeeNames[index], JLabel.CENTER);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton orderBtn = new JButton("Add $" + prices[index]);
        orderBtn.setBackground(new Color(153, 76, 0));
        orderBtn.setForeground(Color.WHITE);
        orderBtn.setFont(new Font("SansSerif", Font.BOLD, 12));
        orderBtn.setFocusPainted(false);
        orderBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        orderBtn.setAlignmentX(Component.CENTER_ALIGNMENT);

        int coffeeIndex = index;
        orderBtn.addActionListener(e -> {
            addToOrder(coffeeNames[coffeeIndex], prices[coffeeIndex]);
        });

        card.add(Box.createVerticalStrut(10));
        card.add(imageLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(nameLabel);
        card.add(Box.createVerticalStrut(10));
        card.add(orderBtn);

        return card;
    }

    private void addToOrder(String name, double price) {
        boolean found = false;
        for (OrderItem item : orderedItems) {
            if (item.getName().equals(name)) {
                item.setQuantity(item.getQuantity() + 1);
                found = true;
                break;
            }
        }
        if (!found) {
            orderedItems.add(new OrderItem(name, 1, price));
        }
        refreshOrderSummary();
    }

    private void refreshOrderSummary() {
        // Remove all except the first two components (title + spacing)
        Component[] components = orderSummaryPanel.getComponents();
        orderSummaryPanel.removeAll();

        JLabel orderLabel = new JLabel("🧾 Order Summary");
        orderLabel.setFont(new Font("Segoe UI Emoji", Font.BOLD, 16));
        orderLabel.setForeground(Color.WHITE);
        orderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        orderSummaryPanel.add(orderLabel);
        orderSummaryPanel.add(Box.createVerticalStrut(10));

        for (OrderItem item : orderedItems) {
            JLabel itemLabel = new JLabel(item.getName() + " x" + item.getQuantity());
            itemLabel.setForeground(Color.WHITE);
            itemLabel.setFont(new Font("SansSerif", Font.PLAIN, 14));
            itemLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            orderSummaryPanel.add(itemLabel);
        }

        orderSummaryPanel.add(Box.createVerticalGlue());

        JButton cartBtn = new JButton("🛒 Checkout");
        cartBtn.setFont(new Font("SansSerif", Font.BOLD, 14));
        cartBtn.setBackground(new Color(204, 102, 0));
        cartBtn.setForeground(Color.WHITE);
        cartBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        cartBtn.setFocusPainted(false);
        cartBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        cartBtn.setBorder(BorderFactory.createLineBorder(Color.ORANGE, 1));
        cartBtn.addActionListener(e -> {
            dispose();
            new CartPage(orderedItems).setVisible(true);
        });

        orderSummaryPanel.add(cartBtn);
        orderSummaryPanel.add(Box.createVerticalStrut(10));

        orderSummaryPanel.revalidate();
        orderSummaryPanel.repaint();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new OrderPage().setVisible(true));
    }
}
