import java.awt.*;
import java.awt.event.ActionListener;
import java.util.Properties;
import javax.swing.*;
public class Frame extends JFrame{
    private JPanel nord;
    private JPanel centro;
    private JPanel sud;
    private JPanel nord1;
    private JPanel nord2;
    private JTextField ip;
    private JTextField porta;
    private JButton start;
    private JButton interrompi;
    private JButton connect;
    private JButton disconnect;
    private JButton clear;
    private ColoredButton[] matrice;
    public Frame() {
        setTitle("Franco Capone 1884168");
        ip = new JTextField(20);
        ip.setText("localhost");
        porta = new JTextField(20);
        porta.setText("4400");
        start = new JButton("start");
        interrompi = new JButton("interrompi");
        nord1 = new JPanel(new FlowLayout());
        nord1.add(new JLabel("IP Address"));
        nord1.add(ip);
        nord2 = new JPanel(new FlowLayout());
        nord2.add(new JLabel("Port"));
        nord2.add(porta);
        nord = new JPanel(new FlowLayout());
        nord.add(start);
        nord.add(nord1);
        nord.add(nord2);
        nord.add(interrompi);
        connect = new JButton("connect");
        disconnect = new JButton("disconnect");
        clear = new JButton("clear");
        sud = new JPanel(new FlowLayout());
        sud.add(connect);
        sud.add(disconnect);
        sud.add(clear);
        matrice = new ColoredButton[5];
        centro = new JPanel(new GridLayout(1,5));
        for (int i = 0; i < 5; i++) {
            matrice[i] = new ColoredButton();
            centro.add(matrice[i]);
        }
        this.getContentPane().add(sud, BorderLayout.SOUTH);
        this.getContentPane().add(centro, BorderLayout.CENTER);
        this.getContentPane().add(nord, BorderLayout.NORTH);
        ActionListener listener = new Listener(this,matrice, ip, porta);
        connect.setActionCommand(Listener.CONNECT);
        connect.addActionListener(listener);
        disconnect.setActionCommand(Listener.DISCONNECT);
        disconnect.addActionListener(listener);
        start.setActionCommand(Listener.START);
        start.addActionListener(listener);
        interrompi.setActionCommand(Listener.INTERROMPI);
        interrompi.addActionListener(listener);
        clear.setActionCommand(Listener.CLEAR);
        clear.addActionListener(listener);
        setLocation(200, 100);
        setButtons(false, false);
        this.setVisible(true);
    }
    public void setButtons(boolean connected, boolean transmitting) {
        if(connected) {
            connect.setEnabled(false);
            setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
            if(transmitting) {
                disconnect.setEnabled(false);
                interrompi.setEnabled(true);
                start.setEnabled(false);
            }
            else {
                interrompi.setEnabled(false);
                clear.setEnabled(false);
                start.setEnabled(true);
                disconnect.setEnabled(true);
            }
        }
        else {
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            connect.setEnabled(true);
            clear.setEnabled(true);
            disconnect.setEnabled(false);
            start.setEnabled(false);
            interrompi.setEnabled(false);
        }
    }
    public ColoredButton[] getMatrice(){ return matrice;  }
}
