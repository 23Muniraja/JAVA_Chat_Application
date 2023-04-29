import javax.swing.*;
import java.awt.*;

public class ChatAppHomePage extends JFrame {

    public ChatAppHomePage() {

        ImageIcon backgroundIcon = new ImageIcon("bg1.jpg");
        JLabel backgroundLabel = new JLabel(backgroundIcon);
        backgroundLabel.setBounds(0, 0, 500, 600);
        this.add(backgroundLabel, BorderLayout.CENTER);
        this.add(backgroundLabel, BorderLayout.PAGE_START);

        // Set JFrame properties
        setTitle("Aikhyaam App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 600);

        // Set layout manager for the content pane
        setLayout(new BorderLayout());
        ImageIcon image = new ImageIcon("Logo.png");
        this.setIconImage(image.getImage());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 10)); // set the FlowLayout alignment to the left and add padding
        JLabel appNameLabel = new JLabel("Welcome to Aikhyaam App", SwingConstants.CENTER);
        
        topPanel.add(appNameLabel);
        add(topPanel, BorderLayout.NORTH);

        // Add contacts list to the center panel
        JPanel centerPanel = new JPanel(new GridLayout(10, 1));
        JButton contact1Button = new JButton("User 1");
        centerPanel.add(contact1Button);
        add(centerPanel, BorderLayout.CENTER);
        setVisible(true);
        setResizable(false);
    }

    public static void main(String[] args) {
        new ChatAppHomePage();
    }
}
