package gui;
import model.Product;

import javax.swing.*;
import java.awt.*;

public class AdminViewDetail extends JDialog {

    private Product product;

    public AdminViewDetail(Frame owner, Product product) {
        super(owner, "Details for: " + product.getName(), true);
        this.product = product;

        setSize(350, 250);
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        initUI();
    }


    private void initUI() {
        JPanel mainPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        mainPanel.add(new JLabel("Product ID:"));
        mainPanel.add(new JLabel(String.valueOf(product.getProductId())));

        mainPanel.add(new JLabel("Name:"));
        mainPanel.add(new JLabel(product.getName()));

        mainPanel.add(new JLabel("Category:"));
        mainPanel.add(new JLabel(product.getCategory()));

        mainPanel.add(new JLabel("Price:"));
        mainPanel.add(new JLabel(product.getPrice() + "$"));

        mainPanel.add(new JLabel("Stock Quantity:"));
        mainPanel.add(new JLabel(String.valueOf(product.getStockQuantity())));

        add(mainPanel, BorderLayout.CENTER);

        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }
}

