import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Costumer implements ActionListener {


    Font Header = new Font("Arial", Font.BOLD, 20);
    Font Header2 = new Font("Arial", Font.BOLD, 15);

    JFrame frame;
    JPanel mainpanel = new JPanel(new BorderLayout(5,5));

    JPanel northPanel = new JPanel(new GridBagLayout());
    JPanel centerPanel = new JPanel(new GridBagLayout());
    JPanel eastPanel = new JPanel(new GridLayout());

    JPanel greetingPanel_eastPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JButton logoutButton_eastPanel = new JButton("Logout");


    JPanel searchPanel_eastPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JTextField search_TextField_eastPanel = new JTextField(20);
    JButton searchButton_eastPanel = new JButton("Search");
    JComboBox sort_comboBox_eastPanel = new JComboBox();
    JButton sort_button_eastPanel = new JButton("Sort");
    JButton basket_button_eastPanel = new JButton("Basket");
    JButton profile_button_eastPanel = new JButton("Profile");

    JPanel empty_eastPanel_2 = new JPanel();
    JPanel sort_eastPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
    JPanel empty_eastPanel_4 = new JPanel();


    JLabel northLabel = new JLabel("Shopping Mall");

    Costumer(){


        frame = new JFrame("Costumer");

        northPanel.setLayout(new GridBagLayout());
        northPanel.setBackground(Color.RED);
        northPanel.setPreferredSize(new Dimension(200,100));
        northLabel.setFont(Header);
        northPanel.add(northLabel);

        centerPanel.setBackground(Color.BLUE);
        centerPanel.setPreferredSize(new Dimension(400,300));

        eastPanel.setLayout(new GridLayout(4,1));
        eastPanel.setBackground(Color.YELLOW);
        eastPanel.setPreferredSize(new Dimension(300,200));

        eastPanel.add(greetingPanel_eastPanel);
        greetingPanel_eastPanel.add(new JLabel("Welcome!               "));
        greetingPanel_eastPanel.add(new JLabel("Balance:               "));
        logoutButton_eastPanel.setLocation(searchButton_eastPanel.getLocation().x,searchButton_eastPanel.getLocation().y);
        logoutButton_eastPanel.setFocusable(false);
        greetingPanel_eastPanel.add(logoutButton_eastPanel);

        eastPanel.add(empty_eastPanel_2);

        eastPanel.add(sort_eastPanel);
        sort_eastPanel.add(search_TextField_eastPanel);
        sort_eastPanel.add(searchButton_eastPanel);
        sort_eastPanel.add(new JLabel("Sort by:"));
        sort_eastPanel.add(sort_comboBox_eastPanel);
        sort_comboBox_eastPanel.setPreferredSize(new Dimension(285,20));
        sort_button_eastPanel.setPreferredSize(new Dimension(285,20));
        sort_eastPanel.add(sort_button_eastPanel);

        eastPanel.add(empty_eastPanel_4);






        frame.add(mainpanel);
        mainpanel.add(northPanel,BorderLayout.NORTH);
        mainpanel.add(centerPanel,BorderLayout.CENTER);
        mainpanel.add(eastPanel,BorderLayout.EAST);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000,600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

}
