import java.util.Observable;

public class Potager extends Observable implements Runnable {
    static final int HAUTEUR = 10;
    static final int LARGEUR = 10;

    private int vitesse;

    private Case cases[][];

    private ConditionEnvironementale conditionGlobal;

    public Potager(){
        Ordonnanceur.getOrdonnanceur().addRunable(this);
        this.conditionGlobal = new ConditionEnvironementale(50,50,50);
        this.vitesse = 1;
        this.cases = new Case[HAUTEUR][LARGEUR];

        for (int i = 0; i < HAUTEUR; i++) {
            for (int j = 0; j < LARGEUR; j++) {
                if(i==0 && j==0) this.cases[i][j] = new Culture();
                else this.cases[i][j] = new Case();
            }
        }

        
    }

    public void planter(Plante plante, int yCase, int xCase){
        if (yCase>=0 && yCase<HAUTEUR && xCase>=0 && xCase<LARGEUR){
            if (! (this.cases[yCase][xCase] instanceof Culture)){
                this.cases[yCase][xCase] = new Culture(plante, this.conditionGlobal);
            }
        }
    }

    public void recolter(int yCase, int xCase){
        if (yCase>=0 && yCase<HAUTEUR && xCase>=0 && xCase<LARGEUR){
            if (this.cases[yCase][xCase] instanceof Culture){
                this.cases[yCase][xCase] = new Case();
            }
        }
    }

    public boolean estUneculture(int yCase, int xCase){
        if (yCase>=0 && yCase<HAUTEUR && xCase>=0 && xCase<LARGEUR){
            return this.cases[yCase][xCase] instanceof Culture;
        }
        return false;
    }

    @Override
    public void run() {
        System.out.println("Je suis dans le run de potager");

    }
}
