import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class SignUpPage extends JFrame implements ActionListener {
    JLabel nameLabel, usernameLabel, passwordLabel;
    JTextField nameField, usernameField;
    JPasswordField passwordField;
    JButton submitBtn;
    JPanel panel;
    private Connection connection;
    private PreparedStatement preparedStatement;
    ImageIcon backgroundIcon = new ImageIcon("Bg2.png");
    JLabel backgroundLabel = new JLabel(backgroundIcon);


    SignUpPage() {
        nameLabel = new JLabel("Name:");
        usernameLabel = new JLabel("Username:");
        passwordLabel = new JLabel("Password:");
        nameField = new JTextField(20);
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        submitBtn = new JButton("Submit");
        submitBtn.addActionListener(this);

        panel = new JPanel();
        panel.setLayout(null);

        nameLabel.setBounds(50, 150, 120, 40);
        nameField.setBounds(180, 150, 200, 40);

        usernameLabel.setBounds(50, 220, 120, 40);
        usernameField.setBounds(180, 220, 200, 40);

        passwordLabel.setBounds(50, 290, 120, 40);
        passwordField.setBounds(180, 290, 200, 40);
        submitBtn.setBounds(180, 360, 200, 40);

        backgroundLabel.setBounds(10,10,500,600);

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(submitBtn);

        add(panel, BorderLayout.CENTER);
        setTitle("Sign Up");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        ImageIcon image = new ImageIcon("img.jpg");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(0x0492C2));


        // Establish database connection
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/UserLogin", "root", "Muni");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        submitBtn.addActionListener(e -> {
            String name = nameField.getText();
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            try {
                // Set the values of the prepared statement
                String sql = "INSERT INTO UserLogin(UserId, UserPassword) VALUES(?, SHA2(?,256))";
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                preparedStatement.setString(2, password);

                // Execute the SQL statement
                preparedStatement.executeUpdate();

                // Display a message to the user
                JOptionPane.showMessageDialog(this, "User created successfully!");
                this.setVisible(false);
                Myframe c=new Myframe();
                c.setVisible(true);

            } catch (SQLException ex) {
                // If SQLIntegrityConstraintViolationException occurs, show a message box
                if (ex instanceof SQLIntegrityConstraintViolationException) {
                    JOptionPane.showMessageDialog(this, "Username already exists.");
                } else {
                    ex.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        new SignUpPage();
    }

    public void actionPerformed(ActionEvent ae) {
        String name = nameField.getText();
        String username = usernameField.getText();
        char[] password = passwordField.getPassword();
        String pass = new String(password);


    }
}