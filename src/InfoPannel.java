import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class InfoPannel extends JPanel {
    private JLabel nomPlante;
    private JLabel infoEau;
    private JLabel infoSoleil;
    private JLabel infoTemperature;

    public InfoPannel() {
        this("Debug", "-1", "-1", "-1");
    }

    public InfoPannel(String nomPlante, String infoEau, String infoSoleil, String infoTemperature) {
        this.setLayout(new GridLayout(1, 4));
        this.nomPlante = new JLabel(nomPlante);
        this.infoEau = new JLabel(infoEau);
        this.infoSoleil = new JLabel(infoSoleil);
        this.infoTemperature = new JLabel(infoTemperature);

        this.add(this.nomPlante);
        this.add(this.infoEau);
        this.add(this.infoSoleil);
        this.add(this.infoTemperature);

        this.setBackground(Color.RED);
        this.setVisible(true);
    }

    public void majInfoPanel(String nomPlante, int infoEau, int infoSoleil, int infoTemp) {
        this.nomPlante.setText(nomPlante);
        this.infoEau.setText(String.valueOf(infoEau));
        this.infoSoleil.setText(String.valueOf(infoSoleil));
        this.infoTemperature.setText(String.valueOf(infoTemp));
    }

}