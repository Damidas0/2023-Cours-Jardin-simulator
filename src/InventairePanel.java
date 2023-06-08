import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JPanel;




public class InventairePanel extends JPanel {
    private HashMap<Integer, Integer> stock;
    private HashMap<Integer, InventaireElement> lj;
    private HashMap<Integer, InventaireElement> listeAmenagement;
    private Potager p;


   

    public InventairePanel(HashMap<Integer, Integer> stockInit, HashMap<Integer, Integer> stockAmenagementInit, Potager p){
        this.p = p;
        this.stock = stockInit;
        this.setLayout(new GridLayout(5,4));
        this.stock = stockInit;
        this.lj = new HashMap<Integer, InventaireElement>();
        this.listeAmenagement = new HashMap<Integer, InventaireElement>();


        
        for (Integer key : stockAmenagementInit.keySet()) {
            
            InventaireElement iv = new InventaireElement(key, stockAmenagementInit.get(key), true);
            iv.setVisible(true);

            iv.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e){
                    selectAmenagement(iv.getId());
                }
            });
            listeAmenagement.put(key, iv);


            this.add(iv);
        }

        for (Integer key : stock.keySet()) {
            InventaireElement iv = new InventaireElement(key, stock.get(key), false);
            iv.setVisible(true);

            iv.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e){

                    selectGraine(iv.getId());
                }
            });
            lj.put(key, iv);


            this.add(iv);
        }

        this.setVisible(true);
    }

    private void selectGraine(int id){
        this.p.selectionnerGraine(id);
    }

    private void selectAmenagement(int id){
        this.p.selectionnerAmenagement(id);
    }

    public void majInventaire(HashMap<Integer, Integer> nouveauStock, HashMap<Integer, Integer> nStockAmenagement){
        //this.stock = stock;
        for (Integer key : nouveauStock.keySet()) {
            if(this.lj.containsKey(key)){
                this.lj.get(key).majQte(nouveauStock.get(key));
            }else{
                InventaireElement iv = new InventaireElement(key, stock.get(key), false);
                this.lj.put(key, iv);
                iv.setVisible(true);
    
                iv.addMouseListener(new MouseAdapter(){
                    public void mouseClicked(MouseEvent e){
    
                        selectGraine(iv.getId());
                    }
                });
                this.add(iv);
            }
        }

        for (Integer key : nStockAmenagement.keySet()) {
            if(this.listeAmenagement.containsKey(key)){
                this.listeAmenagement.get(key).majQte(nStockAmenagement.get(key));
            }else{
                ImageGraphique img = new ImageGraphique(100, 100, key);
                img.setVisible(true);
                InventaireElement iv = new InventaireElement(key, stock.get(key), true);
                this.listeAmenagement.put(key, iv);
                iv.setVisible(true);
    
                iv.addMouseListener(new MouseAdapter(){
                    public void mouseClicked(MouseEvent e){
    
                        selectGraine(iv.getId());
                    }
                });
                this.add(iv);
            }
        }
    }
    




}


