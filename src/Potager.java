import java.util.Arrays;
import java.util.HashMap;
import java.util.Observable;

public class Potager extends Observable{
    final int HAUTEUR = 10;
    final int LARGEUR = 10;

    static int vitesse;
    final private Graine[] LISTE_GRAINE = {
            new Graine("debug", 0, 0,0,0,5,200,1,1,3),
            new Graine("salade", 1, 65, 50, 50, 25, 15, 3, 2,4),
            new Graine("carotte", 2, 50, 50, 50, 25, 15, 3, 2,3),
            new Graine("patate", 3, 65, 65, 45, 35, 15, 3, 2,3),
            new Graine("ail", 4, 75, 25, 50, 25, 15, 2, 1,5),
            new Graine("epinard", 5, 60, 35, 45, 20, 15, 2, 1,4),
            new Graine("courge", 6, 75, 40, 55, 25, 15, 2, 1,4),
    };

    private SystemeMeteo meteo;

    private Case cases[][];

    private int idGraineSelectionner;
    private int idAmenagementSelectionner;

    private HashMap<Integer, Integer> stockGraine;
    private HashMap<Integer, Integer> stockAmenagemnt;

    public Potager() {
        Potager.vitesse = 1;
        this.cases = new Case[HAUTEUR][LARGEUR];

        for (int i = 0; i < HAUTEUR; i++) {
            for (int j = 0; j < LARGEUR; j++) {
                this.cases[i][j] = new Case();
            }
        }

        this.stockGraine = new HashMap<>();
        idGraineSelectionner = -1;

        this.stockAmenagemnt = new HashMap<>();
        this.stockAmenagemnt.put(0,1);
        idAmenagementSelectionner = 0;


        this.meteo = new SystemeMeteo();
    }


    public void selectionnerGraine(int idGraine){
        if (this.stockGraine.get(idGraine) != null){
            this.idGraineSelectionner = idGraine;
        }else{
            System.out.println("vous n'avez pas la graine d'id : "+ idGraine);
        }
    }

    public void selectionnerAmenagement(int idAmenagement){
        if (this.stockAmenagemnt.get(idAmenagement) != null){
            this.idAmenagementSelectionner = idAmenagement;
        }else{
            System.out.println("il n'existe pas d'aménagement : "+idAmenagement);
        }
    }


    public void ajouterGraineStock(Graine graine, int quantite){
        ajouterGraineStock(graine.getId(), quantite);
    }

    public void ajouterAmenagementStock(int type, int quantite){
        if(quantite > 0){
            this.stockAmenagemnt.merge(type, quantite, (prev, one) -> prev + one);
        }else {
            System.out.println("On ne peut pas ajouter un nombre < 0 d'aménagement");
        }
    }

    public void ajouterGraineStock(int idGraine, int quantite){
        if(quantite > 0){
            this.stockGraine.merge(idGraine, quantite, (prev, one) -> prev + one);
        }else {
            System.out.println("On ne peut pas ajouter un nombre < 0 de plante");
        }
    }

    public boolean placerAmenagementSelectionner(int yCase, int xCase){
        return placerAmenagement(yCase,xCase,this.idAmenagementSelectionner);
    }

