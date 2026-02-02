import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HomePage extends JFrame {

    private JLabel sliderLabel;
    private String[] imagePaths = {
            "C:\\Users\\DELL\\IdeaProjects\\CoffeeShop\\coffee3.jpeg",
            "C:\\Users\\DELL\\IdeaProjects\\CoffeeShop\\coffee4.jpeg",
            "C:\\Users\\DELL\\IdeaProjects\\CoffeeShop\\coffee5.jpeg"
    };
    private int currentImage = 0;
    private Timer sliderTimer;

    public HomePage() {
        setTitle("☕ Java Coffee Shop - Home");
        setSize(950, 680);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // NAVBAR
        JPanel navbarPanel = new JPanel();
        navbarPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        navbarPanel.setBackground(new Color(33, 24, 15));

        String[] navItems = {
                "Home", "Menu", "Order", "Cart",
                "Specials", "Feedback", "About Us", "Contact"
        };

        for (String item : navItems) {
            JButton btn = new JButton(item);
            styleNavbarButton(btn);
            navbarPanel.add(btn);

            // Navigation Actions
            if (item.equals("Menu")) {
                btn.addActionListener(e -> {
                    dispose();
                    new MenuPage().setVisible(true);
                });
            } else if (item.equals("Order")) {
                btn.addActionListener(e -> {
                    dispose();
                    new OrderPage().setVisible(true);
                });
            } else if (item.equals("Cart")) {
                btn.addActionListener(e -> {
                    dispose();
                    new CartPage().setVisible(true);
                });
            } else if (item.equals("Specials")) {
                btn.addActionListener(e -> {
                    dispose();
                    new SpecialsPage().setVisible(true);
                });
            } else if (item.equals("Feedback")) {
                btn.addActionListener(e -> {
                    dispose();
                    new FeedbackPage().setVisible(true);
                });
            } else if (item.equals("About Us")) {
                btn.addActionListener(e -> {
                    dispose();
                    new AboutUsPage().setVisible(true);
                });
            } else if (item.equals("Contact")) {
                btn.addActionListener(e -> {
                    dispose();
                    new ContactUsPage().setVisible(true);
                });
            } else if (item.equals("Home")) {
                btn.addActionListener(e -> {
                    dispose();
                    new HomePage().setVisible(true); // reload
                });
            }
        }

        add(navbarPanel, BorderLayout.NORTH);

        // MAIN CONTENT
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBackground(new Color(51, 34, 26));

        JLabel title = new JLabel("☕ Welcome to Coffee Shop!");
        title.setFont(new Font("Segoe UI Emoji", Font.BOLD, 34));
        title.setForeground(new Color(255, 235, 205));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(Box.createRigidArea(new Dimension(0, 30)));
        content.add(title);

        JLabel subtitle = new JLabel("Brewed with love, served with style.");
        subtitle.setFont(new Font("SansSerif", Font.ITALIC, 20));
        subtitle.setForeground(new Color(255, 204, 153));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(Box.createRigidArea(new Dimension(0, 10)));
        content.add(subtitle);

        // IMAGE SLIDER
        content.add(Box.createRigidArea(new Dimension(0, 30)));
        sliderLabel = new JLabel();
        sliderLabel.setHorizontalAlignment(JLabel.CENTER);
        sliderLabel.setPreferredSize(new Dimension(600, 350));
        sliderLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(sliderLabel);
        updateSliderImage();

        // FOOTER
        JLabel footer = new JLabel("© 2025 Java Coffee Shop • Designed with ☕", JLabel.CENTER);
        footer.setFont(new Font("SansSerif", Font.PLAIN, 14));
        footer.setForeground(new Color(200, 180, 160));
        footer.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        footer.setBackground(new Color(33, 24, 15));
        footer.setOpaque(true);

        add(content, BorderLayout.CENTER);
        add(footer, BorderLayout.SOUTH);

        startSlider();
    }

    private void updateSliderImage() {
        try {
            ImageIcon icon = new ImageIcon(imagePaths[currentImage]);
            Image img = icon.getImage();

            int targetWidth = 600;
            int targetHeight = 350;

            double aspectRatio = (double) img.getWidth(null) / img.getHeight(null);
            int newWidth = targetWidth;
            int newHeight = (int) (targetWidth / aspectRatio);

            if (newHeight > targetHeight) {
                newHeight = targetHeight;
                newWidth = (int) (targetHeight * aspectRatio);
            }

            Image scaled = img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            sliderLabel.setIcon(new ImageIcon(scaled));
            sliderLabel.setText("");
        } catch (Exception e) {
            sliderLabel.setText("⚠ Image not found: " + imagePaths[currentImage]);
            sliderLabel.setIcon(null);
            sliderLabel.setForeground(Color.RED);
        }
    }

    private void startSlider() {
        sliderTimer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                currentImage = (currentImage + 1) % imagePaths.length;
                updateSliderImage();
            }
        });
        sliderTimer.start();
    }

    private void styleNavbarButton(JButton btn) {
        btn.setFocusPainted(false);
        btn.setBackground(new Color(66, 39, 20));
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 12));
        btn.setPreferredSize(new Dimension(90, 30));
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setBorder(BorderFactory.createLineBorder(new Color(102, 68, 40)));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HomePage().setVisible(true));
    }
}
