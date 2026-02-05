package gui;

import com.formdev.flatlaf.FlatLightLaf; // Make sure FlatLaf is in your pom.xml
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminEntryPanel extends JPanel {

    public AdminEntryPanel() {
        // Apply FlatLaf if it hasn't been applied in Main
        FlatLightLaf.setup();

        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Overall outer padding
        initializeUI();
    }

    private void initializeUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        // 1. Header Section
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 4; gbc.weighty = 0.1;
        add(createHeaderPanel(), gbc);

        // 2. Welcome Message (Replaced TextArea with Labels)
        gbc.gridx = 2; gbc.gridy = 1;
        gbc.gridwidth = 2; gbc.weightx = 0.3; gbc.weighty = 0.1;
        add(createWelcomePanel("Admin User"), gbc);

        // 3. Product List (Left Side)
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2; gbc.gridheight = 3;
        gbc.weightx = 0.7; gbc.weighty = 0.8;
        add(createProductScrollPanel(), gbc);

        // 4. Sorting Bar (Above buttons)
        gbc.gridx = 2; gbc.gridy = 2;
        gbc.gridwidth = 2; gbc.gridheight = 1;
        gbc.weighty = 0.05;
        add(createSortingPanel(), gbc);

        // 5. Action Buttons (Bottom Right)
        gbc.gridx = 2; gbc.gridy = 3;
        gbc.gridwidth = 1; gbc.gridheight = 1;
        gbc.weighty = 0.1;
        add(createStyledButton("Add Product", new Color(46, 204, 113)), gbc);

        gbc.gridx = 3; gbc.gridy = 3;
        add(createStyledButton("Logout", new Color(231, 76, 60)), gbc);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBackground(new Color(52, 73, 94)); // Deep charcoal blue
        panel.putClientProperty("FlatLaf.style", "arc: 20"); // Rounded corners for panel

        JLabel title = new JLabel("Furniture & Toy Store");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subtitle = new JLabel("Administrative Management Console");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitle.setForeground(new Color(189, 195, 199));
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(title);
        panel.add(subtitle);
        panel.setPreferredSize(new Dimension(0, 80));
        return panel;
    }

    private JPanel createWelcomePanel(String adminName) {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setOpaque(false);

        JLabel welcomeLabel = new JLabel("Welcome back,");
        welcomeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JLabel nameLabel = new JLabel(adminName);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        nameLabel.setForeground(new Color(41, 128, 185));

        panel.add(welcomeLabel);
        panel.add(nameLabel);
        return panel;
    }

    private JPanel createProductScrollPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Inventory Overview"));

        DefaultListModel<String> model = new DefaultListModel<>();
        model.addElement(String.format("%-20s | %-10s | %-5s", "Product Name", "Price", "Stock"));
        model.addElement("---------------------------------------------------------");
        model.addElement(String.format("%-20s | $300.00    | 18", "☕ Coffee"));
        model.addElement(String.format("%-20s | $150.00    | 42", "🍵 Green Tea"));
        model.addElement(String.format("%-20s | $350.00    | 8", "🍰 Cake"));

        JList<String> list = new JList<>(model);
        list.setFont(new Font("Monospaced", Font.PLAIN, 13));

        JScrollPane scroll = new JScrollPane(list);
        scroll.setBorder(BorderFactory.createEmptyBorder());
        // Customizing the scrollbar for FlatLaf
        scroll.getVerticalScrollBar().putClientProperty("JScrollBar.showButtons", true);

        panel.add(scroll, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createSortingPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("Display Settings"));

        String[] options = {"Time Added", "Price (Asc)", "Price (Desc)", "Stock Level"};
        JComboBox<String> combo = new JComboBox<>(options);
        combo.putClientProperty("JComponent.roundRect", true); // Modern rounded combo box

        panel.add(new JLabel("Sort Inventory:"));
        panel.add(combo);
        return panel;
    }

    private JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // FlatLaf rounded button style
        btn.putClientProperty("JButton.buttonType", "roundRect");
        btn.setPreferredSize(new Dimension(140, 45));

        return btn;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Admin System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(new AdminEntryPanel());
            frame.setSize(1000, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}