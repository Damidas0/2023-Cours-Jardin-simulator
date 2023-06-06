import java.util.HashMap;
import java.util.Observable;

public class Potager extends Observable{
    final int HAUTEUR = 10;
    final int LARGEUR = 10;

    final private Graine[] LISTE_GRAINE = {
            new Graine("debug", 0, 0,0,0,0,200,1,1,3),
            new Graine("salade", 1, 65, 50, 50, 15, 10, 1, 2,4),
            new Graine("carotte", 2, 50, 50, 50, 25, 15, 1, 2,3),
            new Graine("patate", 3, 65, 65, 45, 35, 15, 1, 2,3),
            new Graine("ail", 4, 75, 25, 50, 25, 15, 1, 1,5),
    };

    private int vitesse;

    private SystemeMeteo meteo;

    private Case cases[][];

    private int idGraineSelectionner;

    private HashMap<Integer, Integer> stock;

    private ConditionEnvironementale conditionGlobale;

    public Potager() {
        this.vitesse = 1;
        this.cases = new Case[HAUTEUR][LARGEUR];

        for (int i = 0; i < HAUTEUR; i++) {
            for (int j = 0; j < LARGEUR; j++) {
                this.cases[i][j] = new Case();
            }
        }

        this.stock = new HashMap<>();
        idGraineSelectionner = -1;

        this.meteo = new SystemeMeteo();
        this.conditionGlobale = meteo.getCondition();
        //TODO relier le systeme de météo et le potager
    }


    public void selectionnerGraine(int idGraine){
        if (this.stock.get(idGraine) != null){
            this.idGraineSelectionner = idGraine;
        }
    }

    public void ajouterGraineStock(Graine graine, int quantite){
        ajouterGraineStock(graine.getId(), quantite);
    }

    public void ajouterGraineStock(int idGraine, int quantite){
        if(quantite > 0){
            this.stock.merge(idGraine, quantite, (prev, one) -> prev + one);
        }else {
            System.out.println("On ne peut pas ajouter un nombre < 0 de plante");
        }
    }

    public void enleverPlante(int idGraine, int quantite){
        if(this.stock.get(idGraine) != null){
            if(this.stock.get(idGraine) - quantite >= 0){
                this.stock.put(idGraine, this.stock.get(idGraine) - quantite);
            } else {
                System.out.println("Pas assez de plante "+ idGraine+ "vous en avez "+this.stock.get(idGraine)+ "et vous voulez en enlenver "+ quantite);
            }
        }else{
            System.out.println("Vous n'avez pas la plante "+idGraine+" en stock");
        }
    }


    public void planterSelection(int yCase, int xCase) {
        if(this.idGraineSelectionner != -1) {
            planterStock(new Plante(this.LISTE_GRAINE[this.idGraineSelectionner]), yCase, xCase);
        }else{
            System.out.println("Aucune graine n'est séléctionnée.");
        }
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
                    ajouterGraineStock(tmp.getIdPlante(), tmp.recolter());

                    this.cases[yCase][xCase] = new Case();
                }
            }
        }
    }

    public int niveauDeSurvie(int yCase, int xCase){
        if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
            if (this.cases[yCase][xCase] instanceof Culture) {
                Culture tmp = (Culture)this.cases[yCase][xCase];
                return tmp.niveauDeSurvie();
            }
        }

        return -1;
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


    public String getNomPlante(int yCase, int xCase) {
        if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
            if (estUneculture(yCase, xCase)) {
                Culture tmp = (Culture)this.cases[yCase][xCase]; 
                return (tmp.getNomPlante());
            }
        }
        return "-1";
    }

    public int getInfoEau(int yCase, int xCase) {
        if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
            if (estUneculture(yCase, xCase)) {
                Culture tmp = (Culture)this.cases[yCase][xCase]; 
                return (tmp.getInfoEau());
            }
        }
        return -1;
    }

    public int getInfoTemp(int yCase, int xCase) {
        if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
            if (estUneculture(yCase, xCase)) {
                Culture tmp = (Culture)this.cases[yCase][xCase]; 
                return (tmp.getInfoTemp());
            }
        }
        return -1;
    }

    public int getInfoSoleil(int yCase, int xCase) {
        if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
            if (estUneculture(yCase, xCase)) {
                Culture tmp = (Culture)this.cases[yCase][xCase]; 
                return (tmp.getInfoSoleil());
            }
        }
        return -1;
    }


    public void afficherStock(){
        System.out.println("Stock: ");
        for (Integer key : this.stock.keySet()) {
            System.out.println("    - id: "+key+", quantité: "+this.stock.get(key));
        }
    }

    public void afficher() {
        System.out.println("-------------------");
        System.out.println("AFFICHAGE DE POTAGER");
        System.out.println("HAUTEUR: " + HAUTEUR);
        System.out.println("LARGEUR: " + LARGEUR);
        System.out.println("vitesse: " + this.vitesse);

        System.out.println("idGraineSelectionner: " + this.idGraineSelectionner);
        this.afficherStock();

        System.out.println("condition globale: " + this.conditionGlobale);
        System.out.println("-------------------");
    }

}
