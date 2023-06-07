import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;


public class MenuPanel extends JPanel{
    private JButton inventaire;
    private JButton shop;
    private JButton autre;

    private Vue v;

    
    public MenuPanel(Vue v) {
        this("Inventaire", "Shop", "Autre", v);
    }

    public MenuPanel(String nomBtInventaire, String nomBtShop, String nomBtAutre, Vue v) {
        this.setLayout(new GridLayout(1, 3));
        
        this.inventaire = new JButton(nomBtInventaire);
        this.shop = new JButton(nomBtShop);
        this.autre = new JButton(nomBtAutre);
        this.v = v;
        
        this.add(this.inventaire);
        this.add(this.shop);
        this.add(this.autre);

        this.setBackground(Color.RED);
        this.setVisible(true);
    }
}
