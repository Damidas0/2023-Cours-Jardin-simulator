import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPannel extends JPanel{
    private JLabel nomPlante;
    private JLabel infoEau;
    private JLabel infoSoleil;
    private JLabel infoTemperature;



    //private String nomPlante;
    //private String infoEau;
    //private String infoSoleil;
    //private String infoTemperature;

    public InfoPannel(){
        this("Debug", "-1","-1","-1");
    }

    public InfoPannel(String nomPlante, String infoEau, String infoSoleil, String infoTemperature){
        this.setLayout(new GridLayout(1, 4));
        this.nomPlante = new JLabel(nomPlante);
        this.infoEau = new JLabel(infoEau);
        this.infoSoleil = new JLabel(infoSoleil);
        this.infoTemperature = new JLabel(infoTemperature);

        this.add(new JLabel(nomPlante));
        this.add(new JLabel(infoEau)); 
        this.add(new JLabel(infoSoleil)); 
        this.add(new JLabel(infoTemperature));

        this.setBackground(Color.black);
        this.setVisible(true);
    }

    /*public static void main(String[] args) {
                
        JFrame frame = new JFrame();
        frame.setSize(400, 200);
        
        InfoPannel ip= new InfoPannel(); 
        ip.setBounds(20, 20, 200, 600);
        frame.add(ip);
        frame.setVisible(true);
    }*/

    public void majInfoPanel(String nomPlante, int infoEau, int infoSoleil, int infoTemp) {
        this.nomPlante.setText(nomPlante);
        this.infoEau.setText(String.valueOf(infoEau));
        this.infoSoleil.setText(String.valueOf(infoSoleil));
        this.infoTemperature.setText(String.valueOf(infoTemp));
        System.out.println(this.nomPlante.getText());
        //this.repaint();
    }
}