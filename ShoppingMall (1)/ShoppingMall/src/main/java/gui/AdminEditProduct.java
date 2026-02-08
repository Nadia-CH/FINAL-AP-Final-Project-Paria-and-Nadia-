package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AdminEditProduct extends JDialog implements ActionListener {

    private JTextField priceField;
    private JTextField quantityField;

    private JButton submit;
    private boolean submitted = false;

    public AdminEditProduct(Frame parent, String productName, double currentPrice, int currentStock) {
        super(parent, "Edit Product: " + productName, true);


        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        setContentPane(mainPanel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;


        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(new JLabel("Set new price:"), gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        priceField = new JTextField(String.valueOf(currentPrice));
        add(priceField, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Set new stock quantity:"), gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        quantityField = new JTextField(String.valueOf(currentStock));
        add(quantityField, gbc);

        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1;
//        changeImage = new JButton("Change Image");
//        changeImage.addActionListener(this);
//        add(changeImage, gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        submit = new JButton("Submit");
        submit.addActionListener(this);
        add(submit, gbc);

        this.setMinimumSize(new Dimension(350, 0));

        pack();
        setLocationRelativeTo(parent);
    }

    public double getNewPrice() {
        return Double.parseDouble(priceField.getText());
    }

    public int getNewQuantity() {
        return Integer.parseInt(quantityField.getText());
    }

    public boolean isSubmitted() { return submitted; }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            try {
                // Validation logic...
                submitted = true; // Set flag to true
                dispose();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input!");
            }
        }
    }
}
