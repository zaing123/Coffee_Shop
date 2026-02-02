import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.ArrayList;

public class CartPage extends JFrame {
    private List<OrderItem> orderedItems;

    // Default constructor for fallback
    public CartPage() {
        this(new ArrayList<>());  // empty cart if none passed
    }

    // Constructor with order list
    public CartPage(List<OrderItem> orderedItems) {
        this.orderedItems = orderedItems;

        setTitle("Java Coffee Shop - Cart");
        setSize(800, 700);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // BACKGROUND PANEL WITH IMAGE
        JPanel backgroundPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    ImageIcon icon = new ImageIcon("C:\\Users\\DELL\\IdeaProjects\\CoffeeShop\\cappuccino.jpeg");
                    Image img = icon.getImage();
                    g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    g.setColor(Color.WHITE);
                    g.fillRect(0, 0, getWidth(), getHeight());
                }
            }
        };

        // HEADER
        JPanel header = new JPanel();
        header.setBackground(new Color(255, 228, 196));
        JLabel title = new JLabel("Coffee Shop");
        title.setFont(new Font("Georgia", Font.BOLD, 32));
        title.setForeground(new Color(101, 67, 33));
        header.add(title);
        backgroundPanel.add(header, BorderLayout.NORTH);

        // RECEIPT PANEL
        JPanel receiptPanel = new JPanel();
        receiptPanel.setLayout(new BoxLayout(receiptPanel, BoxLayout.Y_AXIS));
        receiptPanel.setBackground(new Color(255, 255, 255, 220));
        receiptPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));
        receiptPanel.setMaximumSize(new Dimension(450, 600));

        JLabel receiptTitle = new JLabel("Final Receipt");
        receiptTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        receiptTitle.setFont(new Font("SansSerif", Font.BOLD, 22));
        receiptTitle.setForeground(new Color(60, 30, 10));
        receiptPanel.add(receiptTitle);
        receiptPanel.add(Box.createVerticalStrut(20));

        double total = 0;
        if (orderedItems.isEmpty()) {
            JLabel emptyLabel = new JLabel("🛒 Your cart is empty.");
            emptyLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));
            emptyLabel.setForeground(new Color(80, 40, 0));
            emptyLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            receiptPanel.add(emptyLabel);
        } else {
            for (OrderItem item : orderedItems) {
                String line = item.getName() + " x" + item.getQuantity() + " = $" + String.format("%.2f", item.getPrice() * item.getQuantity());
                JLabel label = new JLabel(line);
                label.setFont(new Font("Monospaced", Font.PLAIN, 16));
                label.setForeground(new Color(80, 40, 0));
                label.setAlignmentX(Component.LEFT_ALIGNMENT);
                receiptPanel.add(label);
                total += item.getPrice() * item.getQuantity();
            }

            receiptPanel.add(Box.createVerticalStrut(15));
            JSeparator separator = new JSeparator();
            separator.setMaximumSize(new Dimension(400, 1));
            receiptPanel.add(separator);
            receiptPanel.add(Box.createVerticalStrut(10));

            JLabel totalLabel = new JLabel("Total: $" + String.format("%.2f", total));
            totalLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
            totalLabel.setForeground(new Color(102, 0, 0));
            totalLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
            receiptPanel.add(totalLabel);
        }

        receiptPanel.add(Box.createVerticalStrut(20));
        JLabel thankYou = new JLabel("Thank you for choosing us!");
        thankYou.setFont(new Font("SansSerif", Font.ITALIC, 14));
        thankYou.setForeground(new Color(80, 40, 0));
        thankYou.setAlignmentX(Component.CENTER_ALIGNMENT);
        receiptPanel.add(thankYou);

        JPanel centerWrapper = new JPanel(new GridBagLayout());
        centerWrapper.setOpaque(false);
        centerWrapper.add(receiptPanel);
        backgroundPanel.add(centerWrapper, BorderLayout.CENTER);

        // FOOTER BUTTONS
        JPanel footer = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        footer.setOpaque(false);

        JButton backToOrderBtn = new JButton("⬅ Back to Order");
        styleButton(backToOrderBtn);
        backToOrderBtn.addActionListener((ActionEvent e) -> {
            dispose();
            new OrderPage().setVisible(true);
        });

        JButton backToHomeBtn = new JButton("🏠 Back to Home");
        styleButton(backToHomeBtn);
        backToHomeBtn.addActionListener((ActionEvent e) -> {
            dispose();
            new HomePage().setVisible(true);
        });

        JButton checkoutBtn = new JButton("✅ Checkout");
        styleButton(checkoutBtn);
        checkoutBtn.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(this, "Payment successful! Your order has been placed.", "Checkout", JOptionPane.INFORMATION_MESSAGE);
        });

        footer.add(backToOrderBtn);
        footer.add(backToHomeBtn);
        footer.add(checkoutBtn);
        backgroundPanel.add(footer, BorderLayout.SOUTH);

        add(backgroundPanel);
    }

    private void styleButton(JButton btn) {
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setBackground(new Color(102, 51, 0));
        btn.setForeground(Color.WHITE);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setFocusPainted(false);
        btn.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 1));
    }

    // ✅ Test main method
    public static void main(String[] args) {
        List<OrderItem> sample = new ArrayList<>();
        sample.add(new OrderItem("Latte", 2, 4.5));
        sample.add(new OrderItem("Mocha", 1, 5.0));
        new CartPage(sample).setVisible(true);
    }
}
