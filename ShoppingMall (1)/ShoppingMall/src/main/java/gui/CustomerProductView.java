package gui;
import com.formdev.flatlaf.FlatLightLaf; // Make sure FlatLaf is in your pom.xml
import model.Product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;


public class CustomerProductView extends JPanel {
    private JButton btnAdd;
    private JButton btnRemove;
    private Product product;
    public CustomerProductView(Product product) {

        FlatLightLaf.setup();

        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY), // Bottom border line
                BorderFactory.createEmptyBorder(10, 10, 10, 10)

        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);


        // Product Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.2;
        JPanel namePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel nameLabel = new JLabel(product.getName());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        namePanel.add(nameLabel, LEFT_ALIGNMENT);
        add(namePanel, gbc);

        // 1. Image Section (Left)
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 3;
        gbc.weightx = 0.5;
        gbc.weighty = 0.6;
        JLabel imgLabel = new JLabel();
        imgLabel.setPreferredSize(new Dimension(100, 300));
        imgLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
//        add(imgLabel, gbc);
        ImageIcon icon = loadScaledImage(product.getImagePath(), 100, 100);
        if (icon != null) {
            imgLabel.setIcon(icon);
        } else {
            imgLabel.setText("No Image");
        }

        add(imgLabel, gbc);


        // Spacer
        gbc.gridx = 2; gbc.gridy = 1;
        gbc.gridwidth = 2; gbc.gridheight = 2;
        gbc.weighty = 1.0;
        JPanel spacer = new JPanel();
        spacer.setOpaque(false);
        add(spacer, gbc);


        // 3. Buttons Section (Bottom or Right)
        gbc.gridy = 4; gbc.gridx = 0;
        gbc.gridwidth = 4;
        gbc.gridheight = 1;
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnAdd =  new JButton("Add");
        btnAdd.setBackground(Color.LIGHT_GRAY);
        btnAdd.putClientProperty("JButton.buttonType", "roundRect");
        btnAdd.setPreferredSize(new Dimension(120, 30));
        btnRemove = new JButton("Remove");
        btnRemove.setBackground(Color.lightGray);
        btnRemove.putClientProperty("JButton.buttonType", "roundRect");
        btnRemove.setPreferredSize(new Dimension(120, 30));
        btnPanel.add(btnAdd);
        btnPanel.add(btnRemove);
        add(btnPanel, gbc);


        // price
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.insets = new Insets(240, 0, 0, 0);
        JLabel priceLabel = new JLabel("Price: $" + product.getPrice());
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(priceLabel, gbc);
    }
    private ImageIcon loadScaledImage(String imgPath, int w, int h) {
        // 1. Handle null or empty paths
        if (imgPath == null || imgPath.trim().isEmpty()) {
            return null;
        }

        // 2. Construct the path to the resources folder
        // Note: We assume images are in src/main/resources/images/
        String resourcePath = "/images/" + imgPath;

        // 3. Try to find the file
        URL imgUrl = getClass().getResource(resourcePath);

        if (imgUrl != null) {
            // 4. Load and Resize
            ImageIcon original = new ImageIcon(imgUrl);
            Image scaled = original.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        } else {
            // Debugging helper: Print error if image isn't found
            System.err.println("⚠ Could not find image file: " + resourcePath);
            return null;
        }
    }
    public JButton getBtnAdd() { return btnAdd; }
    public JButton getBtnRemove() { return btnRemove; }
}
//package gui;
//
//import com.formdev.flatlaf.FlatLightLaf;
//import model.Product;
//import javax.swing.*;
//import java.awt.*;
//
//public class CustomerProductView extends JPanel {
//    private JButton btnAdd;
//    private Product product;
//
//    public CustomerProductView(Product product) {
//        this.product = product;
//        FlatLightLaf.setup();
//
//        setLayout(new BorderLayout());
//        setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
//        setPreferredSize(new Dimension(300, 100));
//        setMaximumSize(new Dimension(Integer.MAX_VALUE, 100));
//
//        // Info
//        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
//        JLabel nameLabel = new JLabel("  " + product.getName());
//        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
//        JLabel priceLabel = new JLabel("  Price: $" + product.getPrice());
//
//        infoPanel.add(nameLabel);
//        infoPanel.add(priceLabel);
//        add(infoPanel, BorderLayout.CENTER);
//
//        // Button
//        btnAdd = new JButton("Add to Cart");
//        btnAdd.setBackground(new Color(46, 204, 113));
//        btnAdd.setForeground(Color.WHITE);
//
//        JPanel btnPanel = new JPanel();
//        btnPanel.add(btnAdd);
//        add(btnPanel, BorderLayout.EAST);
//    }
//
//    public JButton getBtnAdd() { return btnAdd; }
//}