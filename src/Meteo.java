import java.util.concurrent.ThreadLocalRandom;

public class Meteo {
    private ConditionEnvironementale changementMini;
    private ConditionEnvironementale changementMaxi;

    public Meteo(int humiditeMini,  int humiditeMaxi, int temperatureMini,int temperatureMaxi, int ensoleillementMini, int ensoleillementMaxi){
        this.changementMini = new ConditionEnvironementale(humiditeMini, temperatureMini, ensoleillementMini);
        this.changementMaxi = new ConditionEnvironementale(humiditeMaxi, temperatureMaxi, ensoleillementMaxi);
    }

    public ConditionEnvironementale impacter(ConditionEnvironementale avant, ConditionEnvironementale mini, ConditionEnvironementale maxi) {
        int h = ThreadLocalRandom.current().nextInt(this.changementMini.getHumidite(), this.changementMaxi.getHumidite());
        int t = ThreadLocalRandom.current().nextInt(this.changementMini.getTemperature(), this.changementMaxi.getTemperature());
        int e = ThreadLocalRandom.current().nextInt(this.changementMini.getEnsoleillement(), this.changementMaxi.getEnsoleillement());

        //humidité
        if (avant.getHumidite() + h < mini.getHumidite()){
            avant.setHumidite(mini.getHumidite());
        }else if(avant.getHumidite() + h > maxi.getHumidite()){
            avant.setHumidite(maxi.getHumidite());
        }else{
            avant.setHumidite(avant.getHumidite() + h);
        }

        //température
        if (avant.getTemperature() + t < mini.getTemperature()){
            avant.setTemperature(mini.getTemperature());
        }else if(avant.getTemperature() + t > maxi.getTemperature()){
            avant.setTemperature(maxi.getTemperature());
        }else{
            avant.setTemperature(avant.getTemperature() + t);
        }

        //ensoleilement
        if (avant.getEnsoleillement() + e < mini.getEnsoleillement()){
            avant.setEnsoleillement(mini.getEnsoleillement());
        }else if(avant.getEnsoleillement() + e > maxi.getEnsoleillement()){
            avant.setEnsoleillement(maxi.getEnsoleillement());
        }else{
            avant.setEnsoleillement(avant.getEnsoleillement() + e);
        }

        return avant;
    }
}
