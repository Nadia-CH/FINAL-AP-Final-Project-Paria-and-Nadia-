package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import model.*;

public class LoginDialog extends JDialog implements ActionListener {

    private JTextField username;
    private JPasswordField password;
    private JButton loginButton;
    private JButton cancelButton;

    private boolean loginSuccess = false;
    private User loggedInUser;

    public LoginDialog(JFrame parent) {
        super(parent, "Login", true);

        setLayout(new BorderLayout());

        // Header
        JLabel titleLabel = new JLabel("Pls Enter!", SwingConstants.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.GREEN);
        titleLabel.setPreferredSize(new Dimension(300, 50));
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Username
        gbc.gridx = 0; gbc.gridy = 0;
        centerPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        username = new JTextField(15);
        centerPanel.add(username, gbc);

        // Password
        gbc.gridx = 0; gbc.gridy = 1;
        centerPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        password = new JPasswordField(15);
        centerPanel.add(password, gbc);

        add(centerPanel, BorderLayout.CENTER);

        // BUTTONS
        JPanel buttonPanel = new JPanel();
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");

        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);

        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);


        this.pack();
        this.setLocationRelativeTo(parent);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            String userIn = username.getText();
            String passIn = new String(password.getPassword());

            if (userIn.contains("ADMIN") && passIn.length() == 8) {
                loginSuccess = true;
                this.loggedInUser = new Admin(userIn, passIn);
                dispose();
            }
            else if (passIn.length() == 8) {
                loginSuccess = true;
                this.loggedInUser = new Customer(userIn, passIn);
                dispose();
            }
            else {
                JOptionPane.showMessageDialog(this, "Invalid Username or Password!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == cancelButton) {
            System.exit(0);
        }
    }
    public User getLoggedInUser() {
        return loggedInUser;
    }

    public boolean isLoginSuccessful() { return loginSuccess; }

}