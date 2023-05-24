/*package Jardin;*/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import javax.swing.SwingUtilities;
import javax.swing.text.View;

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

        Potager p = new Potager();
        Vue V = new Vue(p);
        V.setVisible(true);
        p.addObserver(V);

        Ordonnanceur.getOrdonnanceur().addObserver(V);
        new Thread(Ordonnanceur.getOrdonnanceur()).start();
    }

}
