/*package Jardin;*/

import java.util.Observable;
import java.util.Random;

public class Modele extends Observable implements Runnable {

    public int size_x;
    public int size_y;
    public boolean[][] tab;
    
    public Modele(int x, int y) {
        this.size_x = x;
        this.size_y = y;
        this.tab = new boolean[size_x][size_y];
    }

    @Override
    public void run() {
        Random r = new Random();

        while(true) {
            /* changement de couleur aléatoire
            for(int i=0; i<size_x; i++) {
                for(int j=0; j<size_y; j++) {
                    tab[i][j] = r.nextBoolean();
                }
            }*/
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setChanged();
            notifyObservers();
        }
    }

    public void maj(int i, int j){
        tab[i][j] = !tab[i][j];
    }
}
