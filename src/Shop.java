
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Shop extends JPanel{
    private List<ShopElementCulture> cultureListe; 
    private List<ShopElementAmenagement> amenagementListe;

    private Potager p;


    public Shop(){
        this(new ArrayList<ShopElementCulture>(), 
            new ArrayList<ShopElementAmenagement>(), 
            new Potager());
        
    }

    public Shop(List<ShopElementCulture> cultures, Potager p){
        this(cultures, new ArrayList<ShopElementAmenagement>(), p);
    }

    public Shop(List<ShopElementCulture> cultures, List<ShopElementAmenagement> amenagements, Potager p){
        this.cultureListe = cultures;
        this.amenagementListe = amenagements;
        this.p = p;

        this.setLayout(new GridLayout(3,1));


        int tailleListe = this.cultureListe.size() + this.amenagementListe.size();

        /*GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight=1;
        c.weightx = 1;
        //c.weighty = ;*/
        
        for(int i = 0; i<this.cultureListe.size(); i++){
            System.out.println(i);
            ShopElement sc = (ShopElement) this.cultureListe.get(i);
            //c.gridx = i;
            sc.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e){
                    System.out.println("ndqzjdzqoidjzqodoiqjd");

                    achat(sc);
                    System.out.println("ndqzjdzqoidjzqodoiqjd");
                }
            });
            //culturePanel.setBounds(0,0,100,100);
            sc.setVisible(true);
            this.add(this.cultureListe.get(i));//,c);
        }

        this.setVisible(true);
    }



    private void achat(ShopElement s){
        if(estAchetable(s.getPrix(), this.p.getStock()) != 1 ){
            //this.inventaire.ajouter(sc.Achete())
            this.p.ajouterGraineStock(s.getId(), 1);
            this.majShop((ShopElement) s);
        }
    }
    
    private int estAchetable(HashMap<Integer, Integer> prix, HashMap<Integer, Integer> stock) {
        for (Integer key : prix.keySet()) {
            if(stock.get(key) < prix.get(key)) return -1;
        }
        return 1;
    }

    private void majShop(ShopElement s) {
        if(!s.estPermanent()){
            remove(s);
        }
    }

}
