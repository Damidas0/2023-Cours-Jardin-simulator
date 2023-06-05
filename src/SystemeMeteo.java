import java.util.concurrent.ThreadLocalRandom;

public class Meteo {
    private ConditionEnvironementale conditionMini;
    private ConditionEnvironementale conditionMaxi;

    private int[][] tableMeteo = {
            /*|       |soleil|nuage|pluie|neige|*/
            /*|soleil|*/{60, 30, 40, 100},
            /*|nuage |*/{30, 30, 30, 0},
            /*|pluie |*/{10, 40, 40, 0},
            /*|neige |*/{0, 0, 0, 0},
    };

    /*
    |       |soleil|nuage|pluie|neige|
    |soleil| proba de passer de en haut au coté
    |nuage | somme d'une colone = 100
    |pluie |
    |neige |
     */


    private ConditionEnvironementale conditionActuelle;
    private int meteoActuelle; // | soleil : 0 | nuage : 1 | pluie : 2 | neige : 3 |


    public ConditionEnvironementale update(){


        return this.conditionActuelle;
    }

    private void updateMeteo(){
        int r = ThreadLocalRandom.current().nextInt(0,100);
        int origine = 0;
        for (int i = 0; i < 4; i++) {
            if (origine<=r && r<=this.tableMeteo[i][meteoActuelle]+origine){
                meteoActuelle = i;
            }
            origine += this.tableMeteo[i][meteoActuelle];
        }
    }

}
