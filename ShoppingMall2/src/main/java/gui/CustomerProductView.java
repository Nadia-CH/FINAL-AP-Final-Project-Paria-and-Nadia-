package gui;
import com.formdev.flatlaf.FlatLightLaf; // Make sure FlatLaf is in your pom.xml
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class CustomerProductView extends JPanel {
    public CustomerProductView(String name, double price, int stock, String imgPath) {

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
        JLabel nameLabel = new JLabel(name);
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
        JButton add = new JButton("+");
        add.setBackground(Color.LIGHT_GRAY);
        add.putClientProperty("JButton.buttonType", "roundRect");
        add.setPreferredSize(new Dimension(30, 30));
        JButton remove = new JButton("-");
        remove.setBackground(Color.lightGray);
        remove.putClientProperty("JButton.buttonType", "roundRect");
        remove.setPreferredSize(new Dimension(30, 30));
        btnPanel.add(add);
        btnPanel.add(remove);
        add(btnPanel, gbc);


        // price
        gbc.gridx = 2;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.insets = new Insets(240, 0, 0, 0);
        JLabel priceLabel = new JLabel("Price: $" + price);
        priceLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        add(priceLabel, gbc);
    }
}