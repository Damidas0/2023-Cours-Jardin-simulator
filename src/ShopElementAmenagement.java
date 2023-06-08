import java.awt.GridLayout;
import java.awt.Image;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ShopElementAmenagement extends ShopElement{
    public ShopElementAmenagement(String nom, int id, boolean estPermanent, HashMap<Integer, Integer> prix){
        this.nom = nom;
        this.id = id;
        this.estPermanent = estPermanent;
        this.prix = prix;

        this.setLayout(new GridLayout(1,6));
        ImageGraphique im = new ImageGraphique(100, 100, id);
        im.changerImgAmenagement(id);
        im.setVisible(true);
        this.add(im);


        this.add(new JLabel(nom));

        JLabel lprix = new JLabel();
        lprix.setLayout(new GridLayout(3,2));

        for (Integer key : prix.keySet()) {
            ImageGraphique imPrix = new ImageGraphique(10,10, key);
            Image imP = imPrix.getImage(); 
            imP = imP.getScaledInstance(50,50, Image.SCALE_DEFAULT);

            ImageIcon imF = new ImageIcon(imP);
            this.add(new JLabel(imF));
            this.add(new JLabel(String.valueOf(this.prix.get(key))));
        }
        //this.add(lprix);
    }
}
