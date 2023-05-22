public class Plante {
    protected String nom;
    protected int id;
    protected int developpement;

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
        this.developpement = 0;

        this.conditionOptimal = new ConditionEnvironementale();
        this.ecartTolerable = 5;
        this.ecartCritique = 10;

        this.nbJoursSurvie = 0;
        this.nbJoursSurvieMax = 5;

        this.estVivante = true;
        this.modificateurVitesse = 1;
    }

    public Plante(Plante plante){
        this.nom = plante.nom;
        this.id = plante.id;
        this.developpement = plante.developpement;

        this.conditionOptimal = new ConditionEnvironementale(plante.conditionOptimal);
        this.ecartTolerable = plante.ecartTolerable;
        this.ecartCritique = plante.ecartCritique;

        this.nbJoursSurvie = plante.nbJoursSurvie;
        this.nbJoursSurvieMax = plante.nbJoursSurvieMax;

        this.estVivante = plante.estVivante;
        this.modificateurVitesse = plante.modificateurVitesse;
    }

    public Plante(String nom, int id, int ensoleillementOpti, int humiditeOpti, int temperatureOpti, int ecartTolerable){
        this.nom = nom;
        this.id = id;
        this.developpement = 0;

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
            this.developpement += (int)vitesse * this.modificateurVitesse;
            if(this.developpement > 100){
                this.developpement = 100;
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
