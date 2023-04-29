import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.net.*;
import java.awt.*;

class User1 extends JFrame {
    ServerSocket server;
    Socket socket;
    BufferedReader br;
    PrintWriter out;

    private static DataOutputStream dataOutputStream = null;
    private static DataInputStream dataInputStream = null;

    //GUI COMPO
    private JLabel heading = new JLabel("User1");
    private JTextArea messageArea = new JTextArea();
    private JTextField messageInput = new JTextField();
    ImageIcon backgroundIcon = new ImageIcon("Chat.jpg");
    JLabel BG = new JLabel(backgroundIcon);
    private Font font = new Font("Roboto",Font.BOLD,20);

    public User1() {
        try {
            server = new ServerSocket(7777);
            System.out.println("User 1 is ready to accept connection");
            System.out.println("Waiting....");
            socket = server.accept();
            dataInputStream = new DataInputStream(socket.getInputStream());
            dataOutputStream = new DataOutputStream(socket.getOutputStream());

            receiveFile("testing.doc");
            System.out.println("File recieved");

//            dataInputStream.close();
//            dataOutputStream.close();

            br = new BufferedReader(new InputStreamReader(socket.getInputStream
                    ()));
            out = new PrintWriter(socket.getOutputStream());

//            createGUI();
            handleEvents();

            startReading();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void createGUI(){
        //GUI code
        this.setTitle("User2 Messager");
        this.setSize(500,600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //coding for component
        heading.setFont(font);
        messageArea.setFont(font);
        messageInput.setFont(font);
        heading.setIcon(new ImageIcon("Usr1.jpg"));
        heading.setHorizontalTextPosition(SwingConstants.RIGHT);
        heading.setVerticalTextPosition(SwingConstants.BOTTOM);
        heading.setHorizontalAlignment(SwingConstants.CENTER);
        heading.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        messageArea.setEditable(false);
        messageInput.setHorizontalAlignment(SwingConstants.LEFT);
        this.setLayout(new BorderLayout());
        BG.setBounds(0, 0 , 500, 600);
        this.add(heading,BorderLayout.NORTH);
        this.add(BG, BorderLayout.CENTER);
        JScrollPane jScrollPane = new JScrollPane(messageArea);
        this.add(jScrollPane,BorderLayout.CENTER);
        this.add(messageInput,BorderLayout.SOUTH);
        ImageIcon image = new ImageIcon("Logo.png");
        this.setIconImage(image.getImage());
        this.setVisible(true);
        this.setResizable(false);
    }

    private void handleEvents(){
        messageInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode()==10) {
                    String content = messageInput.getText();
                    messageArea.append("Me :"+content+"\n");
                    out.println(content);
                    out.flush();
                    messageInput.setText("");
                    messageInput.requestFocus();
                }
            }
        });
    }
    public void startReading(){
        Runnable r1 = ()->{
            System.out.println("Reader started...");
            try {
            while(true){
                String msg = null;
                    msg = br.readLine();
                    if(msg.equals("Exit")){
                        JOptionPane.showMessageDialog(this, "Server" +
                                " terminated the chat");
                        messageInput.setEnabled(false);
                        socket.close();
                        break;
                    }
//                    System.out.println("User2 :"+msg);
                messageArea.append("User2 :"+msg+"\n");
                }
            } catch (Exception e) {
                System.out.println("");
            }
        };
        new Thread(r1).start();
    }
    private static void receiveFile(String fileName) throws Exception{
        int bytes = 0;
        FileOutputStream fileOutputStream = new FileOutputStream(fileName);

        long size = dataInputStream.readLong();     // read file size
        byte[] buffer = new byte[4*1024];
        while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer,0,bytes);
            size -= bytes;      // read upto file size
        }
        fileOutputStream.close();
    }

    public static void main(String[] args) {
        System.out.println("User1 is online");
        new User1();
    }
}
