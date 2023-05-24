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
