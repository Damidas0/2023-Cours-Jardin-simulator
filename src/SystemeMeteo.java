import java.util.concurrent.ThreadLocalRandom;

public class SystemeMeteo implements Runnable{
    private Saison saison;
    private Meteo meteos[] =  {
            new Meteo(-15, -5, 10, 20, 20, 35), /*|soleil|*/
            new Meteo(-5,10,-10, 5, -30, -15),  /*|nuage |*/
            new Meteo(20,35, -20, -10, -20,-10), /*|pluie |*/
            new Meteo(-5, 5, -35, -20,-20,-5), /*|neige |*/
    };

    private ConditionEnvironementale conditionActuelle;
    private int meteoActuelle; // | soleil : 0 | nuage : 1 | pluie : 2 | neige : 3 |
    private int saisonActuelle; // | été : 0 | automne : 1 | hiver : 2 | printemps : 3 |

    private int jourAvProchaineSaison;
    private int dureeSaison;

    public SystemeMeteo(){
        //Ordonnanceur.getOrdonnanceur().addRunable(this);
        this.saison = new Saison();

        this.jourAvProchaineSaison = 0;
        this.dureeSaison = 100;

        this.conditionActuelle = new ConditionEnvironementale(50,50,50);
    }

    public void update(){
        updateMeteo();
        updateCondition();
        updateSaison();
    }


    private void updateMeteo(){
        int r = ThreadLocalRandom.current().nextInt(0,100);
        int origine = 0;
        for (int i = 0; i < 4; i++) {
            if (origine<=r && r<=this.saison.getProba(this.meteoActuelle, this.saisonActuelle, i)+origine){
                this.meteoActuelle = i;
            }
            origine += this.saison.getProba(this.meteoActuelle, this.saisonActuelle, i);
        }
    }

    private void updateCondition(){
        this.conditionActuelle = this.meteos[this.meteoActuelle].impacter(this.conditionActuelle, this.saison.getConditionMini(this.saisonActuelle), this.saison.getConditionMaxi(this.saisonActuelle));
    }

    private void updateSaison(){
        this.jourAvProchaineSaison ++;
        if(this.jourAvProchaineSaison >= this.dureeSaison){
            this.jourAvProchaineSaison = 0;
            this.saisonActuelle = (this.saisonActuelle+1) % 4;
        }
    }

    public ConditionEnvironementale getCondition() {
        return conditionActuelle;
    }

    @Override
    public void run() {
        update();
    }

    public void afficher() {
        System.out.println("-------------------");
        System.out.println("AFFICHAGE DE SYSTEMEMETEO");
        System.out.print("meteo: " + this.meteoActuelle);
        switch (this.meteoActuelle){
            case 0:
                System.out.println("  => soleil");
                break;
            case 1:
                System.out.println("  => nuage");
                break;
            case 2:
                System.out.println("  => pluie");
                break;
            case 3:
                System.out.println("  => neige");
                break;
        }
        System.out.print("saison: " + this.saisonActuelle);
        switch (this.saisonActuelle){
            case 0:
                System.out.println("  => été");
                break;
            case 1:
                System.out.println("  => automne");
                break;
            case 2:
                System.out.println("  => hiver");
                break;
            case 3:
                System.out.println("  => printemps");
                break;
        }
        System.out.println("jourAvProchaineSaison : " + this.jourAvProchaineSaison + "/" + this.dureeSaison);
        System.out.println("Condition actuelle: ");
        this.conditionActuelle.afficher();
        System.out.println("-------------------");
    }
}
