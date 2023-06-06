
public class Culture extends Case {
    private Plante plante;
    private ConditionEnvironementale conditionCase;

    //TODO:VERIFIER SI ON DOIT GARDER 
    private final int nbStadeDev = 4;


    public Culture(){
        super();
        this.plante = new Plante();
        this.conditionCase = new ConditionEnvironementale(0,0,0);
        //TODO relier la météo au potager
    }


    public Culture(Plante plante, ConditionEnvironementale conditionCase){
        super();
        this.plante = new Plante(plante);
        this.conditionCase = new ConditionEnvironementale(conditionCase);
    }

    public int recolter(){
        return this.plante.recolter();
    }

    public int niveauDeSurvie(){
        return this.plante.niveauDeSurvie();
    }

    public boolean[] getBesoin() {
        return this.plante.getBesoin();
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
        System.out.println("---------JPanel----------");
        System.out.println("AFFICHAGE DE CULTURE");
        System.out.print("condition environemental :");
        this.conditionCase.afficher();
        System.out.print("plante :");
        this.plante.afficher();
        System.out.println("-------------------");
    }

    @Override
    public void run(){
        //this.plante.afficher();
        this.plante.developper(this.conditionCase, 10);
        
    }


    public String getNomPlante() {

        return this.plante.getNom();
    }


    public int getInfoEau() {
        return this.conditionCase.getHumidite();
    }


    public int getInfoTemp() {
        return this.conditionCase.getTemperature();
    }


    public int getInfoSoleil() {
        return this.conditionCase.getEnsoleillement();
    }
}   
