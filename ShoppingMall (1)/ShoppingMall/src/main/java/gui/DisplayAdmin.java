package gui;


import javax.swing.*;
import java.awt.*;

import repository.*;
import model.*;
import service.*;

public class DisplayAdmin {
    public static JPanel Display(JProductRepository pr, Admin admin) {
        CardLayout cardLayout = new CardLayout();
        JPanel displayPanel = new JPanel(cardLayout);
        displayPanel.setBorder(BorderFactory.createTitledBorder("Admin Area"));

        AdminService service = new AdminService(pr);

        AdminEntryPanel dashboard = new AdminEntryPanel(pr, admin);
        AdminAddProduct addPanel = new AdminAddProduct();

        displayPanel.add(dashboard, "Dashboard");
        displayPanel.add(addPanel, "AddProduct");

        dashboard.getBtnAdd().addActionListener(e -> {
            cardLayout.show(displayPanel, "AddProduct");
        });

        // Inside DisplayAdmin class

        dashboard.getBtnLogout().addActionListener(e -> {
            // Option A: Simple Exit (Kill the app)
            // System.exit(0);

            // Option B: The "Real" Logout (Close Admin, Open Login)

            // 1. Find the current window (JFrame) that holds this panel
            Window currentWindow = SwingUtilities.getWindowAncestor(displayPanel);

            // 2. Close it
            currentWindow.dispose();
//            JFrame frame = new JFrame("Shopping Mall");
//
//            LoginDialog login = new LoginDialog(frame);
//
//            if (login.isLoginSuccessful()) {
//                String role = login.getLoggedInUser().getRole();
//
//                // Switch based on what the user chose/entered
//                if ("ADMIN".equalsIgnoreCase(role)) {
//                    User user = login.getLoggedInUser();
//                    JPanel adminPanel = DisplayAdmin.Display(pr, (Admin) user);
//                    frame.add(adminPanel);
//                } else {
//                    frame.add(new CustomerEntryPanel(pr));
//                }
//
//                // 5. FINALLY, SHOW THE FRAME
//                frame.setVisible(true);
//
//            } else {
//                // If they closed the login without success, just quit
//                System.exit(0);
//            }
        });

        addPanel.getBtnSave().addActionListener(e -> {
            try {
                // Get data from the form
                Product newProduct = addPanel.getNewProduct();

                // Save to Repository
//                pr.addItem(newProduct);
                service.add(newProduct);

                // Refresh Dashboard & Switch back
                dashboard.refreshList();
                JOptionPane.showMessageDialog(displayPanel, "Saved!");
                addPanel.clearFields();
                cardLayout.show(displayPanel, "Dashboard");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(displayPanel, "Error: " + ex.getMessage());
            }
        });

        // Back Button
        addPanel.getBtnBack().addActionListener(e -> {
            cardLayout.show(displayPanel, "Dashboard");
        });


        return displayPanel;
    }

}
