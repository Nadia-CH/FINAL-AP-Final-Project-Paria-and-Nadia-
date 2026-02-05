import javax.swing.*;
import java.awt.*;

public class guitest extends JPanel {

    public guitest() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Title Panel
        JPanel titlePanel = new JPanel(new FlowLayout());
        titlePanel.setBackground(Color.YELLOW);
        titlePanel.add(new JLabel("Shopping Mall"));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        add(titlePanel, gbc);

        // User Information
        JPanel userPanel = new JPanel();
        userPanel.add(new JLabel("User type: "));
        userPanel.add(new JLabel("admin"));
        userPanel.add(new JLabel("Welcome, Cool Admin!"));
        userPanel.add(new JLabel("Your username is: admin"));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        add(userPanel, gbc);

        // Search and Sort
        JPanel searchPanel = new JPanel();
        searchPanel.add(new JTextField(10));
        searchPanel.add(new JButton("Search"));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        add(searchPanel, gbc);

        //Product 1
        JPanel product1Panel = new JPanel();
        product1Panel.add(new JLabel("Title: Coffee"));
        product1Panel.add(new JLabel("Price: 300"));
        product1Panel.add(new JLabel("Average rating: 3.5"));
        product1Panel.add(new JLabel("Rating count: 2"));
        product1Panel.add(new JLabel("Stock: 18"));
        product1Panel.add(new JLabel("Available for client: true"));
        product1Panel.add(new JButton("Modify"));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        add(product1Panel, gbc);

        // Add more product panels similarly...

    }
}
