import java.awt.Color;

public class Culture extends Case {
    private Plante plante;
    private ConditionEnvironementale conditionCase;

<<<<<<< HEAD
    //TODO:VERIFIER SI ON DOIT GARDER 
    private final int nbStadeDev = 4;


    public Culture(){
        super();
        this.plante = new Plante("Default", 0);
        this.conditionCase = new ConditionEnvironementale();
        }
=======
    public Culture(){
        super();
    }
>>>>>>> d164653525261adb3b16efb911a5afa1d132c1a7


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
        System.out.println(this.plante.getDeveloppement());
        this.plante.developper(this.conditionCase, 1);
        if (this.plante.getDeveloppement() > 50){
            if (this.plante.getDeveloppement()==100) setBackground(Color.green);
            else setBackground(Color.yellow);
        }
        else{
            System.out.println("Je suis censé changer de couleur pour du rouge wtf");
            setBackground(Color.red);
        } 
    }
}   
