
import java.awt.event.*;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.sound.midi.MidiDevice.Info;
import javax.swing.*;
import java.awt.*;

import java.awt.image.BufferedImage;

import javax.swing.border.Border;




public class Vue extends JFrame implements Observer, ComponentListener{

    // coordonnée de l'image des graines dans la tileset IMAGE_GRAINE[id_graine]
    final static public CoordImg[] IMAGE_GRAINE = {
            new CoordImg(0,0,140,140)
    };


    final String CHEMIN_IMG = "img/";

    public Potager p;
    public CaseGraphique[][] tabG;

    private InfoPannel InfoP;
    private BufferedImage tileset;

    public Vue(Potager potager) {
        super();
        addComponentListener(this);

        this.p = potager;
        this.tabG = new CaseGraphique[this.p.HAUTEUR][this.p.LARGEUR];
        this.InfoP = new InfoPannel();

        buildBis();
        
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent arg0) {
                super.windowClosing(arg0);
                System.exit(0);
            }
        });

        this.tileset = null;
    }

    private JComponent buildPotager(){
        
        JComponent pan = new JPanel (new GridLayout(this.p.HAUTEUR, this.p.LARGEUR));

        //Border blackline = BorderFactory.createLineBorder(Color.black,1);

        for(int i = 0; i<this.p.HAUTEUR;i++){
            for(int j = 0; j<this.p.LARGEUR;j++){
                CaseGraphique ptest = new CaseGraphique(i,j,p,this,this.tileset);
                //tabG[i][j].setSize(80,80);
                tabG[i][j] = ptest;
                pan.add(ptest);
            }
        }
        return pan;
    }


    public void buildBis(){
        // paramétrage de la fenetre
        //this.setLayout(new GridBagLayout());

        //on ajoute et séléctionne une graine de base
        this.p.ajouterGraineStock(0,10);
        this.p.selectionnerGraine(0);
        
        JPanel jp = new JPanel(new GridBagLayout());
        setContentPane(jp);

        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setTitle("Le poti potager");
        this.setSize(1000, 1000);

        // changer l'icon de la fenetre
        ImageIcon image = new ImageIcon("img/logo.png");
        this.setIconImage(image.getImage()); //change l'icon de la frame
        
        this.chargerImages();

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
        c.weighty = 1;

        c.anchor = GridBagConstraints.CENTER;

        c.fill = GridBagConstraints.BOTH;
        jp.add(buildPotager(), c);

        //SubMenu
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight=1;
        c.weightx = 0.3;

        c.fill = GridBagConstraints.HORIZONTAL;
        jp.add(buildSubMenu(), c);

        //menu
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight=1;
        c.weightx = 0.2;

        //c.fill = GridBagConstraints.BOTH;
        jp.add(buildMenu(), c);

        //infoPannel
        c.gridx = 1;
        c.gridy = 2;
        c.gridwidth = 1;
        c.gridheight=1;
        c.weightx = 0.2;

        //c.fill = GridBagConstraints.BOTH;
        jp.add(this.InfoP, c);

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
        
        /*JComponent pan = new JPanel (new GridLayout(this.p.HAUTEUR, this.p.LARGEUR));
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
        pan.setBorder(blackline);*/
        add(buildPotager());
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
                InfoP.setToolTipText(String.valueOf(i));
                //System.out.println(InfoP.infoEau.getText());
                if(p.estUneculture(i,j)) {

                }
                else {
                    //TODO:Remplacer par de la terre
                }
            }
        }
        
    }

    public void majInfoPanel(int y, int x) {
        this.InfoP.majInfoPanel(this.p.getNomPlante(y,x), 
                                this.p.getInfoEau(y,x),
                                this.p.getInfoSoleil(y,x),
                                this.p.getInfoTemp(y,x));
        
    }

    public void resetInfoPannel() {
        this.InfoP.majInfoPanel("Debug", -1, -1, -1);
        //TODO:lier cette fonction aux params de la météo
    }


    @Override
    public void componentResized(ComponentEvent e) {
        for(int i=0; i<this.p.HAUTEUR; i++) {
            for (int j = 0; j < this.p.LARGEUR; j++) {
                //this.tabG[i][j].rechargerImg();
            }
        }
    }

    @Override
    public void componentMoved(ComponentEvent e) {

    }

    @Override
    public void componentShown(ComponentEvent e) {

    }

    @Override
    public void componentHidden(ComponentEvent e) {

    }
}
