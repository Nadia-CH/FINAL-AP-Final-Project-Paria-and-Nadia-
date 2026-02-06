package gui;
import model.Product;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AdminProductView extends JPanel implements ActionListener {
    JButton btnEdit = new JButton("Edit");

    JButton btnDelete = new JButton("Delete");
    JButton btnDetails = new JButton("Details");

    private Product product;





    public AdminProductView(Product p1, String imgPath) {
        this.product = p1;

        setLayout(new BorderLayout(15, 10));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY), // Bottom border line
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        this.setAlignmentX(Component.LEFT_ALIGNMENT); // Critical for BoxLayout

        // 1. Image Section (Left)
        JLabel imgLabel = new JLabel();
        imgLabel.setPreferredSize(new Dimension(100, 300));
        imgLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        // Note: You'll eventually use ImageIcon here
        add(imgLabel, BorderLayout.CENTER);


        // 3. Buttons Section (Bottom or Right)
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));


        btnPanel.add(btnEdit);
        btnEdit.addActionListener(this);
        btnPanel.add(btnDelete);
        btnDelete.addActionListener(this);
        btnPanel.add(btnDetails);
        btnDetails.addActionListener(this);

        add(btnPanel, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == btnDetails ) {
            Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
            AdminViewDetail showDetail = new AdminViewDetail(parentFrame, product);
            showDetail.setVisible(true);


            //show details

        }
        if(e.getSource() == btnDelete ) {
            // remove from repo

        }
        if(e.getSource() == btnEdit) {
            Frame parentFrame = (Frame) SwingUtilities.getWindowAncestor(this);
            AdminEditProduct editDlg = new AdminEditProduct(parentFrame, product.getName(), product.getPrice(), product.getStockQuantity());
            editDlg.setVisible(true);
            //open AdminEditProduct

        }

    }
}