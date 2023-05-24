
import java.awt.Color;
import java.awt.GridLayout;

import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;

import javax.swing.border.Border;


public class Vue extends JFrame implements Observer{

    public Potager p;
    public JComponent[][] tabG;

    public Vue(Potager potager) {
        super();
        
        this.p = potager;
        this.tabG = new JComponent[this.p.HAUTEUR][this.p.LARGEUR];

        build();
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });
        
        
    }
    
    public void build() {
        
        JMenuBar jm = new JMenuBar();
        JMenu m = new JMenu("Jeu");
        JMenuItem mi = new JMenuItem("Partie");
        

        m.add(mi);
        jm.add(m);
        setJMenuBar(jm);
        
        setTitle("Le poti potager");
        setSize(1000, 1000);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // changer l'icon de la fenetre
        ImageIcon image = new ImageIcon("img/logo.png");
        setIconImage(image.getImage()); //change l'icon de la frame

        JComponent pan = new JPanel (new GridLayout(this.p.HAUTEUR, this.p.LARGEUR));
        Border blackline = BorderFactory.createLineBorder(Color.black,1);


        for(int i = 0; i<this.p.HAUTEUR;i++){
            for(int j = 0; j<this.p.LARGEUR;j++){
                JComponent ptest = new CaseGraphique(i,j,p);
                tabG[i][j] = ptest;
                ptest.setBorder(blackline);
                pan.add(ptest);

                final int ii = i;
                final int jj = j;

                tabG[ii][jj].addMouseListener(new MouseAdapter() {
                    //@Override
                    public void mouseClicked(MouseEvent e) {

                    }
                });
            }
        }
        pan.setBorder(blackline);
        add(pan);
        //setContentPane(pan);
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("JE MUPDATE");
        for(int i=0; i<this.p.HAUTEUR; i++) {
            for(int j=0; j<this.p.LARGEUR; j++) {
                if(p.estUneculture(i,j)) {
                    if (this.p.getDeveloppement(i,j) > 50){
                        if (this.p.getDeveloppement(i,j)==100) tabG[i][j].setBackground(Color.GREEN);
                        else tabG[i][j].setBackground(Color.YELLOW);
                    }
                    else{
                        //System.out.println("Je suis censé changer de couleur pour du rouge wtf");
                        tabG[i][j].setBackground(Color.RED);
                    } 
                    //tabG[i][j].setBackground(Color.RED);
                }
                else {
                    //tabG[i][j].setBackground(Color.WHITE);
                }
            }
        }
    }


    
}
