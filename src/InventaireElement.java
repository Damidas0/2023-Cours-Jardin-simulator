import java.awt.GridLayout;
import java.awt.event.HierarchyBoundsAdapter;

import javax.swing.JLabel;

public class InventaireElement extends JLabel{
    private int id;
    private int qte;
    private boolean estAmenagement;
    JLabel qteA;
    
    public InventaireElement(int id, int qte, boolean estAmenagement){
        this.id = id;
        this.qte = qte;
        this.setLayout(new GridLayout());
        ImageGraphique img = new ImageGraphique(100, 100, id);

        if(estAmenagement) img.changerImgAmenagement(id);
        img.setVisible(true);
        this.add(img);
        this.qteA = new JLabel(String.valueOf(qte));
        this.add(qteA);
        this.setVisible(true); 
    }

    public int getId() {
        return this.id;
    }

    public void majQte(Integer integer) {
        this.qteA.setText(String.valueOf(integer));
    }
}
