package gui;


import javax.swing.*;
import java.awt.*;

import repository.*;
import model.*;
import service.*;

public class DisplayAdmin {
    public static JPanel Display(JProductRepository pr, Admin admin, JCartRepository cr, JCustomerRepository customerRepo) {
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
