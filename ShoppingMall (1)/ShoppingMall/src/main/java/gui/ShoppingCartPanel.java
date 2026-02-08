package gui;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import model.*;
import service.*;

public class ShoppingCartPanel extends JPanel {

    private CustomerService customerService;
    private Customer customer;

    private JPanel listContainer;
    private JLabel lblTotal;
    private JButton btnBack;

    public ShoppingCartPanel(CustomerService customerService, Customer customer) {
        this.customerService = customerService;
        this.customer = customer;

        FlatLightLaf.setup();
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Header with Back Button
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel header = new JLabel("Your Shopping Cart", SwingConstants.CENTER);
        header.setFont(new Font("SansSerif", Font.BOLD, 24));

        btnBack = new JButton("← Back to Shop");

        headerPanel.add(btnBack, BorderLayout.WEST);
        headerPanel.add(header, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // List
        listContainer = new JPanel();
        listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));
        JScrollPane scrollPane = new JScrollPane(listContainer);
        add(scrollPane, BorderLayout.CENTER);

        // Footer
        lblTotal = new JLabel("Total: $0.00");
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 18));
        add(lblTotal, BorderLayout.SOUTH);
    }

    public void refreshCart() {
        listContainer.removeAll();
        Cart cart = customerService.getShoppingCart(customer.getId());

        if (cart.getCartItems().isEmpty()) {
            listContainer.add(new JLabel("Cart is empty"));
            lblTotal.setText("Total: $0.00");
        } else {
            // Group items (Product -> Count)
            Map<Product, Integer> counts = new HashMap<>();
            for (Product p : cart.getCartItems()) {
                counts.put(p, counts.getOrDefault(p, 0) + 1);
            }

            for (Map.Entry<Product, Integer> entry : counts.entrySet()) {
                Product p = entry.getKey();
                int qty = entry.getValue();

                // Create Row
                JPanel row = new JPanel(new BorderLayout());
                row.setBorder(BorderFactory.createMatteBorder(0,0,1,0,Color.LIGHT_GRAY));
                row.add(new JLabel(p.getName() + " (x" + qty + ")"), BorderLayout.WEST);

                JButton btnRemove = new JButton("Remove One");
                btnRemove.setForeground(Color.RED);
                btnRemove.addActionListener(e -> {
                    customerService.removeFromCart(customer.getId(), p);
                    refreshCart(); // Refresh UI immediately
                });

                row.add(btnRemove, BorderLayout.EAST);
                listContainer.add(row);
            }
            lblTotal.setText("Total: $" + cart.getItemsTotalPrice());
        }
        listContainer.revalidate();
        listContainer.repaint();
    }

    public JButton getBtnBack() { return btnBack; }
}