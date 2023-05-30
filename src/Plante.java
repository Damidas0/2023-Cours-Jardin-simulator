public class Plante {
    protected String nom;
    protected int id;
    protected int developpement;

    protected boolean estVivante;

    protected float modificateurVitesse;

    protected ConditionEnvironementale conditionOptimale;
    protected ConditionEnvironementale ecartTolerable;
    protected ConditionEnvironementale ecartCritique;

    protected int nbJoursSurvie;
    protected int nbJoursSurvieMax;

    public Plante(){
        this("Default", -1);
    }

    public Plante(String nom, int id){
        this.nom = nom;
        this.id = id;
        this.developpement = 0;

        this.conditionOptimale = new ConditionEnvironementale();
        this.ecartTolerable = new ConditionEnvironementale(5,5,5);
        this.ecartCritique = new ConditionEnvironementale(10,10,10);

        this.nbJoursSurvie = 0;
        this.nbJoursSurvieMax = 5;

        this.estVivante = true;
        this.modificateurVitesse = 1;
    }

    public Plante(Plante plante){
        this.nom = plante.nom;
        this.id = plante.id;
        this.developpement = plante.developpement;

        this.conditionOptimale = new ConditionEnvironementale(plante.conditionOptimale);
        this.ecartTolerable = new ConditionEnvironementale(plante.ecartTolerable);
        this.ecartCritique = new ConditionEnvironementale(plante.ecartCritique);

        this.nbJoursSurvie = plante.nbJoursSurvie;
        this.nbJoursSurvieMax = plante.nbJoursSurvieMax;

        this.estVivante = plante.estVivante;
        this.modificateurVitesse = plante.modificateurVitesse;
    }

    public Plante(String nom, int id, int ensoleillementOpti, int humiditeOpti, int temperatureOpti, int ecartTolerable){
        this.nom = nom;
        this.id = id;
        this.developpement = 0;

        this.conditionOptimale = new ConditionEnvironementale(ensoleillementOpti, humiditeOpti, temperatureOpti);
        this.ecartTolerable =  new ConditionEnvironementale(ecartTolerable,ecartTolerable,ecartTolerable);
        this.ecartCritique = new ConditionEnvironementale(2*ecartTolerable,2*ecartTolerable,2*ecartTolerable);

        this.nbJoursSurvie = 0;
        this.nbJoursSurvieMax = 5;

        this.estVivante = true;
        this.modificateurVitesse = 1;
    }


    public void mourir(){
        this.estVivante = false;
    }


    public void developper(ConditionEnvironementale conditionCase, int vitesse){
        if (this.conditionOptimale.compareCE(conditionCase, this.ecartTolerable) == 0){
            this.nbJoursSurvie = 0;
            this.developpement += (int)vitesse * this.modificateurVitesse;
            if(this.developpement > 100){
                this.developpement = 100;
            }
        }
        if (this.conditionOptimale.compareCE(conditionCase, this.ecartCritique) != 0){
            this.nbJoursSurvie += vitesse;
            if(this.nbJoursSurvie > this.nbJoursSurvieMax){
                mourir();
            }
        }
    }

    public boolean estVivante(){
        return this.estVivante;
    }

    public int getDeveloppement(){
        return this.developpement;
    }

    public int getId(){
        return this.id;
    }

    public String getNom() {
        return this.nom;
    }

    public void afficher(){
        System.out.println("-------------------");
        System.out.println("AFFICHAGE DE PLANTE");
        System.out.println("id: "+this.id);
        System.out.println("nom: "+this.nom);
        System.out.println("developpement (sur 100): "+this.developpement);
        System.out.println("nbJoursSurvie / nbMax: "+this.nbJoursSurvie+" / "+this.nbJoursSurvieMax);
        if (this.estVivante){ System.out.println("estVivante: TRUE");}
        else {System.out.println("estVivante: FALSE");}
        System.out.println("modificateurVitesse: "+this.modificateurVitesse);
        System.out.println("nom: "+this.nom);


        System.out.print("condition optimal :");
        this.conditionOptimale.afficher();
        System.out.println("ecartTolerable: ");
        this.ecartTolerable.afficher();
        System.out.println("ecartCritique: ");
        this.ecartCritique.afficher();
        System.out.println("-------------------");
    }

    
}
