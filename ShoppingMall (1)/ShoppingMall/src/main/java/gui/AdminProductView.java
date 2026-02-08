package gui;
import com.formdev.flatlaf.FlatLightLaf; // Make sure FlatLaf is in your pom.xml
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import model.*;
import service.*;
import repository.*;



public class AdminProductView extends JPanel {
    public JButton btnEdit = new JButton("Edit");
    public JButton btnDelete = new JButton("Delete");
    public JButton btnDetails = new JButton("Details");
    protected Product product;

    public AdminProductView(Product product) {
        this.product = product;

//        btnEdit.addActionListener(this);

        setLayout(new BorderLayout(15, 10));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY), // Bottom border line
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));
        this.setAlignmentX(Component.LEFT_ALIGNMENT);





        // Image Section
        JLabel imgLabel = new JLabel();
        imgLabel.setPreferredSize(new Dimension(100, 100)); // Fixed size frame
        imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imgLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        ImageIcon icon = loadScaledImage(product.getImagePath(), 100, 100);
        if (icon != null) {
            imgLabel.setIcon(icon);
        } else {
            imgLabel.setText("No Image");
        }

        add(imgLabel, BorderLayout.WEST);

        // button section

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        btnPanel.add(btnEdit);
        btnPanel.add(btnDelete);
        btnPanel.add(btnDetails);
        add(btnPanel, BorderLayout.SOUTH);
    }
    private ImageIcon loadScaledImage(String imgPath, int w, int h) {
        if (imgPath == null || imgPath.trim().isEmpty()) {
            return null;
        }

        // 2. Construct the path to the resources folder
        // Note: We assume images are in src/main/resources/images/
        String resourcePath = "/images/" + imgPath;

        // 3. Try to find the file
        URL imgUrl = getClass().getResource(resourcePath);

        if (imgUrl != null) {
            // 4. Load and Resize
            ImageIcon original = new ImageIcon(imgUrl);
            Image scaled = original.getImage().getScaledInstance(w, h, Image.SCALE_SMOOTH);
            return new ImageIcon(scaled);
        } else {
            // Debugging helper: Print error if image isn't found
            System.err.println("⚠ Could not find image file: " + resourcePath);
            return null;
        }
    }


}