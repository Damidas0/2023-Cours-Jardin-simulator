public class Plante {
    protected String nom;
    protected int devellopement;
    protected ConditionEnvironementale conditionOptimal;
    protected int ecartTolerable;

    public Plante(String nom){
        this.nom = nom;
        this.devellopement = 0;

        this.conditionOptimal = new ConditionEnvironementale();
        this.ecartTolerable = 5;
    }

    public Plante(String nom, int ensoleillementOpti, int humiditeOpti, int temperatureOpti, int ecartTolerable){
        this.nom = nom;
        this.devellopement = 0;

        this.conditionOptimal = new ConditionEnvironementale(ensoleillementOpti, humiditeOpti, temperatureOpti);
        this.ecartTolerable = ecartTolerable;
    }

    public void developper(condition de ){

    }
}