    private boolean placerAmenagement(int yCase, int xCase, int idAmenagement){
        if (this.stockAmenagemnt.get(idAmenagement) > 0){
            if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
                if (caseLibre(yCase,xCase)) {
                    this.cases[yCase][xCase] = new Amenagement(idAmenagement);
                    enleverAmenagement(idAmenagement, 1);

                    //on répercute les changements
                    repercuterAmenagements();
                    return true;
                }
            }
        }else {
            System.out.println("Vous n'avez plus l'aménagement' "+ idAmenagement +" dans votre stock d'aménagement");
            return false;
        }
        return false;
    }

    public void enleverAmenagement(int idAmenagement, int quantite){
        if(this.stockAmenagemnt.get(idAmenagement) != null){
            if(this.stockAmenagemnt.get(idAmenagement) - quantite >= 0){
                this.stockAmenagemnt.put(idAmenagement, this.stockAmenagemnt.get(idAmenagement) - quantite);
            } else {
                System.out.println("Pas assez d'aménagement "+ idAmenagement+ "vous en avez "+this.stockAmenagemnt.get(idAmenagement)+ "et vous voulez en enlenver "+ quantite);
            }
        }else{
            System.out.println("Vous n'avez pas l'aménagement "+idAmenagement+" en stock");
        }
    }

    public void retirerAmenagement(int yCase, int xCase){
        if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
            if (this.cases[yCase][xCase] instanceof Amenagement) {
                Amenagement tmp = (Amenagement) this.cases[yCase][xCase];
                ajouterAmenagementStock(tmp.getType(), 1);

                this.cases[yCase][xCase] = new Case();

                //on répercute les changements
                repercuterAmenagements();
            }
        }

        //afficherStock();
    }

    public int getIdAmenagement(int yCase, int xCase){
        if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
            if (this.cases[yCase][xCase] instanceof Amenagement) {
                Amenagement tmp = (Amenagement) this.cases[yCase][xCase];
                return tmp.getType();
            }
        }
        return -1;
    }

    private void repercuterAmenagements(){
        for (int i = 0; i <this.HAUTEUR ; i++) {
            for (int j = 0; j < this.LARGEUR; j++) {
                // on rentabilise les protections des cultures
                if (this.cases[i][j] instanceof Culture) {
                    Culture tmp = (Culture)this.cases[i][j];
                    tmp.resetProtectionEnvironemental();
                }
                // on recherche tous les aménagements
                if(this.cases[i][j] instanceof Amenagement){
                    Amenagement tmp = (Amenagement) this.cases[i][j];
                    //on parcours les 8 cases autours
                    for (int k = -1; k < 1; k++) {
                        for (int l = -1; l < 1; l++) {
                            if(k!=0 || l!=0){
                                //on vérifie qu'on sort pas du tableau
                                if(i+k >=0 && i+k<HAUTEUR && j+l>=0 && j+l<LARGEUR){
                                    changerProtectionEnvironemental(i+k, j+l, tmp.getType(), true);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public void changerProtectionEnvironemental(int yCase, int xCase, int type, boolean bool) {
        if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
            if (estUneculture(yCase, xCase)) {
                Culture tmp = (Culture)this.cases[yCase][xCase];
                tmp.changerProtectionEnvironemental(type, bool);
                tmp.changerProtectionEnvironemental(type, bool);
            }
        }
    }

    public void enleverPlante(int idGraine, int quantite){
        if(this.stockGraine.get(idGraine) != null){
            if(this.stockGraine.get(idGraine) - quantite >= 0){
                this.stockGraine.put(idGraine, this.stockGraine.get(idGraine) - quantite);
            } else {
                System.out.println("Pas assez de plante "+ idGraine+ "vous en avez "+this.stockGraine.get(idGraine)+ "et vous voulez en enlenver "+ quantite);
            }
        }else{
            System.out.println("Vous n'avez pas la plante "+idGraine+" en stockGraine");
        }
    }


    public boolean planterSelection(int yCase, int xCase) {
        if(this.idGraineSelectionner != -1) {
            return planterStock(new Plante(this.LISTE_GRAINE[this.idGraineSelectionner]), yCase, xCase);
        }else{
            System.out.println("Aucune graine n'est séléctionnée.");
            return false;
        }
    }

    public boolean planterStock(Plante plante, int yCase, int xCase) {
        if (this.stockGraine.get(plante.getId()) != null){
            if (this.stockGraine.get(plante.getId()) > 0){
                planter(plante, yCase, xCase);
                enleverPlante(plante.getId(), 1);
                return true;
            }else {
                System.out.println("Vous n'avez plus la plante "+plante.getId()+" en stockGraine");
                return false;
            }
        }else {
            System.out.println("Vous n'avez pas la plante "+plante.getId()+" en stockGraine");
            return false;
        }
    }

    public void planter(Plante plante, int yCase, int xCase) {
        if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
            if (caseLibre(yCase,xCase)) {
                this.cases[yCase][xCase] = new Culture(plante);
                repercuterAmenagements();
            }
        }
    }

    public boolean caseLibre(int yCase, int xCase){
        return !(this.cases[yCase][xCase] instanceof Culture) && !(this.cases[yCase][xCase] instanceof Amenagement);
    }

    public boolean estVivante(int yCase, int xCase) {
        if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
            if (this.cases[yCase][xCase] instanceof Culture) {
                Culture tmp = (Culture) this.cases[yCase][xCase];
                return tmp.estVivante();
            }
        }
        return false;
    }

    public void recolter(int yCase, int xCase) {
        if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
            if (this.cases[yCase][xCase] instanceof Culture) {
                if(getDeveloppement(yCase,xCase) == 100){
                    Culture tmp = (Culture)this.cases[yCase][xCase];

                    // on met à jour le stockGraine
                    ajouterGraineStock(tmp.getIdPlante(), tmp.recolter());

                    this.cases[yCase][xCase] = new Case();
                }
            }
        }
    }

    public void arracher(int yCase, int xCase) {
        if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
            if (this.cases[yCase][xCase] instanceof Culture) {
                Culture tmp = (Culture)this.cases[yCase][xCase];

                // on met à jour le stockGraine
                ajouterGraineStock(tmp.getIdPlante(), tmp.arracher());

                this.cases[yCase][xCase] = new Case();
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

    public boolean[] getBesoin(int yCase, int xCase){
        if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
            if (this.cases[yCase][xCase] instanceof Culture) {
                Culture tmp = (Culture)this.cases[yCase][xCase];
                return tmp.getBesoin();
            }
        }

        return new boolean[]{true};
    }


    public boolean estUneculture(int yCase, int xCase) {
        if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
            return this.cases[yCase][xCase] instanceof Culture;
        }
        return false;
    }

    public boolean estUnAmenagement(int yCase, int xCase) {
        if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
            return this.cases[yCase][xCase] instanceof Amenagement;
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

    public int getIdPlante(int yCase, int xCase) {
        if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
            if (estUneculture(yCase, xCase)) {
                Culture tmp = (Culture)this.cases[yCase][xCase];
                return (tmp.getIdPlante());
            }
        }
        return -1;
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

    public HashMap<Integer, Integer> getStock(){
        return this.stockGraine;
    }

    public HashMap<Integer, Integer> getStockAmenagement(){
        return this.stockAmenagemnt;
    }


    public void afficherStock(){
        System.out.println("Stock: ");
        for (Integer key : this.stockGraine.keySet()) {
            System.out.println("    - id: "+key+", quantité: "+this.stockGraine.get(key));
        }
        for (Integer key : this.stockAmenagemnt.keySet()) {
            System.out.println("    - id: "+key+", quantité: "+this.stockAmenagemnt.get(key));
        }
    }

    public void afficherPlante(int yCase, int xCase) {
        if (yCase >= 0 && yCase < HAUTEUR && xCase >= 0 && xCase < LARGEUR) {
            if (estUneculture(yCase, xCase)) {
                Culture tmp = (Culture)this.cases[yCase][xCase];
                tmp.afficher();
            }
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

        System.out.println("systeme météo: ");
        this.meteo.afficher();
        System.out.println("-------------------");
    }

}
