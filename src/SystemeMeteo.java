import java.util.concurrent.ThreadLocalRandom;

public class SystemeMeteo implements Runnable{
    private Saison saison;
    private Meteo meteos[] =  {
            new Meteo(-7, -3, 5, 8, 8, 10), /*|soleil|*/
            new Meteo(-5,5,-5, 3, -12, -7),  /*|nuage |*/
            new Meteo(9,13, -9, -5, -9,-5), /*|pluie |*/
            new Meteo(-3, 3, -13, -9,-9,-3), /*|neige |*/
    };

    private static int meteoActuelle; // | soleil : 0 | nuage : 1 | pluie : 2 | neige : 3 |
    private int saisonActuelle; // | été : 0 | automne : 1 | hiver : 2 | printemps : 3 |

    private int jourAvProchaineSaison;
    private int dureeSaison;

    public static ConditionEnvironementale conditionGlobale;

    public SystemeMeteo(){
        Ordonnanceur.getOrdonnanceur().addRunable(this);
        this.saison = new Saison();

        this.jourAvProchaineSaison = 0;
        this.dureeSaison = 100;

        SystemeMeteo.conditionGlobale = new ConditionEnvironementale(50,50,50);
    }

    public void update(){
        updateMeteo();
        updateCondition();
        updateSaison();

        //afficher();
    }


    public static String getMeteo(){
        String[] m = {"soleil", "nuage", "pluie", "neige"};
        return m[SystemeMeteo.meteoActuelle];
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
        SystemeMeteo.conditionGlobale = this.meteos[this.meteoActuelle].impacter(SystemeMeteo.conditionGlobale, this.saison.getConditionMini(this.saisonActuelle), this.saison.getConditionMaxi(this.saisonActuelle));
    }

    private void updateSaison(){
        this.jourAvProchaineSaison ++;
        if(this.jourAvProchaineSaison >= this.dureeSaison){
            this.jourAvProchaineSaison = 0;
            this.saisonActuelle = (this.saisonActuelle+1) % 4;
        }
    }

    public ConditionEnvironementale getCondition() {
        return conditionGlobale;
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
        SystemeMeteo.conditionGlobale.afficher();
        System.out.println("-------------------");
    }
}
