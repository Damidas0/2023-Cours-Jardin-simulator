public class ConditionEnvironementale {

    /** les jauge vont de 0 à 100 */
    private int humidite;
    private int temperature;
    private int ensoleillement;

    public ConditionEnvironementale() {
        this.humidite = 0;
        this.temperature = 0;
        this.ensoleillement = 0;
    }

    public ConditionEnvironementale(int humidite, int temperature, int ensoleillement) {
        setEnsoleillement(ensoleillement);
        setHumidite(humidite);
        setTemperature(temperature);
    }

    public ConditionEnvironementale(ConditionEnvironementale CE) {
        this.humidite = CE.humidite;
        this.ensoleillement = CE.ensoleillement;
        this.temperature = CE.temperature;
    }

    public int getEnsoleillement() {
        return ensoleillement;
    }

    public int getHumidite() {
        return humidite;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setEnsoleillement(int ensoleillement) {
        /*if (ensoleillement < 0) {
            this.ensoleillement = 0;
        } else if (ensoleillement > 100) {
            this.ensoleillement = 100;
        } else {
            this.ensoleillement = ensoleillement;
        }*/

        this.ensoleillement = ensoleillement;
    }

    public void setHumidite(int humidite) {
        /*if (humidite < 0) {
            this.humidite = 0;
        } else if (humidite > 100) {
            this.humidite = 100;
        } else {
            this.humidite = humidite;
        }*/

        this.humidite = humidite;
    }

    public void setTemperature(int temperature) {
        /*if (temperature < 0) {
            this.temperature = 0;
        } else if (temperature > 100) {
            this.temperature = 100;
        } else {
            this.temperature = temperature;
        }*/

        this.temperature = temperature;
    }

    /***
     *
     * @param condition
     * @param ecartTolere
     * @return True si l'écart entre les 2 CE est plus petit que ecartTolere, False
     *         sinon
     */
    public boolean compareCEBasique(ConditionEnvironementale condition, int ecartTolere) {
        return (Math.abs(this.ensoleillement - condition.ensoleillement) <= ecartTolere
                && Math.abs(this.humidite - condition.humidite) <= ecartTolere
                && Math.abs(this.temperature - condition.temperature) <= ecartTolere);
    }

    /***
     * Retourne un entier qui correspons à la comparaison des 2 CE :
     * [0: tolérable, 1: manque de soleil, 2: manque d'humidité, 3: manque de
     * température]
     * 
     * @param condition
     * @param ecartTolere
     * @return int
     */
    public boolean[] compareCE(ConditionEnvironementale condition, ConditionEnvironementale ecartTolere) {
        this.afficher();
        condition.afficher();
        ecartTolere.afficher();
        System.out.println("////////////////////////////////////////////////////");

        // {la plante va bien,  manque H, trop H, manque T, trop T, manque E, trop E}
        boolean [] besoin = {false, false, false, false, false, false, false};
        if (Math.abs(this.humidite - condition.humidite) > ecartTolere.humidite)
            if(this.humidite > condition.humidite){
                besoin[1] = true;
            }else besoin[2] = true;
        if (Math.abs(this.temperature - condition.temperature) > ecartTolere.temperature)
            if(this.temperature > condition.temperature){
                besoin[3] = true;
            }else besoin[4] = true;
        if (Math.abs(this.ensoleillement - condition.ensoleillement) > ecartTolere.ensoleillement)
            if(this.ensoleillement > condition.ensoleillement){
                besoin[5] = true;
            }else besoin[6] = true;
        else {
            besoin[0] = true;
        }

        return besoin;
    }

    public void afficher() {
        System.out.println("-------------------");
        System.out.println("AFFICHAGE DE CONDITIONENVIRONEMENTALE");
        System.out.println("humidite: " + this.humidite);
        System.out.println("temperature: " + this.temperature);
        System.out.println("temperature: " + this.temperature);
        System.out.println("-------------------");
    }
}
