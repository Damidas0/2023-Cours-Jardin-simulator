import java.util.concurrent.ThreadLocalRandom;
public class Plante extends Graine{
    protected int developpement;
    protected boolean estVivante;
    protected int nbJoursSurvie;

    public Plante(){
        this("Default", -1);
    }

    public Plante(String nom, int id){
        this("debug", -1, 0,0,0,25,10,1,1,1);
    }

    public Plante(Graine Graine){
        this.nom = Graine.nom;
        this.id = Graine.id;

        this.conditionOptimale = new ConditionEnvironementale(Graine.conditionOptimale);
        this.ecartTolerable = new ConditionEnvironementale(Graine.ecartTolerable);
        this.ecartCritique = new ConditionEnvironementale(Graine.ecartCritique);

        this.nbJoursSurvieMax = Graine.nbJoursSurvieMax;

        this.modificateurVitesse = Graine.modificateurVitesse;
        this.rendementMin = Graine.rendementMin;
        this.rendementMax = Graine.rendementMax;

        this.estVivante = true;
        this.nbJoursSurvie = 0;
        this.developpement = 0;
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
        this.rendementMin = plante.rendementMin;
        this.rendementMax = plante.rendementMax;
    }

    public Plante(String nom, int id, int ensoleillementOpti, int humiditeOpti, int temperatureOpti, int ecartTolerable, int nbJoursSurvieMax, int modificateurVitesse, int rendementMin, int rendementMax){
        this.nom = nom;
        this.id = id;
        this.developpement = 0;

        this.conditionOptimale = new ConditionEnvironementale(ensoleillementOpti, humiditeOpti, temperatureOpti);
        this.ecartTolerable =  new ConditionEnvironementale(ecartTolerable,ecartTolerable,ecartTolerable);
        this.ecartCritique = new ConditionEnvironementale(2*ecartTolerable,2*ecartTolerable,2*ecartTolerable);

        this.nbJoursSurvie = 0;
        this.nbJoursSurvieMax = nbJoursSurvieMax;

        this.estVivante = true;
        this.modificateurVitesse = modificateurVitesse;

        this.rendementMin = rendementMin;
        this.rendementMax = rendementMax;
    }


    public void mourir(){
        this.estVivante = false;
    }

    public int recolter(){
        mourir();
        return ThreadLocalRandom.current().nextInt(this.rendementMin,this.rendementMax + 1);
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
