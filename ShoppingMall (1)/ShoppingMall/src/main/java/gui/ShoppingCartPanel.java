package gui;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import model.*;
import repository.JCartRepository;
import repository.JCustomerRepository;
import repository.JProductRepository;
import service.*;

public class ShoppingCartPanel extends JPanel {

    private CustomerService customerService;
    private Customer customer;
    private Cart cart;

    private JPanel listContainer;
    private JLabel lblTotal;
    private JButton btnBack;
    private JButton btnPurchase;
    private JProductRepository productRepo;
    private JCartRepository cartRepo;
    int currId;


    public ShoppingCartPanel(CustomerService customerService, Customer customer, JCartRepository cartRepo, JProductRepository productRepo, JCustomerRepository customerRepo) {
        this.customerService = customerService;
        this.customer = customer;
        this.cart = customerService.getShoppingCart(customer);

        this.productRepo = productRepo;

        this.cartRepo = cartRepo;

        currId = customerRepo.getId(customer.getUsername());

        Checkout checkout = new Checkout(productRepo, cartRepo);


        FlatLightLaf.setup();
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20));

        // Header
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
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.setBorder(null);
        add(listContainer, BorderLayout.CENTER);
//
//        add(createProductScrollPanel(), BorderLayout.CENTER);

        // Footer
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setBorder(new EmptyBorder(10, 0, 0, 0));


        lblTotal = new JLabel("Total: $0.00");
        lblTotal.setFont(new Font("SansSerif", Font.BOLD, 18));
        footerPanel.add(lblTotal, BorderLayout.WEST);

// Purchase Button
        btnPurchase = new JButton("PURCHASE");
        btnPurchase.setFont(new Font("SansSerif", Font.BOLD, 18));
        btnPurchase.setPreferredSize(new Dimension(180, 50));
        btnPurchase.setBackground(new Color(76, 175, 80)); // سبز خوشگل
        btnPurchase.setForeground(Color.WHITE);
        btnPurchase.setFocusPainted(false);

        footerPanel.add(btnPurchase, BorderLayout.EAST);
        add(footerPanel, BorderLayout.SOUTH);

    }

    public void refreshCart() {
        listContainer.removeAll();
        Cart cart = customerService.getShoppingCart(customer);

        if (cart.getCartItems().isEmpty()) {
            JLabel empty = new JLabel("Cart is empty");
            empty.setAlignmentX(Component.CENTER_ALIGNMENT);
            listContainer.add(empty);
            lblTotal.setText("Total: $0.00");
        } else {
            Map<Product, Integer> counts = new HashMap<>();
            for (Product p : cart.getCartItems()) {
                counts.put(p, counts.getOrDefault(p, 0) + 1);
            }

            for (Map.Entry<Product, Integer> entry : counts.entrySet()) {
                listContainer.add(createRow(entry.getKey(), entry.getValue()));
            }

            lblTotal.setText("Total: $" + cart.getItemsTotalPrice());
        }

        listContainer.revalidate();
        listContainer.repaint();
        btnPurchase.setEnabled(!cart.getCartItems().isEmpty());

    }



    private JPanel createRow(Product p, int qty) {

        JPanel row = new JPanel(new BorderLayout());
        row.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.LIGHT_GRAY));

        row.setPreferredSize(new Dimension(0, 80));
        row.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        // Left: product name + quantity
        JLabel lblName = new JLabel(p.getName() + " (x" + qty + ")");
        lblName.setBorder(new EmptyBorder(5, 10, 5, 10));
        row.add(lblName, BorderLayout.WEST);

        // Right: buttons
        JPanel btnPanel = new JPanel();
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.Y_AXIS));

        JButton btnIncrement = new JButton("Increament");
        JButton btnDecrement = new JButton("Decreament");
        JButton btnRemoveAll = new JButton("Remove");

        btnRemoveAll.setForeground(Color.RED);

        btnIncrement.addActionListener(e -> {
            customerService.addToCart(customer, p);
            refreshCart();
//            refreshList();
        });

        btnDecrement.addActionListener(e -> {
            customerService.removeFromCart(customer, p);
            refreshCart();
//            refreshList();
        });
/// //////////////////////////////////
        btnPurchase.addActionListener(e -> {
            Checkout checkout = new Checkout(productRepo, cartRepo);
            checkout.checkoutCustomerOrder(currId);
            refreshCart();

        });


        btnRemoveAll.addActionListener(e -> {
//            customerService.removeProductCompletely(customer.getId(), p);
//            refreshCart();
//            refreshList();
        });

        btnPanel.add(btnIncrement);
        btnPanel.add(Box.createVerticalStrut(5));
        btnPanel.add(btnDecrement);
        btnPanel.add(Box.createVerticalStrut(5));
        btnPanel.add(btnRemoveAll);

        row.add(btnPanel, BorderLayout.EAST);

        return row;
    }


    public JButton getBtnBack() {
        return btnBack;
    }
    public JButton getBtnPurchase() {
        return  btnPurchase;
    }
}
