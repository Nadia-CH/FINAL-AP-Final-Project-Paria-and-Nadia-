import model.Product;
import java.util.Scanner;

import model.*;
import repository.*;
import gui.AdminEntryPanel;
import gui.*;

import javax.swing.*;

// this is a test run
public class Main {
    public static void main(String[] args) {
        JProductRepository pr = new JProductRepository();
        JCartRepository cr = new JCartRepository();
        JFrame frame = new JFrame("Shopping Mall");

        frame.setSize(1200, 600);
        frame.setLocationRelativeTo(null);

        LoginDialog login = new LoginDialog(frame);
        login.pack();
        login.setLocationRelativeTo(frame);
        login.setVisible(true);


        if (login.isLoginSuccessful()) {
            String role = login.getLoggedInUser().getRole();

            // Switch based on what the user chose/entered
            if ("ADMIN".equalsIgnoreCase(role)) {
                User user = login.getLoggedInUser();
                JPanel adminPanel = DisplayAdmin.Display(pr, (Admin) user);
                frame.add(adminPanel);
            } else {
                User user = login.getLoggedInUser();
                JPanel customerPanel = DisplayCustomer.createCustomerPanel(pr, (Customer) user, cr);
                frame.add(customerPanel);
            }

            // 5. FINALLY, SHOW THE FRAME
            frame.setVisible(true);

        } else {
            // If they closed the login without success, just quit
            System.exit(0);
        }
    }

}

