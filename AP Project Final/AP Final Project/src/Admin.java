import javax.swing.*;
import java.awt.*;
import java.awt.event.*;




public class Admin implements ActionListener {

    JFrame frame;

    //Main Panel
    JPanel mainpanelAdmin = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    JPanel titlePanel = new JPanel(); //gbc1
    JPanel greetingPanel = new JPanel(); //gbc 2
    JPanel cataloguePanel = new JPanel(); //gbc 3
    JPanel srch_srtPanel = new JPanel(); //gbc 4

//**Panels in Main**

    //titlePanel
    JLabel Title;

    JButton addButton;
    JButton clientsButton;
    JButton salesButton;



    //greetingPanel
    JLabel usertypeLabel;
    JLabel usernameLabel;
    JLabel greetLabel;

    JButton logoutButton;



    //add cataloguePanel here
    JScrollPane scrollpane ;

    //search and sort panel
    JLabel sortLabel;

    JButton searchButton;
    JButton sortButton;
    JButton profileButton;


Admin(){

    scrollpane = new JScrollPane(cataloguePanel);
    frame = new JFrame("Admin");
    frame.setSize(1000,400);
    gbc.fill = GridBagConstraints.BOTH;




    //title panel
    gbc.gridx = 0; gbc.gridy = 0;
    gbc.weightx = 0.7; gbc.weighty = 0.2;
    titlePanel.setBackground(Color.RED);
    mainpanelAdmin.add(titlePanel,gbc);



    //greetings panel
    gbc.gridx = 1; gbc.gridy = 0;
    gbc.weightx = 0.3;
    greetingPanel.setBackground(Color.YELLOW);
    mainpanelAdmin.add(greetingPanel,gbc);




    //catalogue panel
    gbc.gridx = 0; gbc.gridy = 1;
    gbc.weightx = 0.7; gbc.weighty = 0.8;
    cataloguePanel.setBackground(Color.RED);
    mainpanelAdmin.add(scrollpane,gbc);

    //search and sort panel
    gbc.gridx = 1; gbc.gridy = 1;
    gbc.weightx = 0.3;
    srch_srtPanel.setBackground(Color.YELLOW);
    mainpanelAdmin.add(srch_srtPanel,gbc);


    titlePanel.setBorder(BorderFactory.createTitledBorder("عنوان صفحه"));
    greetingPanel.setBorder(BorderFactory.createTitledBorder("خوش‌آمدگویی"));
    cataloguePanel.setBorder(BorderFactory.createTitledBorder("لیست محصولات"));
    srch_srtPanel.setBorder(BorderFactory.createTitledBorder("جستجو و فیلتر"));



    //frame settings
    //mainpanelAdmin.add(scrollpane);
    frame.add(mainpanelAdmin);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
}








    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
