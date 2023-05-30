
import java.awt.Color;
import java.awt.GridLayout;

import java.awt.event.*;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.image.BufferedImage;

import javax.swing.border.Border;




public class Vue extends JFrame implements Observer{
    final String CHEMIN_IMG = "img/";

    public Potager p;
    public CaseGraphique[][] tabG;

    private BufferedImage tileset;

    public Vue(Potager potager) {
        super();
        this.p = potager;
        this.tabG = new CaseGraphique[this.p.HAUTEUR][this.p.LARGEUR];

        build();
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });

        this.tileset = null;
    }
    
    public void build() {
        // paramétrage de la fenetre
        this.setTitle("Le poti potager");
        this.setSize(1000, 1000);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // changer l'icon de la fenetre
        ImageIcon image = new ImageIcon("img/logo.png");
        this.setIconImage(image.getImage()); //change l'icon de la frame

        JMenuBar jm = new JMenuBar();
        JMenu m = new JMenu("Jeu");
        JMenuItem mi = new JMenuItem("Partie");

        m.add(mi);
        jm.add(m);
        setJMenuBar(jm);

        chargerImages();
        
        JComponent pan = new JPanel (new GridLayout(this.p.HAUTEUR, this.p.LARGEUR));
        Border blackline = BorderFactory.createLineBorder(Color.black,1);
        //getVerifyInputWhenFocusTarget();

        for(int i = 0; i<this.p.HAUTEUR;i++){
            for(int j = 0; j<this.p.LARGEUR;j++){
                CaseGraphique ptest = new CaseGraphique(i,j,p,this, this.tileset);
                tabG[i][j] = ptest;
                pan.add(ptest);

                final int ii = i;
                final int jj = j;

            }
        }
        pan.setBorder(blackline);
        add(pan);
        //setContentPane(pan);
    }

    public void chargerImages(){
        try {
            this.tileset = ImageIO.read(new File("./src/img/data.png")); // chargement de l'image globale
        } catch (java.io.IOException e) {
            System.out.println("ERREUR : Impossoble d'ouvrir la tileset ./src/img/data.png   "+ e.getMessage());
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        //System.out.println("JE MUPDATEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        for(int i=0; i<this.p.HAUTEUR; i++) {
            for(int j=0; j<this.p.LARGEUR; j++) {
                tabG[i][j].updateBar();
                if(p.estUneculture(i,j)) {

                }
                else {
                    //TODO:Remplacer par de la terre
                }
            }
        }
    }


    
}
