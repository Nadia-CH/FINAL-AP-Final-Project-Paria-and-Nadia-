package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AdminEditProduct extends JDialog implements ActionListener {

    private JTextField priceField;
    private JTextField quantityField;
    private JButton changeImage;
    private JButton submit;
    private File selectedImage;

    public AdminEditProduct(Frame parent, String productName, double currentPrice, int currentStock) {
        super(parent, "Edit Product: " + productName, true);


        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

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
        changeImage = new JButton("Change Image");
        changeImage.addActionListener(this);
        add(changeImage, gbc);

        gbc.gridx = 1; gbc.gridy = 4;
        submit = new JButton("Submit");
        submit.addActionListener(this);
        add(submit, gbc);

        pack();
        setLocationRelativeTo(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == changeImage) {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedImage = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(this, "Image selected: " + selectedImage.getName());
            }
        }

        if (e.getSource() == submit) {
            System.out.println("Updating product to Price: " + priceField.getText());
            dispose();
        }
    }
}
