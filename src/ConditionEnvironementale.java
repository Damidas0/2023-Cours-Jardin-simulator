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

    public boolean compareCE(ConditionEnvironementale condition, int ecartTolere){
        return (Math.abs(this.ensoleillement - condition.ensoleillement) > ecartTolere
            || Math.abs(this.humidite - condition.humidite) > ecartTolere
                || Math.abs(this.temperature - condition.temperature) > ecartTolere);
    }
}
