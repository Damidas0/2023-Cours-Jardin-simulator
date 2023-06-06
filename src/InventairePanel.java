import java.awt.GridLayout;
import java.util.*;

import javax.swing.JPanel;




public class InventairePanel extends JPanel {
    private Graine[][] lgraine;


    public InventairePanel(){
        
    }

    public InventairePanel(List<Graine> lGraine){
        this.setLayout(new GridLayout(5,4));
        
        
    }




}
