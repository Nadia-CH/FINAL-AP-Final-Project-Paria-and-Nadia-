package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import model.Product;

public class AdminProductView extends JPanel {
    private Product product;
    private JLabel imageLabel;

    public AdminProductView(Product product, ActionListener editAction,
                            ActionListener viewAction, ActionListener deleteAction) {
        this.product = product;

        setLayout(new BorderLayout(10, 10));
        setPreferredSize(new Dimension(250, 320));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        setBackground(Color.WHITE);

        setupUI(editAction, viewAction, deleteAction);
        addMouseEffects();
    }

    private void setupUI(ActionListener editAction, ActionListener viewAction,
                         ActionListener deleteAction) {
        // 1. تصویر محصول (بخش بالا)
        add(createImagePanel(), BorderLayout.NORTH);

        // 2. اطلاعات محصول (بخش وسط)
        add(createInfoPanel(), BorderLayout.CENTER);

        // 3. دکمه‌های اکشن (بخش پایین)
        add(createButtonPanel(editAction, viewAction, deleteAction), BorderLayout.SOUTH);
    }

    private JPanel createImagePanel() {
        JPanel imagePanel = new JPanel(new BorderLayout());
        imagePanel.setBackground(new Color(250, 250, 250));
        imagePanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(230, 230, 230)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        imagePanel.setPreferredSize(new Dimension(0, 120));

        // اگر تصویر واقعی داری:
        // imageLabel = new JLabel(loadProductImage());

        // نسخه فعلی با emoji/placeholder
        JLabel placeholder = new JLabel(getProductEmoji());
        placeholder.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 48));
        placeholder.setHorizontalAlignment(SwingConstants.CENTER);
        placeholder.setVerticalAlignment(SwingConstants.CENTER);

        JLabel imageText = new JLabel("Click to upload image", SwingConstants.CENTER);
        imageText.setFont(new Font("Arial", Font.PLAIN, 10));
        imageText.setForeground(Color.GRAY);

        imagePanel.add(placeholder, BorderLayout.CENTER);
        imagePanel.add(imageText, BorderLayout.SOUTH);

        return imagePanel;
    }

    private String getProductEmoji() {
        // بر اساس category محصول emoji مناسب
        String category = product.getCategory().toLowerCase();
        if (category.contains("toy") || category.contains("game")) {
            return "🧸";
        } else if (category.contains("furniture") || category.contains("chair") || category.contains("table")) {
            return "🪑";
        } else if (category.contains("electronic") || category.contains("tech")) {
            return "💻";
        } else if (category.contains("book") || category.contains("education")) {
            return "📚";
        } else if (category.contains("food") || category.contains("drink")) {
            return "🍕";
        }
        return "📦"; // پیش‌فرض
    }

    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        infoPanel.setBackground(Color.WHITE);
        infoPanel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));

        // نام محصول
        JLabel nameLabel = new JLabel(product.getName(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        nameLabel.setForeground(new Color(44, 1, 190));

        // دسته‌بندی
        JLabel categoryLabel = new JLabel("📁 " + product.getCategory(), SwingConstants.CENTER);
        categoryLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        categoryLabel.setForeground(Color.DARK_GRAY);

        // قیمت
        JLabel priceLabel = new JLabel(String.format("💰 $%.2f", product.getPrice()), SwingConstants.CENTER);
        priceLabel.setFont(new Font("Arial", Font.BOLD, 14));
        priceLabel.setForeground(new Color(46, 204, 113));

        // موجودی
        JLabel stockLabel = new JLabel("📦 Stock: " + product.getStockQuantity(), SwingConstants.CENTER);
        stockLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        stockLabel.setForeground(product.getStockQuantity() > 10 ? Color.BLACK : Color.RED);

        infoPanel.add(nameLabel);
        infoPanel.add(categoryLabel);
        infoPanel.add(priceLabel);
        infoPanel.add(stockLabel);

        return infoPanel;
    }

    private JPanel createButtonPanel(ActionListener editAction, ActionListener viewAction,
                                     ActionListener deleteAction) {
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 5, 0));
        buttonPanel.setBackground(Color.WHITE);

        // دکمه Edit
        JButton editBtn = createActionButton("✏️ Edit", new Color(52, 152, 219));
        editBtn.addActionListener(editAction);
        editBtn.addActionListener(e -> {
            // product object رو منتقل کن
            editBtn.putClientProperty("product", product);
        });

        // دکمه View Details
        JButton viewBtn = createActionButton("👁️ View", new Color(155, 89, 182));
        viewBtn.addActionListener(viewAction);
        viewBtn.addActionListener(e -> {
            viewBtn.putClientProperty("product", product);
        });

        // دکمه Delete
        JButton deleteBtn = createActionButton("🗑️ Delete", new Color(231, 76, 60));
        deleteBtn.addActionListener(deleteAction);
        deleteBtn.addActionListener(e -> {
            deleteBtn.putClientProperty("product", product);
        });

        buttonPanel.add(editBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(deleteBtn);

        return buttonPanel;
    }

    private JButton createActionButton(String text, Color color) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                if (getModel().isPressed()) {
                    g2.setColor(color.darker());
                } else if (getModel().isRollover()) {
                    g2.setColor(color.brighter());
                } else {
                    g2.setColor(color);
                }

                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 8, 8);
                super.paintComponent(g);
                g2.dispose();
            }
        };

        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 11));
        button.setBorder(BorderFactory.createEmptyBorder(8, 5, 8, 5));
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setOpaque(false);

        return button;
    }

    private void addMouseEffects() {
        addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(52, 152, 219), 2),
                        BorderFactory.createEmptyBorder(14, 14, 14, 14)
                ));
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(220, 220, 220), 1),
                        BorderFactory.createEmptyBorder(15, 15, 15, 15)
                ));
                setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
    }

    // Getter برای product
    public Product getProduct() {
        return product;
    }
}