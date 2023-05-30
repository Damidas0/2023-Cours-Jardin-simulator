import java.util.HashMap;
import java.util.Observable;

public class Potager extends Observable implements Runnable {
    final int HAUTEUR = 10;
    final int LARGEUR = 10;

    private int vitesse;

    private Case cases[][];

    private int idPlanteSelectionner;

    private HashMap<Integer, Integer> stock;

    private ConditionEnvironementale conditionGlobale;

    public Potager() {
        Ordonnanceur.getOrdonnanceur().addRunable(this);
        this.conditionGlobale = new ConditionEnvironementale(0, 0, 0);
        this.vitesse = 1;
        this.cases = new Case[HAUTEUR][LARGEUR];

        for (int i = 0; i < HAUTEUR; i++) {
            for (int j = 0; j < LARGEUR; j++) {
                this.cases[i][j] = new Case();
            }
        }

        this.stock = new HashMap<>();
        idPlanteSelectionner = -1;
    }


    public void selectionnerPlante(int idPlante){
        if (this.stock.get(idPlante) != null){
            this.idPlanteSelectionner = idPlante;
        }
    }

    public void ajouterPlanteStock(Plante plante, int quantite){
        ajouterPlanteStock(plante.getId(), quantite);
    }

    public void ajouterPlanteStock(int idPlante, int quantite){
        if(quantite > 0){
            if(this.stock.get(idPlante) != null){
                this.stock.put(idPlante, this.stock.get(idPlante) + quantite);
            }else{
                this.stock.put(idPlante, quantite);
            }
        }else {
            System.out.println("On ne peut pas ajouter un nombre < 0 de plante");
        }
    }

    public void enleverPlante(int idPlante, int quantite){
        if(this.stock.get(idPlante) != null){
            if(this.stock.get(idPlante) - quantite >= 0){
                this.stock.put(idPlante, this.stock.get(idPlante) - quantite);
            } else {
                System.out.println("Pas assez de plante "+ idPlante+ "vous en avez "+this.stock.get(idPlante)+ "et vous voulez en enlenver "+ quantite);
            }
        }else{
            System.out.println("Vous n'avez pas la plante "+idPlante+" en stock");
        }
    }


    public void planterSelection(int yCase, int xCase) {

        //TODO mettre la bonne plante ...
        //comment on fait pour le stock de plante
        //un classe mere de palnte avec - de chose genre graine
        Plante pla = new Plante();

        planterStock(pla, yCase, xCase);
    }

    public void planterStock(Plante plante, int yCase, int xCase) {
        if (this.stock.get(plante.getId()) != null){
            if (this.stock.get(plante.getId()) > 0){
                planter(plante, yCase, xCase);
                enleverPlante(plante.getId(), 1);
            }else {
                System.out.println("Vous n'avez plus la plante "+plante.getId()+" en stock");
            }
        }else {
            System.out.println("Vous n'avez pas la plante "+plante.getId()+" en stock");
        }
    }

    public void planter(Plante plante, int yCase, int xCase) {
        if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
            if (!(this.cases[yCase][xCase] instanceof Culture)) {
                this.cases[yCase][xCase] = new Culture(plante, this.conditionGlobale);
            }
        }
    }

    public void recolter(int yCase, int xCase) {
        if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
            if (this.cases[yCase][xCase] instanceof Culture) {
                if(getDeveloppement(yCase,xCase) == 100){
                    Culture tmp = (Culture)this.cases[yCase][xCase];

                    // on met à jour le stock
                    ajouterPlanteStock(tmp.getIdPlante(), tmp.recolter());

                    this.cases[yCase][xCase] = new Case();
                }
            }
        }
    }

    public boolean estUneculture(int yCase, int xCase) {
        //System.out.println("Y : " + yCase + "X : " + xCase);
        if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
            //this.cases[yCase][xCase].afficher();
            return this.cases[yCase][xCase] instanceof Culture;
        }
        return false;
    }

    public int getDeveloppement(int yCase, int xCase){
        if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
            //TODO:aucasouarevoir
            if (estUneculture(yCase, xCase)) {
                Culture tmp = (Culture)this.cases[yCase][xCase]; 
                return (tmp.getDeveloppement());
            }
        }
        return -1;
    }

    public boolean estPoussee(int yCase, int xCase){
        if (this.getDeveloppement(yCase, xCase) == 100) return true;
        return false; 
    }

    public void afficher() {
        System.out.println("-------------------");
        System.out.println("AFFICHAGE DE POTAGER");
        System.out.println("HAUTEUR: " + HAUTEUR);
        System.out.println("LARGEUR: " + LARGEUR);
        System.out.println("vitesse: " + this.vitesse);

        System.out.println("idPlanteSelectionner: " + this.idPlanteSelectionner);
        System.out.println("Stock: ");
        for (Integer key : this.stock.keySet()) {
            System.out.println("    - id: "+key+", quantité: "+this.stock.get(key));
        }

        System.out.println("condition globale: " + this.conditionGlobale);
        System.out.println("-------------------");
    }

    @Override
    public void run() {
        //System.out.println("Je suis dans le run de potager");

    }
}
