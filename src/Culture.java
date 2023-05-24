import java.awt.Color;

public class Culture extends Case {
    private Plante plante;
    private ConditionEnvironementale conditionCase;

    //TODO:VERIFIER SI ON DOIT GARDER 
    private final int nbStadeDev = 4;


    public Culture(){
        super();
        this.plante = new Plante("Default", 0);
        this.conditionCase = new ConditionEnvironementale();
        }


    public Culture(Plante plante, ConditionEnvironementale conditionCase){
        super();
        this.plante = new Plante(plante);
        this.conditionCase = new ConditionEnvironementale(conditionCase);
    }

    /***
     * Revoie un entier symbolisant le type de la case :
     * [0 : case cide, 1 : culture, ]
     * @return int
     */
    @Override
    public int typeCase(){
        return 1;
    }

    public int getIdPlante(){
        return this.plante.getId();
    }

    public int getDeveloppement(){
        return this.plante.getDeveloppement();
    }

    @Override
    public void afficher(){
        System.out.println("-------------------");
        System.out.println("AFFICHAGE DE CULTURE");
        System.out.print("condition environemental :");
        this.conditionCase.afficher();
        System.out.print("plante :");
        this.plante.afficher();
        System.out.println("-------------------");
    }

    @Override
    public void run(){
        this.plante.afficher();
        this.plante.developper(this.conditionCase, 10);
        
    }
}   
