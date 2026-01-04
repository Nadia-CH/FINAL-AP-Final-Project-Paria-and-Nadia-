import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login implements ActionListener {

    JFrame frame;
    JPanel panel;
    JTextField username;
    JPasswordField password;
    JLabel[] labels = new JLabel[3];
    JButton[] buttons = new JButton[2];

    @Override
    public void actionPerformed(ActionEvent e) {

    }



    Login(){

        JFrame frame = new JFrame("Login");
        JPanel panel = new JPanel();
        username = new JTextField();
        password = new JPasswordField();

        frame.setSize(420,250);
        frame.setLayout(null);

        panel.setBounds(50,70,300,50);
        panel.setLayout(new GridLayout(2,2,10,10));
        //panel.setBackground(Color.RED);
// gridbaglayoutttttt!!!!
        labels[0] = new JLabel("Pls Enter!");
        labels[0].setOpaque(true);
        labels[0].setBackground(Color.GREEN);
        labels[0].setBounds(150,20,100,30);
        frame.add(labels[0]);

        labels[1] = new JLabel("Username");
        labels[2] = new JLabel("Password");

        panel.add(labels[1]);
        panel.add(username);

        panel.add(labels[2]);
        panel.add(password);



        frame.add(panel);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }

}
