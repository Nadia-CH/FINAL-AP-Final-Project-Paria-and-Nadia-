package gui;

import com.formdev.flatlaf.FlatLightLaf; // Make sure FlatLaf is in your pom.xml
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import repository.*;
import model.*;
import service.*;

public class AdminEntryPanel extends JPanel {
    private JProductRepository pr;
    private Admin admin;
    AdminService adminService;
    private JButton btnAdd;
    JPanel listContainer;
    private JButton btnLogout;


    public AdminEntryPanel( JProductRepository pr, Admin admin ) {
        this.pr = pr;
        this.admin = admin;
        this.adminService = new AdminService(pr);

        // Apply FlatLaf if it hasn't been applied in Main
        FlatLightLaf.setup();

        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(20, 20, 20, 20)); // Overall outer padding
        initializeUI();
    }

    private void initializeUI() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(10, 10, 10, 10);

        // 1. Header Section
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 4; gbc.weighty = 0.1;
        add(createHeaderPanel(), gbc);

        // 2. Welcome Message (Replaced TextArea with Labels)
        gbc.gridx = 2; gbc.gridy = 1;
        gbc.gridwidth = 2; gbc.weightx = 0.4; gbc.weighty = 0.1;
        add(createWelcomePanel(admin.getName()), gbc);

        // 3. Product List (Left Side)
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.gridwidth = 2; gbc.gridheight = 3;
        gbc.weightx = 0.6; gbc.weighty = 1.0;

        add(createProductScrollPanel(), gbc);

        // 4. Sorting Bar (Above buttons)
        gbc.gridx = 2; gbc.gridy = 2;
        gbc.gridwidth = 2; gbc.gridheight = 1;
        gbc.weightx = 0.4;
        gbc.weighty = 0.05;
        add(createSortingPanel(), gbc);

        // 5. Action Buttons (Bottom Right)
        gbc.gridx = 2; gbc.gridy = 3;
        gbc.gridwidth = 1; gbc.gridheight = 1;
        gbc.weightx = 0.2;
        gbc.weighty = 0.02;
        btnAdd = createStyledButton("Add Product", new Color(217, 72, 173));




        add(btnAdd, gbc);

        gbc.gridx = 3; gbc.gridy = 3;
        btnLogout = createStyledButton("Logout", new Color(58, 176, 211));
        add(btnLogout, gbc);
    }

    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setBackground(new Color(142, 4, 59)); // Deep charcoal blue
        panel.putClientProperty("FlatLaf.style", "arc: 20"); // Rounded corners for panel

        JLabel title = new JLabel("Furniture & Toy Store");
        title.setFont(new Font("SansSerif", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel subtitle = new JLabel("Administrative Management Console");
        subtitle.setFont(new Font("SansSerif", Font.PLAIN, 14));
        subtitle.setForeground(new Color(189, 195, 199));
        subtitle.setHorizontalAlignment(SwingConstants.CENTER);

        panel.add(title);
        panel.add(subtitle);
        panel.setPreferredSize(new Dimension(0, 80));
        return panel;
    }

    private JPanel createWelcomePanel(String adminName) {
        JPanel panel = new JPanel(new GridLayout(2, 1));
        panel.setOpaque(false);

        JLabel welcomeLabel = new JLabel("Welcome back,");
        welcomeLabel.setFont(new Font("SansSerif", Font.PLAIN, 16));

        JLabel nameLabel = new JLabel(adminName);
        nameLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        nameLabel.setForeground(new Color(41, 128, 185));

        panel.add(welcomeLabel);
        panel.add(nameLabel);
        return panel;
    }

private JComponent createProductScrollPanel() {
    listContainer = new JPanel();
    listContainer.setLayout(new BoxLayout(listContainer, BoxLayout.Y_AXIS));

    JScrollPane scrollPane = new JScrollPane(listContainer);
    scrollPane.setViewportView(listContainer);
    scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
    scrollPane.getVerticalScrollBar().setUnitIncrement(16);

    // Load the data for the first time
    refreshList();

    return scrollPane;
}



    private JPanel createSortingPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panel.setBorder(BorderFactory.createTitledBorder("Display Settings"));

        String[] options = {"Time Added", "Price (Asc)", "Price (Desc)", "Stock Level"};
        JComboBox<String> combo = new JComboBox<>(options);
        combo.putClientProperty("JComponent.roundRect", true); // Modern rounded combo box

        panel.add(new JLabel("Sort Inventory:"));
        panel.add(combo);
        return panel;
    }

    private JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 13));
        btn.setFocusPainted(false);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // FlatLaf rounded button style
        btn.putClientProperty("JButton.buttonType", "roundRect");
        btn.setPreferredSize(new Dimension(90, 18));

        return btn;
    }


    public JButton getBtnAdd() {
        return btnAdd;
    }
    public JButton getBtnLogout() {
        return btnLogout;
    }


    public void refreshList() {
        // clear the panel so we don't get duplicate items
        listContainer.removeAll();

        for (Product p : pr.getAll()) {
            AdminProductView pView = new AdminProductView(p);

            // delete
            pView.btnDelete.addActionListener(e -> {
                int confirm = JOptionPane.showConfirmDialog(this,
                        "Delete " + p.getName() + "?", "Confirm", JOptionPane.YES_NO_OPTION);

                if (confirm == JOptionPane.YES_OPTION) {
                    adminService.delete(p.getProductId());
                    refreshList();
                }
            });

            // edit
            pView.btnEdit.addActionListener(e -> {
                Frame parent = (Frame) SwingUtilities.getWindowAncestor(this);
                AdminEditProduct editDlg = new AdminEditProduct(parent, p.getName(), p.getPrice(), p.getStockQuantity());
                editDlg.setVisible(true);

                if (editDlg.isSubmitted()) {
                    adminService.editPrice(p.getProductId(), editDlg.getNewPrice());
                    adminService.editStockQuantity(p.getProductId(), editDlg.getNewQuantity());

                    refreshList();
                }
            });

            // detals
            pView.btnDetails.addActionListener(e -> {
                Frame parent = (Frame) SwingUtilities.getWindowAncestor(this);
                AdminViewDetail detailDialog = new AdminViewDetail(parent, p);
                detailDialog.setVisible(true);
            });

            listContainer.add(pView);
        }

        listContainer.add(Box.createVerticalGlue());
        listContainer.revalidate();
        listContainer.repaint();
    }

    public AdminService getAdminService() {
        return adminService;
    }

}