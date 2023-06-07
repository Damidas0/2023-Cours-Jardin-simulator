
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ShopElement extends JLabel{
    private boolean estPermanent;
    private String nom; 
    private int id;

    private HashMap<Integer, Integer> prix;  
    

    public ShopElement(){
        
    }

    public ShopElement(String nom, int id, boolean estPermanent, HashMap<Integer, Integer> prix){
        this.nom = nom;
        this.id = id;
        this.estPermanent = estPermanent;
        this.prix = prix;

        this.setLayout(new GridLayout(1,3));
        ImageGraphique imPlante = new ImageGraphique(100, 100, id);
        imPlante.setVisible(true);
        this.add(imPlante);
                //TODO:verif taille image


        this.add(new JLabel(nom));

        JLabel lprix = new JLabel();
        lprix.setLayout(new GridLayout(3,2));

        for (Integer key : prix.keySet()) {
            ImageGraphique imPrix = new ImageGraphique(10,10, key);
            imPrix.setVisible(true);
            lprix.add(imPrix);
            lprix.add(new JLabel(String.valueOf(this.prix.get(key))));
        }
        this.add(lprix);
    }
    

    
    
    
    public HashMap<Integer, Integer> getPrix(){
        return this.prix;
    }
    

    public boolean estPermanent(){
        return this.estPermanent;
    }

    public int getId() {
        return id;
    }
    public static void main(String[] args) {
        Tileset tileset = new Tileset();
        JFrame f = new JFrame();
        HashMap<Integer, Integer> prix = new HashMap<>();
        prix.put(1,3);
        prix.put(2,3);
        prix.put(3,3);
        

        f.setSize(1000, 1000);
        f.add(new ShopElementCulture("Carotte", 2, prix));
        f.setVisible(true);
    }
}
