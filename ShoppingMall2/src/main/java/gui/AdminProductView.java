package gui;
import com.formdev.flatlaf.FlatLightLaf; // Make sure FlatLaf is in your pom.xml
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;



public class AdminProductView extends JPanel {
    public AdminProductView(String name, double price, int stock, String imgPath) {

        setLayout(new BorderLayout(15, 10));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createMatteBorder(0, 0, 2, 0, Color.LIGHT_GRAY), // Bottom border line
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));
        this.setMaximumSize(new Dimension(Integer.MAX_VALUE, 200));

        this.setAlignmentX(Component.LEFT_ALIGNMENT); // Critical for BoxLayout

        // 1. Image Section (Left)
        JLabel imgLabel = new JLabel();
        imgLabel.setPreferredSize(new Dimension(100, 300));
        imgLabel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        // Note: You'll eventually use ImageIcon here
        add(imgLabel, BorderLayout.CENTER);


        // 3. Buttons Section (Bottom or Right)
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(new JButton("Edit"));
        btnPanel.add(new JButton("Delete"));
        btnPanel.add(new JButton("Details"));
        add(btnPanel, BorderLayout.SOUTH);
    }
}