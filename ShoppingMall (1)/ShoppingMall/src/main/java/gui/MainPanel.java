package gui;

import javax.swing.*;
import java.awt.*;

public class MainPanel {

    //mainpnl panel consits of all panels
    JFrame frame = new JFrame("Shopping Mall");
    CardLayout cardlayout = new CardLayout();
    JPanel mainpanel = new JPanel(cardlayout);

    //****** all panels ***************


    //****** related to admin *********
    AdminEntryPanel adminpanel = new AdminEntryPanel(this);

    //****** related to customer *******

    //checkoutPanel checkoutPanel = new checkoutPanel();
    CustomerEntryPanel customerpanel = new CustomerEntryPanel();

    //**********************************

    public MainPanel() {
        AdminEntryPanel adminpanel = new AdminEntryPanel(this);


        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);



//login ********************************
        LoginDialog logindialog = new LoginDialog(frame);
        logindialog.pack();
        logindialog.setLocationRelativeTo(frame);

        logindialog.setVisible(true);

        if(!logindialog.isLoginSuccessful()){
            System.out.println("Login Failed");
            System.exit(0);
        }

        mainpanel.add(adminpanel, "Admin");


        mainpanel.add(customerpanel, "Customer");
       // mainpanel.add(checkoutPanel, "checkoutPanel");

        frame.add(mainpanel);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        System.out.println("Success");

        if (logindialog.getRole().equals("Admin")) {
            cardlayout.show(mainpanel, "Admin");

        }else if (logindialog.getRole().equals("Customer")) {
            cardlayout.show(mainpanel, "Customer");
        }
    }

}
