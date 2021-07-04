import java.awt.*;
import javax.swing.border.*;
import javax.swing.*;
public class ColoredButton  extends JPanel{
    JTextField btn;
    Border border = BorderFactory.createLineBorder(Color.GRAY);
    JLabel label;
    public ColoredButton(){
        this.setLayout(new GridBagLayout());
        this.setBorder(border);
        btn = new JTextField(1);
        btn.setFont(btn.getFont().deriveFont(20f));
        btn.setForeground(Color.BLACK);
        btn.setAlignmentX(100);
        changeColor(Color.LIGHT_GRAY);
        this.add(btn);
    }
    public void changeColor(Color c){
        this.setBackground(c);
        btn.setBackground(c);
        btn.setBorder(BorderFactory.createLineBorder(c));
    }
    public int getDigit(){
        return 0;
    }
    public boolean isGreen(){
        return this.getBackground()==Color.GREEN;
    }
    public int read(){
        while(true){
            try{
                int num = Integer.parseInt(btn.getText());
                if(num>=0 && num<=9)
                    return num;
            } catch(Exception e){}
        }
    }
    public void setTextDigit(String digit){
        this.btn.setText(digit);
    }
}
