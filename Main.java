import java.awt.BorderLayout;
import java.awt.Dimension;
//  w  ww.  ja v  a2s .com
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Main extends JFrame {
    public Main() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel p = new JPanel(new BorderLayout());
        JTextField tf = new JTextField();
        JLabel label = new JLabel(new ImageIcon("Chat.jpg"));
        label.setBackground(tf.getBackground());
//        label.setPreferredSize(new Dimension(label.getPreferredSize().width, tf
//                .getPreferredSize().height));
        p.setBorder(tf.getBorder());
        tf.setBorder(null);
        p.add(label, BorderLayout.WEST);
        p.add(tf, BorderLayout.CENTER);
        JPanel p1 = new JPanel();
        p1.add(p);
        getContentPane().add(p1);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}