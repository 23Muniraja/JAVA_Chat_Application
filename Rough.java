import javax.swing.*;
import java.awt.*;

public class Rough extends JFrame {
    private JLabel heading = new JLabel("User2 Area");
    private JTextArea messageArea = new JTextArea();
    private JTextField messageInput = new JTextField();
    JLabel BG = new JLabel(new ImageIcon("Chat.jpg"));
    private Font font = new Font("Roboto",Font.PLAIN,20);

    private void createGUI(){
        this.setTitle("User1 Messager");
        this.setSize(500,600);
//        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        heading.setFont(font);
        messageArea.setFont(font);
        messageInput.setFont(font);
        heading.setIcon(new ImageIcon("Usr3.jpg"));
        heading.setHorizontalTextPosition(SwingConstants.RIGHT);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        messageArea.setEditable(false);
        messageInput.setHorizontalAlignment(SwingConstants.LEFT);

        this.setLayout(new BorderLayout());

        this.add(heading,BorderLayout.NORTH);
        JScrollPane jScrollPane = new JScrollPane(messageArea);
        this.add(jScrollPane,BorderLayout.CENTER);
        this.add(messageInput,BorderLayout.SOUTH);

        ImageIcon image = new ImageIcon("Logo.png");
        this.setIconImage(image.getImage());
        this.getContentPane().setBackground(new Color(0x0492C7));
        BG.setBounds(0, 0, 500, 600);
        this.add(BG, BorderLayout.CENTER);
        this.setVisible(true);
        this.setResizable(false);//1scsdcscsc
    }

    public static void main(String rk[]){
        Rough r = new Rough();
        r.createGUI();
    }
}
