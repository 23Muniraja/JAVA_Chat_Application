import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.Image;
import javax.imageio.ImageIO;
import java.io.File;

 class SignUpPage1 extends JFrame implements ActionListener {

    Container container = getContentPane();
    private Connection connection;
    private PreparedStatement preparedStatement;

    JLabel nameLabel = new JLabel("Name:");
    JLabel usernameLabel = new JLabel("Username:");
    JLabel passwordLabel = new JLabel("Password:");
    JTextField nameField = new JTextField(20);
    JTextField usernameField = new JTextField(20);
    JPasswordField passwordField = new JPasswordField(20);
    JButton submitBtn = new JButton("Submit");
    JButton resetButton = new JButton("Reset");
    ImageIcon backgroundIcon = new ImageIcon("SignUp.png");
    JLabel backgroundLabel = new JLabel(backgroundIcon);

    SignUpPage1() {

        this.setTitle("Aikhyaam SignUp");
        ImageIcon image = new ImageIcon("Logo.png");
        this.setIconImage(image.getImage());
        this.setVisible(true);
        this.setBounds(500, 150, 500, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);


        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();}

    public void setLayoutManager() {
        container.setLayout(null);
    }
    public void setLocationAndSize() {
        nameLabel.setBounds(50, 150, 120, 40);
        nameField.setBounds(180, 150, 200, 40);

        usernameLabel.setBounds(50, 220, 120, 40);
        usernameField.setBounds(180, 220, 200, 40);

        passwordLabel.setBounds(50, 290, 120, 40);
        passwordField.setBounds(180, 290, 200, 40);

        resetButton.setBounds(300,360,150,40);
        submitBtn.setBounds(100, 360, 150, 40);
        backgroundLabel .setBounds(0, 0, 500, 600);

    }
    public void addComponentsToContainer(){
        container.add(nameLabel);
        container.add(nameField);
        container.add(usernameLabel);
        container.add(usernameField);
        container.add(passwordLabel);
        container.add(passwordField);
        container.add(submitBtn);
        container.add(resetButton);
        container.add(backgroundLabel, BorderLayout.CENTER);
        container.add(backgroundLabel, BorderLayout.PAGE_START);

    }
    public void addActionEvent(){
        submitBtn.addActionListener(this);
        resetButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitBtn) {
            String name = nameField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (name.equals("") || username.equals("") || password.equals("")) {
                JOptionPane.showMessageDialog(this, "Please enter all fields.");
                return; // stop execution if any field is empty
            }

            String url = "jdbc:mysql://localhost:3306/UserLogin";
            String query = "INSERT INTO UserLogin(UserId, UserPassword) VALUES(?, SHA2(?,256))";
            try (Connection con = DriverManager.getConnection(url, "root", "Muni");
                 PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, username);
                ps.setString(2, password);
                int rowInserted = ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "User created successfully!\nRedirecting to Home Page");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error creating user.");
                if (ex instanceof SQLIntegrityConstraintViolationException) {
                    JOptionPane.showMessageDialog(this, "Username already exists.");
                } else {
                    ex.printStackTrace();
                }
            }
            this.setVisible(false);
            Myframe c=new Myframe();
            c.setVisible(true);
        }
        if (e.getSource() == resetButton) {
            usernameField.setText("");
            passwordField.setText("");
        }
    }
 }
public class SignUp1{
    public static void main(String[] args) {
        new SignUpPage1();
    }
}