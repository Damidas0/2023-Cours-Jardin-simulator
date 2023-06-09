
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ShopElement extends JLabel{
    protected boolean estPermanent;
    public String nom; 
    protected int id;

    protected HashMap<Integer, Integer> prix;  
    

    public ShopElement(){
        
    }

    public ShopElement(String nom, int id, boolean estPermanent, HashMap<Integer, Integer> prix){
        this.nom = nom;
        this.id = id;
        this.estPermanent = estPermanent;
        this.prix = prix;

        this.setLayout(new GridLayout(1,6));
        ImageGraphique imPlante = new ImageGraphique(100, 100, id);
        imPlante.setVisible(true);
        this.add(imPlante);


        this.add(new JLabel(nom));

        JLabel lprix = new JLabel();
        lprix.setLayout(new GridLayout(3,2));

        for (Integer key : prix.keySet()) {
            ImageGraphique imPrix = new ImageGraphique(10,10, key);
            Image im = imPrix.getImage(); 
            im = im.getScaledInstance(50,50, Image.SCALE_DEFAULT);

            ImageIcon imF = new ImageIcon(im);
            this.add(new JLabel(imF));
            this.add(new JLabel(String.valueOf(this.prix.get(key))));
        }
        //this.add(lprix);
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
