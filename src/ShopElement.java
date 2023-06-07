
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ShopElement extends JLabel{
    private boolean estPermanent;
    private String nom; 

    private HashMap<Integer, Integer> prix;  
    

    public ShopElement(){
        
    }

    public ShopElement(String nom, boolean estPermanent, HashMap<Integer, Integer> prix){
        this.nom = nom;
        this.estPermanent = estPermanent;
        this.prix = prix;

        this.setLayout(new GridLayout(1,3));
    }

    
    
    
    public HashMap<Integer, Integer> getPrix(){
        return this.prix;
    }
    

    public boolean estPermanent(){
        return this.estPermanent;
    }
}
