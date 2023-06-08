import java.util.Arrays;

public class Culture extends Case {
    private Plante plante;

    private boolean protectionsEnvironementales[];



    public Culture(Plante plante){
        super();
        this.plante = new Plante(plante);
        /*this.CEmin = new ConditionEnvironementale(0,0,0);
        this.CEmax = new ConditionEnvironementale(100,100,100);*/
        this.protectionsEnvironementales = new boolean[6];
        Arrays.fill(this.protectionsEnvironementales, false);

    }

    public int recolter(){
        return this.plante.recolter();
    }

    public int arracher()
    {
        return this.plante.arracher();
    }

    public boolean estVivante(){
        return this.plante.estVivante();
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

    public void changerProtectionEnvironemental(int type, boolean bool){
        if(type>=0 && type<6) this.protectionsEnvironementales[type] = bool;
    }
    public void resetProtectionEnvironemental(){
        Arrays.fill(this.protectionsEnvironementales, false);
    }

    @Override
    public void afficher(){
        System.out.println("---------JPanel----------");
        System.out.println("AFFICHAGE DE CULTURE");
        /*System.out.print("condition environemental min:");
        this.CEmin.afficher();
        System.out.print("condition environemental max:");
        this.CEmax.afficher();
        System.out.print("plante :");*/
        System.out.println("protectionsEnvironementales :");
        System.out.println(protectionsEnvironementales.toString());
        System.out.println("plante :");
        this.plante.afficher();
        System.out.println("-------------------");
    }


    @Override
    public void run(){
        //this.plante.afficher();
        this.plante.developper(SystemeMeteo.conditionGlobale, Potager.vitesse, this.protectionsEnvironementales);
        
    }


    public String getNomPlante() {

        return this.plante.getNom();
    }


    public int getInfoEau() {
        return this.plante.getCE().getHumidite();
    }


    public int getInfoTemp() {

        return this.plante.getCE().getTemperature();
    }


    public int getInfoSoleil() {

        return this.plante.getCE().getEnsoleillement();
    }
}   
