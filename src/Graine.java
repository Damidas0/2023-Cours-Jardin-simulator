public class Graine {
    protected String nom;
    protected int id;

    protected float modificateurVitesse;

    protected int rendementMin;
    protected int rendementMax;

    protected ConditionEnvironementale conditionOptimale;
    protected ConditionEnvironementale ecartTolerable;
    protected ConditionEnvironementale ecartCritique;

    protected int nbJoursSurvieMax;

    public Graine(){
        this("debug", -1, 0,0,0,25,10,1,1,1);
    }
    public Graine(Graine Graine){
        this.nom = Graine.nom;
        this.id = Graine.id;

        this.conditionOptimale = new ConditionEnvironementale(Graine.conditionOptimale);
        this.ecartTolerable = new ConditionEnvironementale(Graine.ecartTolerable);
        this.ecartCritique = new ConditionEnvironementale(Graine.ecartCritique);

        this.nbJoursSurvieMax = Graine.nbJoursSurvieMax;

        this.modificateurVitesse = Graine.modificateurVitesse;
        this.rendementMin = Graine.rendementMin;
        this.rendementMax = Graine.rendementMax;
    }

    public Graine(String nom, int id, int ensoleillementOpti, int humiditeOpti, int temperatureOpti, int ecartTolerable, int nbJoursSurvieMax, int modificateurVitesse, int rendementMin, int rendementMax){
        this.nom = nom;
        this.id = id;

        this.conditionOptimale = new ConditionEnvironementale(ensoleillementOpti, humiditeOpti, temperatureOpti);
        this.ecartTolerable =  new ConditionEnvironementale(ecartTolerable,ecartTolerable,ecartTolerable);
        this.ecartCritique = new ConditionEnvironementale(2*ecartTolerable,2*ecartTolerable,2*ecartTolerable);

        this.nbJoursSurvieMax = nbJoursSurvieMax;

        this.modificateurVitesse = modificateurVitesse;

        this.rendementMin = rendementMin;
        this.rendementMax = rendementMax;
    }

    public int getId(){
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public void afficher(){
        System.out.println("-------------------");
        System.out.println("AFFICHAGE DE GRAINE");
        System.out.println("id: "+this.id);
        System.out.println("nom: "+this.nom);
        System.out.println("nbJoursSurvieMax: "+this.nbJoursSurvieMax);
        System.out.println("modificateurVitesse: "+this.modificateurVitesse);
        System.out.println("rendementMin: "+this.rendementMin);
        System.out.println("rendementMax: "+this.rendementMax);

        System.out.print("condition optimal :");
        this.conditionOptimale.afficher();
        System.out.println("ecartTolerable: ");
        this.ecartTolerable.afficher();
        System.out.println("ecartCritique: ");
        this.ecartCritique.afficher();
        System.out.println("-------------------");
    }
}
