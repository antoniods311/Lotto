import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
public class Listener implements ActionListener{
    public static final String START = "start", INTERROMPI = "interrompi", CONNECT = "connect", DISCONNECT = "disconnect", CLEAR = "clear";
    private JTextField ipAddressField;
    private JTextField portaField;
    private ColoredButton[] matrice;
    private boolean connected = false, transmitting = false;
    private Downloader downloader = null;
    private PrintWriter netPw;
    private Scanner scan;
    private Socket sock;
    private Frame frame;
    public Listener(Frame frame, ColoredButton[] matrice, JTextField ipAddr, JTextField porta) {
        this.frame = frame;
        this.matrice = frame.getMatrice();
        for(int i=0; i<5; i++){
            matrice[i].setTextDigit(("/"));
        }
        this.ipAddressField = ipAddr;
        this.portaField = porta;
    }
    private void setupConnection() throws UnknownHostException, IOException{
        sock = new Socket(ipAddressField.getText(), Integer.parseInt(portaField.getText()));
        OutputStream os = sock.getOutputStream();
        netPw = new PrintWriter(new OutputStreamWriter(os));
        scan = new Scanner(sock.getInputStream());
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if(cmd.equals(Listener.CONNECT)) {
            try {
                setupConnection();
                connected = true;
                System.out.println("Connected");
            }catch (IOException e1) {
                JOptionPane.showMessageDialog(null, "Impossiblie connettersi al server: \n" + e1.getMessage());
                e1.printStackTrace();
                return;
            }
            JOptionPane.showMessageDialog(null, "Connessione stabilita");
        }
        else if(cmd.equals(Listener.START)) {
            try {
                downloader = new Downloader(matrice, scan);
                transmitting = true;
                netPw.println(cmd);
                netPw.flush();
                Thread t = new Thread(downloader);
                t.start();
                JOptionPane.showMessageDialog(null, "Download avviato");
            }catch(IOException e1) {
                JOptionPane.showMessageDialog(null, "Impossiblie avviare: \n" + e1.getMessage());
                e1.printStackTrace();
            }
        }
        else if(cmd.equals(Listener.INTERROMPI)) {
            netPw.println(cmd);
            netPw.flush();
            transmitting = false;
            JOptionPane.showMessageDialog(null, "Download fermato");
        }
        else if(cmd.equals(Listener.DISCONNECT)) {
            netPw.println(cmd);
            netPw.flush();
            netPw.close();
            scan.close();
            connected = false;
            try {
                sock.close();
            }catch (IOException e1) {
                e1.printStackTrace();
            }
            JOptionPane.showMessageDialog(null, "Connessione chiusa");
        }
        else if(cmd.equals(Listener.CLEAR)) {
            for(int i=0; i<5; i++){
                matrice[i].changeColor(Color.LIGHT_GRAY);
                matrice[i].setTextDigit(("/"));
            }
        }
        frame.setButtons(connected, transmitting);
    }
}
