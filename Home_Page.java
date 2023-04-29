import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Myframe extends JFrame implements ActionListener {

    Container container = getContentPane();
    JLabel welcome = new JLabel("WELCOME TO THE HOME PAGE!!!");
    ImageIcon Bg = new ImageIcon("hmpgb.png");
    JLabel BG = new JLabel(Bg);
    JButton Usr1 = new JButton("User 1");
    ImageIcon ic = new ImageIcon("Logo1.jpg");
    JLabel IC = new JLabel(ic);


    Myframe(){
        this.setTitle("Home Page");
        ImageIcon image = new ImageIcon("Logo.png");
        this.setIconImage(image.getImage());
        this.setVisible(true);
        this.setBounds(500, 150, 500, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }
    public void setLayoutManager() {
        container.setLayout(null);
    }
    public void addComponentsToContainer(){
        container.add(welcome);
        container.add(IC);
        container.add(Usr1);
        container.add(BG);
    }
    public void setLocationAndSize() {
        welcome.setBounds(50,35,200,40);
        IC.setBounds(8,35,40,40);
        Usr1.setBounds(11,120,460,60);
        BG.setBounds(0,0,500,600);
    }
    public void addActionEvent() {
        Usr1.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == Usr1){
            Usr1.setBackground(new Color(0x73c2fb));
            JOptionPane.showMessageDialog(this, "Waiting for User 2...");//1
            User1 u1 = new User1();
            this.setVisible(false);
            JOptionPane.showMessageDialog(this, "You are now connected to User 2...");
            u1.setVisible(true);
        }
    }
}

public class Home_Page {
    public static void main(String rk[]){
        new Myframe();
    }
}
