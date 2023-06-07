import java.awt.GridLayout;
import java.util.*;

import javax.swing.JPanel;




public class InventairePanel extends JPanel {
    private HashMap<Integer, Integer> stock;  


    public InventairePanel(){
        
    }

    public InventairePanel(HashMap<Integer, Integer> stockInit){
        this.setLayout(new GridLayout(5,4));
        this.stock = stockInit;
        for (Integer key : stock.keySet()) {
            //charger les images dans l'inventaire
        }
        
    }




}
