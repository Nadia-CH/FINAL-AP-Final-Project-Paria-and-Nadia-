package gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;

public class AddProductPanel extends JPanel {

    private MainPanel mainpnl;
    private JTextField txtTitle = new JTextField(25);
    private JTextField txtPrice = new JTextField(25);
    private JTextField txtStock = new JTextField(25);
    private JCheckBox chkAvailable = new JCheckBox();
    private File selectedImageFile;


    public AddProductPanel(MainPanel main) {
        this.mainpnl = main;
        setLayout(new BorderLayout(0, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.GREEN);
        headerPanel.setPreferredSize(new Dimension(0, 40));
        JLabel lblHeader = new JLabel("Please enter product's details", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 14));
        headerPanel.add(lblHeader);
        add(headerPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Title"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(txtTitle, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Price"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(txtPrice, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Available for client"), gbc);
        gbc.gridx = 1;
        formPanel.add(chkAvailable, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Stock"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtStock, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Image"), gbc);

        JPanel imageBtnContainer = new JPanel(new GridLayout(1, 3, 5, 0));
        JButton btnChoose = new JButton("Choose image");
        JButton btnShow = new JButton("Show image");
        JButton btnDelete = new JButton("Delete image");
        imageBtnContainer.add(btnChoose);
        imageBtnContainer.add(btnShow);
        imageBtnContainer.add(btnDelete);
        gbc.gridx = 1;
        formPanel.add(imageBtnContainer, gbc);

        add(formPanel, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints fgbc = new GridBagConstraints();

        JButton btnBack = new JButton("Back");
        btnBack.setPreferredSize(new Dimension(100, 30));
        fgbc.gridx = 0; fgbc.gridy = 0; fgbc.weightx = 0.2;
        footerPanel.add(btnBack, fgbc);

        JButton btnAdd = new JButton("Add");
        btnAdd.setPreferredSize(new Dimension(0, 30));
        fgbc.gridx = 1; fgbc.weightx = 0.8; fgbc.fill = GridBagConstraints.HORIZONTAL;
        footerPanel.add(btnAdd, fgbc);

        add(footerPanel, BorderLayout.SOUTH);

        btnChoose.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedImageFile = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(this, "File selected: " + selectedImageFile.getName());
            }
        });

        btnBack.addActionListener(e -> {
            mainpnl.cardlayout.show(mainpnl.mainpanel, "Admin");
        });
        }


//فریم موقت برا تست
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Test checkoutPanel");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 500);

            AddProductPanel testPanel = new AddProductPanel(null);
            frame.add(testPanel);

            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }


    }
