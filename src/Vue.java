
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;

import javax.swing.*;
import java.awt.*;

import javax.swing.border.Border;




public class Vue extends JFrame implements Observer{
    final String CHEMIN_IMG = "img/";

    public Potager p;
    public CaseGraphique[][] tabG;

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
        
        
    }

    private JComponent buildPotager(){
        
        JComponent pan = new JPanel (new GridLayout(this.p.HAUTEUR, this.p.LARGEUR));

        Border blackline = BorderFactory.createLineBorder(Color.black,1);

        for(int i = 0; i<this.p.HAUTEUR;i++){
            for(int j = 0; j<this.p.LARGEUR;j++){
                CaseGraphique ptest = new CaseGraphique(i,j,p,this);
                tabG[i][j] = ptest;
                pan.add(ptest);
            }
        }
        return pan;
    }


    public void buildBis(){
        // paramétrage de la fenetre
        this.setLayout(new GridBagLayout());
        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setTitle("Le poti potager");
        this.setSize(1000, 1000);

        // changer l'icon de la fenetre
        ImageIcon image = new ImageIcon("img/logo.png");
        this.setIconImage(image.getImage()); //change l'icon de la frame

        //Menus
        JMenuBar jm = new JMenuBar();
        JMenu m = new JMenu("Jeu");
        JMenuItem mi = new JMenuItem("Partie");

        m.add(mi);
        jm.add(m);
        this.setJMenuBar(jm);

        //===GridbagLayout===
        GridBagConstraints c = new GridBagConstraints();


        //Potager
        //Gestion potager
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight=3;
        c.weightx = 0.6;

        c.anchor = GridBagConstraints.CENTER;

        c.fill = GridBagConstraints.BOTH;
        this.add(buildPotager(), c);

        //SubMenu
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight=1;
        c.weightx = 0.2;

        c.fill = GridBagConstraints.NONE;
        this.add(buildSubMenu(), c);

        //menu
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight=1;
        c.weightx = 0.2;

        //c.fill = GridBagConstraints.BOTH;
        this.add(buildMenu(), c);

        //infoPannel
        c.gridx = 2;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight=1;
        c.weightx = 0.2;

        //c.fill = GridBagConstraints.BOTH;
        this.add(buildInfoPannel(), c);

        this.pack();
        this.setVisible(true);
    }

    private Component buildSubMenu() {
        //TODO:replacecode
        return new InfoPannel();
    }

    private Component buildMenu() {
        //TODO:replacecode
        return new InfoPannel();
    }
    
    private Component buildInfoPannel() {
        //TODO:replacecode
        return new InfoPannel();
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

        
        /*JComponent pan = new JPanel (new GridLayout(this.p.HAUTEUR, this.p.LARGEUR));
        Border blackline = BorderFactory.createLineBorder(Color.black,1);
        //getVerifyInputWhenFocusTarget();

        for(int i = 0; i<this.p.HAUTEUR;i++){
            for(int j = 0; j<this.p.LARGEUR;j++){
                CaseGraphique ptest = new CaseGraphique(i,j,p,this);
                tabG[i][j] = ptest;
                pan.add(ptest);

                final int ii = i;
                final int jj = j;

                
        
            }
        }
        pan.setBorder(blackline);*/
        add(buildPotager());
        //setContentPane(pan);
    }

    @Override
    public void update(Observable o, Object arg) {
        //System.out.println("JE MUPDATEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
        for(int i=0; i<this.p.HAUTEUR; i++) {
            for(int j=0; j<this.p.LARGEUR; j++) {
                tabG[i][j].updateBar();
                if(p.estUneculture(i,j)) {
                    //System.out.println("CULTUREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
                    if (this.p.getDeveloppement(i,j) > 50){
                        if (this.p.getDeveloppement(i,j)==100) tabG[i][j].setBackground(Color.GREEN);
                        else tabG[i][j].setBackground(Color.YELLOW);
                    }
                    else{
                        //System.out.println("Je suis censé changer de couleur pour du rouge wtf");
                        tabG[i][j].setBackground(Color.RED);
                    } 
                }
                else {
                    tabG[i][j].setBackground(Color.LIGHT_GRAY);
                    //TODO:Remplacer par de la terre
                }
            }
        }
    }


    
}
