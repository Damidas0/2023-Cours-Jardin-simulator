/*package Jardin;*/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import javax.swing.SwingUtilities;
import javax.swing.text.View;

/**
 *
 * @author frederic
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				//On crée une nouvelle instance de notre JDialog
				Vue fenetre = new Vue();
				fenetre.setVisible(true);//On la rend visible
			}
		});*/

        Modele M = new Modele(20, 20);
        Vue V = new Vue(M);
        V.setVisible(true);
        M.addObserver(V);

        new Thread(Ordonnanceur.getOrdonnanceur()).start();
    }

}
