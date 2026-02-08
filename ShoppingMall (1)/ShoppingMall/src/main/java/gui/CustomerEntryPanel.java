package gui;

import com.formdev.flatlaf.FlatLightLaf; // Make sure FlatLaf is in your pom.xml
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import model.*;
import model.Product;
import repository.*;
import service.*;


public class CustomerEntryPanel extends JPanel {
    private JProductRepository pr;
    private CustomerService customerService;
    private Customer customer;

    private JPanel listContainer;
    private JButton btnCart;// the shopping cart button
    private JButton btnLogout;

    public CustomerEntryPanel(JProductRepository pr, CustomerService service, Customer customer) {
        this.pr = pr;
        this.customerService = service;
        this.customer = customer;

        FlatLightLaf.setup();
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));
        initializeUI();
    }

    private void initializeUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        //Header Section
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.weighty = 0.25;
        add(createHeaderPanel(), gbc);

        //Search Bar
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.weighty = 0.1;
        add(createSearchBar(), gbc);


        //Product List (Left Side)
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 5;
        gbc.weightx = 0.6;
        gbc.weighty = 0.625;

        add(createProductScrollPanel(), gbc);

        //Sorting Bar (Above buttons)
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.weightx = 0.4;
        gbc.weighty = 0.1;
        add(createSortingPanel(), gbc);

        // Spacer
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 4;
        gbc.weighty = 1.0;
        JPanel spacer = new JPanel();
        spacer.setOpaque(false);
        add(spacer, gbc);

        //Action Buttons (Bottom Right)
        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 20;
        btnCart =createStyledButton("Shopping Cart", new Color(154, 45, 205));
        add(btnCart, gbc);

        gbc.gridx = 3;
        gbc.gridy = 7;
        btnLogout =createStyledButton("Logout", new Color(205, 202, 45));
        add(btnLogout, gbc);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBackground(new Color(2, 9, 99)); // Deep charcoal blue
        panel.putClientProperty("FlatLaf.style", "arc: 20"); // Rounded corners for panel

        JLabel title = new JLabel("Furniture & Toy Store");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);


        panel.add(title);

        panel.setPreferredSize(new Dimension(0, 80));
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
        btn.setPreferredSize(new Dimension(90, 18));

        return btn;
    }

    private JPanel createSearchBar() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("Search Bar"));
        JLabel searchLabel = new JLabel("Search Product:");
        searchLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        panel.add(searchLabel);
        JTextField searchField = new JTextField(30);
        panel.add(searchField);

        return panel;

    }

    private JComponent createProductScrollPanel() {
        listContainer = new JPanel();
        listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));

        JScrollPane scrollPane = new JScrollPane(listContainer);
        scrollPane.setViewportView(listContainer);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);

        refreshList(); // Load Data
        return scrollPane;
    }

    //
    public void refreshList() {
        listContainer.removeAll();
        for (Product p : pr.getAll()) {
            CustomerProductView card = new CustomerProductView(p);

            // --- ADD TO CART LOGIC ---
            card.getBtnAdd().addActionListener(e -> {
                customerService.addToCart(customer, p);
                JOptionPane.showMessageDialog(this, p.getName() + " added to cart!");
            });

            card.getBtnRemove().addActionListener(e -> {
                customerService.removeFromCart(customer, p);
                JOptionPane.showMessageDialog(this, p.getName() + " item removed from your cart!");

            });

            listContainer.add(card);
        }
        listContainer.revalidate();
        listContainer.repaint();
    }

   // Getters for DisplayCustomer
    public JButton getBtnCart() {
        return btnCart;
    }

    public JButton getBtnLogout() {
        return btnLogout;
    }
}
//    // Helpers
//    private JPanel createHeaderPanel() {
//        JPanel p = new JPanel(); p.add(new JLabel("Welcome to the Store")); return p;
//    }
//    private JButton createStyledButton(String text, Color bg) {
//        JButton b = new JButton(text); b.setBackground(bg); b.setForeground(Color.WHITE); return b;
//    }
//}