public class Saison {
    private ConditionEnvironementale[] conditionMini ={
            new ConditionEnvironementale(20,40,40), //été
            new ConditionEnvironementale(30,25,0), //automne
            new ConditionEnvironementale(20,5,15), //hiver
            new ConditionEnvironementale(20,20,30), //primptemps
    };
    private ConditionEnvironementale[] conditionMaxi={
            new ConditionEnvironementale(50,85,85), //été
            new ConditionEnvironementale(85,65,65), //automne
            new ConditionEnvironementale(70,55,70), //hiver
            new ConditionEnvironementale(70,75,70), //primptemps
    };

    private int[][][] tableChangementMeteo = {
            {//été
                    /*|       |soleil|nuage|pluie|neige|*/
                    /*|soleil|*/{45, 30, 40, 100},
                    /*|nuage |*/{35, 30, 30, 0},
                    /*|pluie |*/{20, 40, 40, 0},
                    /*|neige |*/{0, 0, 0, 0},
            },

            {//automne
                    /*|       |soleil|nuage|pluie|neige|*/
                    /*|soleil|*/{45, 30, 30, 0},
                    /*|nuage |*/{5, 30, 40, 100},
                    /*|pluie |*/{20, 40, 40, 0},
                    /*|neige |*/{0, 0, 0, 0},
            },

            {//hiver
                /*|       |soleil|nuage|pluie|neige|*/
                /*|soleil|*/{40, 20, 25, 25},
                /*|nuage |*/{30, 20, 25, 25},
                /*|pluie |*/{15, 30, 20, 20},
                /*|neige |*/{15, 30, 30, 30},
            },
            
            {//printemps
                /*|       |soleil|nuage|pluie|neige|*/
                    /*|soleil|*/{50, 30, 40, 100},
                    /*|nuage |*/{30, 30, 30, 0},
                    /*|pluie |*/{20, 40, 40, 0},
                    /*|neige |*/{0, 0, 0, 0},
            },
    };

    /*
    |       |soleil|nuage|pluie|neige|
    |soleil| proba de passer de en haut au côté
    |nuage | somme d'une colone = 100
    |pluie |
    |neige |
     */


    public Saison(){

    }

    public int getProba(int meteo, int saison, int i){
        return this.tableChangementMeteo[saison][i][meteo];
    }

    public ConditionEnvironementale getConditionMaxi(int saison) {
        return conditionMaxi[saison];
    }

    public ConditionEnvironementale getConditionMini(int saison) {
        return conditionMini[saison];
    }
}
