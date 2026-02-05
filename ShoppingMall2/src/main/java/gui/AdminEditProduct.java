package gui;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AdminEditProduct extends JPanel {

    public AdminEditProduct() {
        // 1. Setup Main Layout
        setLayout(new GridBagLayout());
        setBorder(new EmptyBorder(40, 40, 40, 40)); // Add padding around the edges

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.gridx = 0;
        gbc.insets = new Insets(10, 0, 10, 0); // Vertical spacing

        // Back Button
        gbc.gridy = 0;
        JPanel btnPanel = new JPanel(new BorderLayout());
        JButton backBtn = new JButton("←");
        backBtn.setFont(new Font("SansSerif", Font.BOLD, 16));
        backBtn.setForeground(new Color(100, 100, 100));
        btnPanel.add(backBtn, BorderLayout.WEST);
        add(btnPanel, gbc);

        // edit price

        gbc.gridy++;
        JLabel label = new JLabel("Set New Price");
        label.setFont(new Font("SansSerif", Font.BOLD, 16));
        label.setForeground(new Color(80, 80, 80));
        add(label, gbc);


        gbc.gridy++;
        JTextField field = new JTextField();
        field.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(field, gbc);

        // edit stock
        gbc.gridy++;
        JLabel label2 = new JLabel("Set New Stock");
        label2.setFont(new Font("SansSerif", Font.BOLD, 16));
        label2.setForeground(new Color(80, 80, 80));
        add(label2, gbc);

        gbc.gridy++;
        JTextField field2 = new JTextField();
        field2.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(field2, gbc);

        gbc.gridy++;

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        buttonPanel.add(createStyledButton("Change Image", new Color(99, 99, 106)));
        buttonPanel.add(createStyledButton("Save Changes", new Color(99, 99, 106)));

        add(buttonPanel, gbc);


    }


    private JButton createStyledButton(String text, Color bg) {
        JButton btn = new JButton(text);
        btn.setBackground(bg);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("SansSerif", Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.putClientProperty("JButton.buttonType", "roundRect");
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.setPreferredSize(new Dimension(150, 50));
        return btn;
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        JFrame frame = new JFrame("Shopping Mall");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new AdminEditProduct());
        frame.setSize(1200, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}