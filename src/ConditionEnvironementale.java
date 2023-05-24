public class ConditionEnvironementale {

    /**les jauge vont de 0 à 100*/
    private int humidite;
    private int temperature;
    private int ensoleillement;

    public ConditionEnvironementale(){
        this.humidite = 0;
        this.temperature = 0;
        this.ensoleillement = 0;
    }

    public ConditionEnvironementale(int humidite, int temperature, int ensoleillement){
        setEnsoleillement(ensoleillement);
        setHumidite(humidite);
        setTemperature(temperature);
    }

    public ConditionEnvironementale(ConditionEnvironementale CE){
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
        if(ensoleillement < 0){
            this.ensoleillement = 0;
        }else if(ensoleillement > 100){
            this.ensoleillement = 100;
        }else{
            this.ensoleillement = ensoleillement;
        }
    }

    public void setHumidite(int humidite) {
        if(humidite < 0){
            this.humidite = 0;
        }else if(humidite > 100){
            this.humidite = 100;
        }else{
            this.humidite = humidite;
        }
    }

    public void setTemperature(int temperature) {
        if(temperature < 0){
            this.temperature = 0;
        }else if(temperature > 100){
            this.temperature = 100;
        }else{
            this.temperature = temperature;
        }
    }

    /***
     *
     * @param condition
     * @param ecartTolere
     * @return True si l'écart entre les 2 CE est plus petit que ecartTolere, False sinon
     */
    public boolean compareCEBasique(ConditionEnvironementale condition, int ecartTolere){
        return (Math.abs(this.ensoleillement - condition.ensoleillement) <= ecartTolere
            && Math.abs(this.humidite - condition.humidite) <= ecartTolere
            && Math.abs(this.temperature - condition.temperature) <= ecartTolere);
    }

    /***
     * Retourne un entier qui correspons à la comparaison des 2 CE :
     * [0: tolérable, 1: manque de soleil, 2: manque d'humidité, 3: manque de température]
     * @param condition
     * @param ecartTolere
     * @return int
     */
    public int compareCE(ConditionEnvironementale condition, ConditionEnvironementale ecartTolere){
        if (Math.abs(this.ensoleillement - condition.ensoleillement) <= ecartTolere.ensoleillement) return 1;
        if (Math.abs(this.humidite - condition.humidite) <= ecartTolere.humidite) return 2;
        if (Math.abs(this.temperature - condition.temperature) <= ecartTolere.temperature) return 3;
        else return 0;
    }

    public void afficher(){
        System.out.println("-------------------");
        System.out.println("AFFICHAGE DE CONDITIONENVIRONEMENTALE");
        System.out.println("humidite: "+this.humidite);
        System.out.println("temperature: "+this.temperature);
        System.out.println("temperature: "+this.temperature);
        System.out.println("-------------------");
    }
}
