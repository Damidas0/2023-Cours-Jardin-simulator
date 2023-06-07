import java.awt.GridLayout;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JPanel;




public class InventairePanel extends JPanel {
    private HashMap<Integer, Integer> stock;  


    public InventairePanel(){
        
    }

    public InventairePanel(HashMap<Integer, Integer> stockInit){
        this.setLayout(new GridLayout(5,4));
        this.stock = stockInit;
        for (Integer key : stock.keySet()) {
            JLabel invCase = new JLabel();
            invCase.setBounds(0, 0, 100, 100);


            //charger les images dans l'inventaire
        }
        /*for (int i = 0; i < HAUTEUR; i++) {
            for (int j = 0; j < LARGEUR; j++) {
                this.cases[i][j] = new Case();
            }
        } */
        
    }




}
