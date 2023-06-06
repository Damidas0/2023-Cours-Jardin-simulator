
import java.awt.GridLayout;
import java.util.List;

import javax.swing.JLabel;

public class ShopElement extends JLabel{
    private boolean isPermanent;
    private String nom; 

    private List<Graine> prix;
    

    public ShopElement(){
        
    }

    public ShopElement(String nom, boolean isPermanent, List<Graine> prix){
        this.nom = nom;
        this.isPermanent = isPermanent;
        this.prix = prix;

        this.setLayout(new GridLayout(1,3));
        
        
    }
}
