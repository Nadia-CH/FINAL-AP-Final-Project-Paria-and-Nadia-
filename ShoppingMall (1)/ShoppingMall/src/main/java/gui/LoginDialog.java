package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginDialog extends JDialog implements ActionListener {

    JTextField username;
    JPasswordField password;
    JLabel  titleLabel;
    JLabel  userLabel;
    JLabel  passLabel;
    JButton loginButton;
    JButton cancelButton;

    private boolean loginSuccess = false;
    private String role;

    void setLoginSuccess(boolean loginSuccess) {
        this.loginSuccess = loginSuccess;
    }

    boolean isLoginSuccessful() {
        return loginSuccess;
    }

    boolean isAdmin(){
        if(username.getText().equals("admin") && password.getText().equals("1234")){
            return true;
        }
        return false;
    }

    boolean isCustomer(){
        if(username.getText().equals("user") && password.getText().equals("1111")){
            return true;
        }
        return false;
    }

   void setRole(String role){
        this.role = role;
    }

    String getRole(){
        return role;
    }


    public LoginDialog(JFrame parent) {
        super(parent, "Login", true);

        setLayout(new BorderLayout());

        titleLabel = new JLabel("Pls Enter!", SwingConstants.CENTER);
        titleLabel.setOpaque(true);
        titleLabel.setBackground(Color.GREEN);
        titleLabel.setPreferredSize(new Dimension(300, 40));
        add(titleLabel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 10));

        userLabel = new JLabel("Username");
        passLabel = new JLabel("Password");

        username = new JTextField();
        password = new JPasswordField();

        centerPanel.add(userLabel);
        centerPanel.add(username);
        centerPanel.add(passLabel);
        centerPanel.add(password);


        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();

        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");

        loginButton.addActionListener(this);
        cancelButton.addActionListener(this);

        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);

        add(buttonPanel, BorderLayout.SOUTH);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            if (isAdmin())
            {
                setLoginSuccess(true);
                setRole("Admin");
                dispose();

            }else if (isCustomer()){
                setLoginSuccess(true);
                setRole("Customer");
                dispose();

            } else {
                setLoginSuccess(false);
            }
        } else if (e.getSource() == cancelButton) {
            System.exit(0);
        }
    }
}
