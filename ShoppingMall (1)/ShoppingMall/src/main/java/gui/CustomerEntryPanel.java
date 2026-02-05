package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// changes : search bar
//buttons placement
//scroll bar
public class CustomerEntryPanel extends JPanel implements ActionListener {


    public CustomerEntryPanel() {
        setLayout(new GridBagLayout());
        initializeUI();
    }

    private void initializeUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 10, 5, 10);

        // 1. Title Panel (Row 0, spans 2 columns)
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 8;
        gbc.gridheight = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        add(createTitlePanel(), gbc);



        // 2. Sorting Bar (Row 1, Column 1 - right side)
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.weightx = 0.3;
        gbc.weighty = 0.05;
        add(createSortingPanel(), gbc);

        // search bar
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.weightx = 0.3;
        gbc.weighty = 0.05;
        add(new JTextField(""), gbc);


        // 3. Product Scroll Bar (Row 2, Column 0 - left side)
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 4;// Span 2 rows for product list
        gbc.weightx = 0.7;
        gbc.weighty = 0.9;
        add(createProductScrollPanel(), gbc);

        // 5. Logout Button (Row 3, Column 1 - right side)
        gbc.gridx = 7;
        gbc.gridy = 6;
        gbc.gridwidth = 1;
        gbc.weighty = 0.0;
        add(createLogoutPanel(), gbc);

        // shopping cart button
        gbc.gridx = 4;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.weighty = 0.0;
        add(new JButton("shopping cart"), gbc);
    }

    private JPanel createTitlePanel() {
        // this syntax creates anonymous inner class
        // we override the paintComponent from JPanel
        JPanel panel = getTitlePanel();

        JLabel titleLabel = new JLabel("Furniture and Toy store");

        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);


        panel.add(titleLabel, BorderLayout.CENTER);

        return panel;
    }
    private JPanel getTitlePanel() {
        JPanel panel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30); // 30px corner radius
                g2.dispose();
            }
        };
        panel.setBackground(new Color(250, 150, 200));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        return panel;
    }


    private JPanel createSortingPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                "Sort Options"
        ));

        JLabel sortLabel = new JLabel("Sort by:");
        sortLabel.setFont(new Font("Arial", Font.BOLD, 12));

        String[] sortOptions = {
                "Default (Time Added)",
                "Price (High to Low)",
                "Stock Quantity"
        };

        JComboBox<String> sortCombo = new JComboBox<>(sortOptions);
        sortCombo.setPreferredSize(new Dimension(180, 25));

        panel.add(sortLabel);
        panel.add(sortCombo);

        return panel;
    }

    private JPanel createProductScrollPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                "Products"
        ));

        // Create sample product list
        DefaultListModel<String> productListModel = new DefaultListModel<>();
        productListModel.addElement("☕ Coffee - $300 - Stock: 18");
        productListModel.addElement("☕ Coffee Ground - $100 - Stock: 199");
        productListModel.addElement("🌱 Coffee Beans - $250 - Stock: 7");
        productListModel.addElement("🍵 Green Tea - $150 - Stock: 42");
        productListModel.addElement("🍫 Chocolate - $200 - Stock: 25");
        productListModel.addElement("🍪 Cookies - $80 - Stock: 56");
        productListModel.addElement("🥤 Juice - $120 - Stock: 34");
        productListModel.addElement("🍰 Cake - $350 - Stock: 8");

        JList<String> productList = new JList<>(productListModel);
        productList.setFont(new Font("Monospaced", Font.PLAIN, 14));
        productList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(productList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createAddProductPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.weightx = 1.0;

        JButton addButton = new JButton("Add Product");
        addButton.setFont(new Font("Arial", Font.BOLD, 14));
        addButton.setBackground(new Color(46, 204, 113));
        addButton.setForeground(Color.WHITE);
        addButton.setPreferredSize(new Dimension(200, 40));
        addButton.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(addButton, gbc);

        return panel;
    }

    private JPanel createLogoutPanel() {
        JPanel panel = new JPanel(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 0, 10);
        gbc.weightx = 1.0;

        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 14));
        logoutButton.setBackground(new Color(231, 76, 60));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.setPreferredSize(new Dimension(200, 40));
        logoutButton.setFocusPainted(false);

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(logoutButton, gbc);

        return panel;
    }

    // Test method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Admin Panel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 700);

            CustomerEntryPanel panel = new CustomerEntryPanel();
            frame.add(panel);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == "Logout") {

        }

    }
}