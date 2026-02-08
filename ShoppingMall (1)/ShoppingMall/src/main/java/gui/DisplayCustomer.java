//package gui;
//import model.*;
//import repository.*;
//import service.*;
//
//import javax.swing.*;
//import java.awt.*;
//
//
//public class DisplayCustomer {
//    LoginDialog loginDialog;
//    public static JPanel Display(JProductRepository pr, Customer customer, JCartRepository cr) {
//        CardLayout cardLayout = new CardLayout();
//        JPanel displayPanel = new JPanel(cardLayout);
//        displayPanel.setBorder(BorderFactory.createTitledBorder("Customer Area"));
//
//        CustomerService service = new CustomerService(cr);
//
//        CustomerEntryPanel dashboard = new CustomerEntryPanel(pr);
//
//    }
//}
package gui;

import javax.swing.*;
import java.awt.*;
import model.*;
import repository.*;
import service.*;

public class DisplayCustomer {

    public static JPanel createCustomerPanel(JProductRepository pr, Customer customer, JCartRepository cr) {

        // 1. Setup Layout
        CardLayout cardLayout = new CardLayout();
        JPanel displayPanel = new JPanel(cardLayout);
        displayPanel.setBorder(BorderFactory.createTitledBorder("Welcome, " + customer.getUsername() + " Your Balance: " + customer.getBalance()));

        // 2. Initialize Service (Shared between panels)
        CustomerService service = new CustomerService(cr);

        // 3. Create the Two Main Views
        CustomerEntryPanel dashboard = new CustomerEntryPanel(pr, service, customer);
        ShoppingCartPanel cartPanel = new ShoppingCartPanel(service, customer);

        // 4. Add them to the "Deck"
        displayPanel.add(dashboard, "Dashboard");
        displayPanel.add(cartPanel, "Cart");

        // --- NAVIGATION LISTENERS ---

        // A. Go to Cart (AND Refresh Data!)
        dashboard.getBtnCart().addActionListener(e -> {
            cartPanel.refreshCart(); // Critical: Load latest items
            cardLayout.show(displayPanel, "Cart");
        });

        // B. Back to Shop
        cartPanel.getBtnBack().addActionListener(e -> {
            cardLayout.show(displayPanel, "Dashboard");
        });

        // C. Logout Logic
        dashboard.getBtnLogout().addActionListener(e -> {
            // Close current window
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

                // Recursion: Re-open the correct panel
                if (user instanceof Admin) {
                    newFrame.add(DisplayAdmin.Display(pr, (Admin)user));
                } else if (user instanceof Customer) {
                    newFrame.add(DisplayCustomer.createCustomerPanel(pr, (Customer)user, cr));
                }
                newFrame.setVisible(true);
            } else {
                System.exit(0);
            }
        });

        return displayPanel;
    }
}
