package gui;

import javax.swing.*;
import java.awt.*;

public class checkoutPanel extends JPanel {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Test Checkout");
            frame.setSize(800, 600);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            CardLayout cardLayout = new CardLayout();
            JPanel mainPanel = new JPanel(cardLayout);

            // ---------- Fake Previous Page ----------
            JPanel customerPanel = new JPanel();
            JButton goCheckout = new JButton("Go To Checkout");
            customerPanel.add(goCheckout);

            // ---------- Checkout Panel ----------
            JPanel checkoutPanel = new JPanel(new BorderLayout(10, 10));

            DefaultListModel<String> cartModel = new DefaultListModel<>();
            cartModel.addElement("Laptop - 1200$");
            cartModel.addElement("Mouse - 50$");
            cartModel.addElement("Keyboard - 100$");

            JList<String> cartList = new JList<>(cartModel);
            checkoutPanel.add(new JScrollPane(cartList), BorderLayout.CENTER);

            int[] prices = {1200, 50, 100};
            int[] balance = {1500};

            JLabel totalLabel = new JLabel();
            JLabel balanceLabel = new JLabel("Balance: " + balance[0] + "$");

            Runnable updateTotal = () -> {
                int total = 0;
                for (int i = 0; i < cartModel.size(); i++) {
                    total += prices[i];
                }
                totalLabel.setText("Total: " + total + "$");
            };
            updateTotal.run();

            JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            infoPanel.add(totalLabel);
            infoPanel.add(Box.createHorizontalStrut(20));
            infoPanel.add(balanceLabel);

            JButton backBtn = new JButton("Back");
            JButton removeBtn = new JButton("Remove");
            JButton purchaseBtn = new JButton("Purchase");

            backBtn.addActionListener(e ->
                    cardLayout.show(mainPanel, "Customer")
            );

            removeBtn.addActionListener(e -> {
                int idx = cartList.getSelectedIndex();
                if (idx != -1) {
                    cartModel.remove(idx);
                    updateTotal.run();
                }
            });

            purchaseBtn.addActionListener(e ->
                    JOptionPane.showMessageDialog(frame, "Purchase clicked ✅")
            );

            JPanel btnPanel = new JPanel();
            btnPanel.add(backBtn);
            btnPanel.add(removeBtn);
            btnPanel.add(purchaseBtn);

            JPanel southPanel = new JPanel(new GridLayout(2, 1));
            southPanel.add(infoPanel);
            southPanel.add(btnPanel);

            checkoutPanel.add(southPanel, BorderLayout.SOUTH);

            // ---------- CardLayout Setup ----------
            mainPanel.add(customerPanel, "Customer");
            mainPanel.add(checkoutPanel, "Checkout");

            goCheckout.addActionListener(e ->
                    cardLayout.show(mainPanel, "Checkout")
            );

            frame.add(mainPanel);
            frame.setVisible(true);
        });
    }
}
