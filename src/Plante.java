public class Plante {
    protected String nom;
    protected int id;
    protected int devellopement;

    protected boolean estVivante;

    protected float modificateurVitesse;

    protected ConditionEnvironementale conditionOptimal;
    protected int ecartTolerable;
    protected int ecartCritique;

    protected int nbJoursSurvie;
    protected int nbJoursSurvieMax;

    public Plante(String nom, int id){
        this.nom = nom;
        this.id = id;
        this.devellopement = 0;

        this.conditionOptimal = new ConditionEnvironementale();
        this.ecartTolerable = 5;
        this.ecartCritique = 10;

        this.nbJoursSurvie = 0;
        this.nbJoursSurvieMax = 5;

        this.estVivante = true;
        this.modificateurVitesse = 1;
    }

    public Plante(String nom, int id, int ensoleillementOpti, int humiditeOpti, int temperatureOpti, int ecartTolerable){
        this.nom = nom;
        this.id = id;
        this.devellopement = 0;

        this.conditionOptimal = new ConditionEnvironementale(ensoleillementOpti, humiditeOpti, temperatureOpti);
        this.ecartTolerable = ecartTolerable;
        this.ecartCritique = 2*ecartTolerable;

        this.nbJoursSurvie = 0;
        this.nbJoursSurvieMax = 5;

        this.estVivante = true;
        this.modificateurVitesse = 1;
    }


    public void mourir(){
        this.estVivante = false;
    }

    public boolean testCondition(ConditionEnvironementale conditionCase, int ecart){
        return this.conditionOptimal.compareCE(conditionCase, ecart);
    }

    public void developper(ConditionEnvironementale conditionCase, int vitesse){
        if (testCondition(conditionCase, this.ecartTolerable)){
            this.devellopement += (int)vitesse * this.modificateurVitesse;
            if(this.devellopement > 100){
                this.devellopement = 100;
            }
        }
        if (! testCondition(conditionCase, this.ecartCritique)){
            this.nbJoursSurvie += vitesse;
            if(this.nbJoursSurvie > this.nbJoursSurvieMax){
                mourir();
            }
        }
    }

    public boolean estVivante(){
        return this.estVivante;
    }
}
