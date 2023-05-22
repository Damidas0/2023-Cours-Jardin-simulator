public class Jeu {
    private Potager potager;

    private int vitesse;


    public Jeu(){
        this.potager = new Potager();
        this.vitesse = 1;
    }

    public void simulerTour(){
        potager.simulerTour();
    }

    public void planter(){
        potager.planter(new Plante("plante teste",0),0,0);
    }


}
