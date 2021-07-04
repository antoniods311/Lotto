import java.awt.Color;
import java.io.*;
import java.util.*;
import javax.swing.JOptionPane;
public class Downloader implements Runnable{
    private Scanner scan;
    private boolean running;
    private ColoredButton[] matrice;
    int valore;
    public Downloader(ColoredButton[] matrice, Scanner scan) throws IOException{
        this.matrice = matrice;
        this.scan = scan;
        this.running = false;
    }
    @Override
    public void run() {
        if(!this.isRunning()) {
            this.running = true;
            while(running) {
                String cmd = scan.nextLine();
                String[] info = cmd.split(";");
                String position = info[0];
                String numero = info[1];
                if(position.equals("*") && numero.equals("*")) {
                    running = false;
                    continue;
                }
                if(position.equals("-1") && numero.equals("-1")) {
                    JOptionPane.showMessageDialog(null, "Server wins");
                    running = false;
                    break;
                }
                else {
                    for(int i = 0; i < 5; i++) {
                        valore = matrice[i].read();
                        if(valore == Integer.parseInt(numero))
                            matrice[Integer.parseInt(position)].changeColor(Color.GREEN);
                        else matrice[Integer.parseInt(position)].changeColor(Color.RED);
                    }
                }
            }
            int conta = 0;
            for(int i = 0; i < 5; i++) {
                if(matrice[i].isGreen()) conta ++;
            }
            if(conta > 0) {
                JOptionPane.showMessageDialog(null, "Client wins");
            }
            else JOptionPane.showMessageDialog(null, "Server wins");
        }
    }
    public boolean isRunning() {
        return running;
    }
}
