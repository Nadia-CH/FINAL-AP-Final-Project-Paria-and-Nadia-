
package gui;

import javax.swing.*;
import java.awt.*;
import model.*;
import repository.*;
import service.*;

public class DisplayCustomer {

    public static JPanel createCustomerPanel(JProductRepository pr, Customer customer, JCartRepository cr, JCustomerRepository customerRepo) {

        CardLayout cardLayout = new CardLayout();
        JPanel displayPanel = new JPanel(cardLayout);
        String currUsername = customer.getUsername();
        displayPanel.setBorder(BorderFactory.createTitledBorder("Welcome, " + currUsername));

        CustomerService service = new CustomerService(cr, customerRepo);

        CustomerEntryPanel dashboard = new CustomerEntryPanel(pr, service, customer);
        ShoppingCartPanel cartPanel = new ShoppingCartPanel(service, customer, cr, pr, customerRepo);

        displayPanel.add(dashboard, "Dashboard");
        displayPanel.add(cartPanel, "Cart");


        // Go to Cart (AND Refresh Data!)
        dashboard.getBtnCart().addActionListener(e -> {
            cartPanel.refreshCart(); // Critical: Load latest items
            cardLayout.show(displayPanel, "Cart");
        });

        // Back to Shop
        cartPanel.getBtnBack().addActionListener(e -> {
            cardLayout.show(displayPanel, "Dashboard");
        });

        // purchase



        dashboard.getBtnLogout().addActionListener(e -> {
            Window currentWindow = SwingUtilities.getWindowAncestor(displayPanel);
            currentWindow.dispose();

            // Open Login Dialog
            JFrame tempFrame = new JFrame();
            LoginDialog login = new LoginDialog(tempFrame);
            login.setVisible(true);

            if (login.isLoginSuccessful()) {
                User user = login.getLoggedInUser();
                JFrame newFrame = new JFrame("Furniture & Toy Store");
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newFrame.setSize(1200, 700);
                newFrame.setLocationRelativeTo(null);

                if (user instanceof Admin) {
                    newFrame.add(DisplayAdmin.Display(pr, (Admin)user, cr, customerRepo));
                } else if (user instanceof Customer) {
                    newFrame.add(DisplayCustomer.createCustomerPanel(pr, (Customer)user, cr, customerRepo));
                }
                newFrame.setVisible(true);
            } else {
                System.exit(0);
            }
        });

        return displayPanel;
    }
}
