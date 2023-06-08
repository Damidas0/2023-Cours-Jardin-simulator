import java.util.ArrayList;
import java.util.Observable;

public class Ordonnanceur extends Observable implements Runnable{
    private ArrayList<Runnable> listeRunnable;

    public static int TICK = 100;
    private static Ordonnanceur o;//il a lui meme et ça marche vu que c de classe

    private Ordonnanceur() {
        super();
        this.listeRunnable = new ArrayList<Runnable>();
    }

    public static Ordonnanceur getOrdonnanceur() { //aesthetic
        if(o==null) {
            o = new Ordonnanceur();
        }
        return o;
    }

    public void addRunable(Runnable r){
        listeRunnable.add(r);
    }





    @Override
    public void run() {
        // TODO Auto-generated method stub
        while(true){
            for(Runnable r : listeRunnable){
                r.run();
            }
            setChanged();
            notifyObservers();


            try {
                Thread.sleep(Ordonnanceur.TICK);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }   
    }
}