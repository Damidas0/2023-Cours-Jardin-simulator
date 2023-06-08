
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
    private boolean estShopCulture; 
    private List<ShopElementCulture> cultureListe; 
    private List<ShopElementAmenagement> amenagementListe;

    private Potager p;


    public Shop(int qui, Potager p){
        //Jeu de base par défaut
        //init
        this.p = p;
        this.estShopCulture = (qui == 1);
        List<ShopElementCulture> l = new ArrayList<ShopElementCulture>();
        List<ShopElementAmenagement> lA = new ArrayList<ShopElementAmenagement>();


        if(qui == 1 ){
            HashMap<Integer, Integer> prixCarotte = new HashMap<>();

            //Ajout carotte
            prixCarotte.put(1,6);
            l.add(new ShopElementCulture("Carotte", 2, prixCarotte));

            HashMap<Integer, Integer> prixPatate = new HashMap<>();
            prixPatate.put(1,2);
            prixPatate.put(2,4);
            l.add(new ShopElementCulture("Patate", 3 , prixPatate));

            HashMap<Integer, Integer> prixAil = new HashMap<>();
            prixAil.put(1,3);
            prixAil.put(2,3);
            prixAil.put(3,5);

            l.add(new ShopElementCulture("Ail", 4 , prixAil));

            HashMap<Integer, Integer> prixEpindard = new HashMap<>();
            prixEpindard.put(2,3);
            prixEpindard.put(3,3);
            prixEpindard.put(4,5);

            l.add(new ShopElementCulture("Epinard", 5 , prixEpindard));

            HashMap<Integer, Integer> prixCourge = new HashMap<>();

            prixCourge.put(3,3);
            prixCourge.put(4,3);
            prixCourge.put(5,5);

            l.add(new ShopElementCulture("Courge", 6 , prixCourge));

        }else{
            HashMap<Integer, Integer> prixFlaque = new HashMap<Integer, Integer>();

            //Ajout carotte
            prixFlaque.put(1,1);
            lA.add(new ShopElementAmenagement("Flaque", 0, true, prixFlaque));

            HashMap<Integer, Integer> prixSCheveux = new HashMap<>();
            prixSCheveux.put(2,1);
            lA.add(new ShopElementAmenagement("Séche cheveux", 1 , true, prixSCheveux));

            HashMap<Integer, Integer> prixRadiateur = new HashMap<>();
            prixRadiateur.put(1,1);
            prixRadiateur.put(2,1);

            lA.add(new ShopElementAmenagement("Radiateur", 2 , true, prixRadiateur));

            HashMap<Integer, Integer> prixClim = new HashMap<>();
            prixClim.put(3,2);

            lA.add(new ShopElementAmenagement("Climatisation", 3 , true, prixClim));

            HashMap<Integer, Integer> prixMiroir = new HashMap<>();
            prixMiroir.put(6,1);

            lA.add(new ShopElementAmenagement("Miroir", 4 , true, prixMiroir));

            HashMap<Integer, Integer> prixParasol = new HashMap<>();
            prixParasol.put(5,1);
            prixParasol.put(4,1);

            lA.add(new ShopElementAmenagement("Parasol", 4 , true, prixParasol));

        }
        this.cultureListe = l;
        this.amenagementListe = lA;

        

        int tailleListe = this.cultureListe.size() + this.amenagementListe.size();


        this.setLayout(new GridLayout(tailleListe,1));


        
        
        for(int i = 0; i<this.cultureListe.size(); i++){
            System.out.println(i);
            ShopElement sc = (ShopElement) this.cultureListe.get(i);
            //c.gridx = i;
            sc.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e){
                    System.out.println("Actuellement dans la fonction");
                    achat(sc);
                }
            });
            sc.setVisible(true);
            this.add(this.cultureListe.get(i));//,c);
        }

        for(int i = 0; i<this.amenagementListe.size(); i++){
            System.out.println(i);
            ShopElement sc = (ShopElement) this.amenagementListe.get(i);
            //c.gridx = i;
            sc.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e){

                    achat(sc);
                }
            });
            sc.setVisible(true);
            this.add(this.amenagementListe.get(i));//,c);
        }

        this.setVisible(true);


        /*prix.put(2,3);
        prix.put(3,3);
        
        l.add(new ShopElementCulture("Carotte", 2, prix));
        l.add(new ShopElementCulture("Carotte", 2, prix));
        l.add(new ShopElementCulture("Carotte", 2, prix));

        l.add(new ShopElementCulture("Carotte", 2, prix));

        l.add(new ShopElementCulture("Carotte", 2, prix));
        /*this(new ArrayList<ShopElementCulture>(), 
            new ArrayList<ShopElementAmenagement>(), 
            new Potager());*/
        
    }

    public Shop(List<ShopElementCulture> cultures, Potager p){
        this(cultures, new ArrayList<ShopElementAmenagement>(), p);
    }

    public Shop(List<ShopElementCulture> cultures, List<ShopElementAmenagement> amenagements, Potager p){
        this.cultureListe = cultures;
        this.amenagementListe = amenagements;
        this.p = p;


        int tailleListe = this.cultureListe.size() + this.amenagementListe.size();


        this.setLayout(new GridLayout(tailleListe,1));


        
        
        for(int i = 0; i<this.cultureListe.size(); i++){
            System.out.println(i);
            ShopElement sc = (ShopElement) this.cultureListe.get(i);
            //c.gridx = i;
            sc.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e){

                    achat(sc);
                }
            });
            sc.setVisible(true);
            this.add(this.cultureListe.get(i));//,c);
        }

        for(int i = 0; i<this.amenagementListe.size(); i++){
            System.out.println(i);
            ShopElement sc = (ShopElement) this.amenagementListe.get(i);
            //c.gridx = i;
            sc.addMouseListener(new MouseAdapter(){
                public void mouseClicked(MouseEvent e){
                    System.out.println("dnqzodqndqzjndqjdnqzkj");
                    achat(sc);
                }
            });
            sc.setVisible(true);
            this.add(this.amenagementListe.get(i));//,c);
        }

        this.setVisible(true);
    }



    private void achat(ShopElement s){
        if(estAchetable(s.getPrix(), this.p.getStock()) != -1 ){
            if(estShopCulture){
                this.p.ajouterGraineStock(s.getId(), 1);
                
            }else{
                this.p.ajouterAmenagementStock(s.getId(), 1);
            }
            for(Integer key : s.getPrix().keySet()){
                this.p.enleverPlante(key, s.getPrix().get(key));
            }
            
            this.majShop((ShopElement) s);
        }
    }
    
    private int estAchetable(HashMap<Integer, Integer> prix, HashMap<Integer, Integer> stock) {
        for (Integer key : prix.keySet()) {
            if(!stock.containsKey(key)) return -1;
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
