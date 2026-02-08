package gui;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import com.formdev.flatlaf.FlatLightLaf;
import model.*;


public class AdminAddProduct extends JPanel{

    private JTextField txtName = new JTextField(25);
    private JTextField txtCategory = new JTextField(25); // new field
    private JTextField txtPrice = new JTextField(25);
    private JTextField txtStock = new JTextField(25);
    private JCheckBox chkAvailable = new JCheckBox();
    private File selectedImageFile;

    private JButton btnSave = new JButton("Save");
    private JButton btnBack = new JButton("Back");

    public AdminAddProduct() {

        FlatLightLaf.setup();
        setLayout(new BorderLayout(0, 10));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(Color.GREEN);
        headerPanel.setPreferredSize(new Dimension(0, 40));
        JLabel lblHeader = new JLabel("Enter product's details", SwingConstants.CENTER);
        lblHeader.setFont(new Font("Arial", Font.BOLD, 14));
        headerPanel.add(lblHeader);
        add(headerPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Name"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(txtName, gbc);


        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Catrgory"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(txtCategory, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        formPanel.add(new JLabel("Price"), gbc);
        gbc.gridx = 1; gbc.fill = GridBagConstraints.HORIZONTAL;
        formPanel.add(txtPrice, gbc);

        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Available for client"), gbc);
        gbc.gridx = 1;
        formPanel.add(chkAvailable, gbc);

        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Stock"), gbc);
        gbc.gridx = 1;
        formPanel.add(txtStock, gbc);

        gbc.gridx = 0; gbc.gridy = 5;
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

        btnChoose.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                selectedImageFile = fileChooser.getSelectedFile();
                JOptionPane.showMessageDialog(this, "Selected: " + selectedImageFile.getName());
            }
        });

        add(formPanel, BorderLayout.CENTER);

        JPanel footerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints fgbc = new GridBagConstraints();

//        JButton btnBack = new JButton("Back");
        btnBack.setPreferredSize(new Dimension(100, 30));
        fgbc.gridx = 0; fgbc.gridy = 0; fgbc.weightx = 0.2;
        footerPanel.add(btnBack, fgbc);

//        JButton btnAdd = new JButton("Add");
        btnSave.setPreferredSize(new Dimension(0, 30));
        fgbc.gridx = 1; fgbc.weightx = 0.8; fgbc.fill = GridBagConstraints.HORIZONTAL;
        footerPanel.add(btnSave, fgbc);

        add(footerPanel, BorderLayout.SOUTH);

    }
    public JButton getBtnSave() { return btnSave; }
    public JButton getBtnBack() { return btnBack; }

    public Product getNewProduct() {
        String title = txtName.getText().trim();
        String category = txtCategory.getText().trim(); // Assuming you added this field

        if (title.isEmpty()) {
            throw new RuntimeException("Product Title cannot be empty.");
        }
        if (category.isEmpty()) {
            throw new RuntimeException("Category cannot be empty.");
        }

        double price;
        int stock;

        try {
            price = Double.parseDouble(txtPrice.getText().trim());
            stock = Integer.parseInt(txtStock.getText().trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Price and Stock must be valid numbers.");
        }

        if (price < 0) throw new RuntimeException("Price cannot be negative.");
        if (stock < 0) throw new RuntimeException("Stock cannot be negative.");


        String imageName = (selectedImageFile != null) ? selectedImageFile.getName() : "default.png";


        Product newProduct = new Product(title, category, price, imageName);
        newProduct.setStockQuantity(stock);

        return newProduct;
    }

    public void clearFields() {
        // Reset Text Fields
        txtName.setText("");
        txtCategory.setText(""); // If you have this field
        txtPrice.setText("");
        txtStock.setText("");

        // Reset Checkbox
        chkAvailable.setSelected(false);

        // Reset Image Label and File
//        lblFile.setText(" No file selected");
//        selectedImageFile = null;
    }
}
