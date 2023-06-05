import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPannel extends JPanel implements Runnable{
    private Label nomPlante;
    public Label infoEau;
    private Label infoSoleil;
    private Label infoTemperature;



    //private String nomPlante;
    //private String infoEau;
    //private String infoSoleil;
    //private String infoTemperature;

    public InfoPannel(){
        this("Debug", "-1","-1","-1");
    }

    public InfoPannel(String nomPlante, String infoEau, String infoSoleil, String infoTemperature){
        this.setLayout(new GridLayout(1, 4));
        this.nomPlante = new Label(nomPlante);
        this.infoEau = new Label(infoEau);
        this.infoSoleil = new Label(infoSoleil);
        this.infoTemperature = new Label(infoTemperature);

        this.add(new Label(nomPlante));
        this.add(new Label(infoEau)); 
        this.add(new Label(infoSoleil)); 
        this.add(new Label(infoTemperature));

        this.setBackground(Color.red);
        this.setVisible(true);
        Ordonnanceur.getOrdonnanceur();
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
        //this.nomPlante.setText(nomPlante);
        this.nomPlante = new Label(nomPlante);
        this.infoEau.setText(String.valueOf(infoEau));
        this.infoSoleil.setText(String.valueOf(infoSoleil));
        this.infoTemperature.setText(String.valueOf(infoTemp));
        System.out.println(this.nomPlante.getText());
    }

    @Override
    public void run() {
        this.revalidate();
        this.repaint();
    }
}