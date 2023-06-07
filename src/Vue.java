
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public Potager p;
    public CaseGraphique[][] tabG;

    public Tileset tileset = new Tileset();

    private InfoPannel InfoP;
    private MenuPanel menuP;

    public Vue(Potager potager) {
        super();
        addComponentListener(this);

        this.p = potager;
        this.tabG = new CaseGraphique[this.p.HAUTEUR][this.p.LARGEUR];
        this.InfoP = new InfoPannel();
        this.menuP = new MenuPanel(this);
        this.menuP.setBounds(20,20,100,20);

        buildBis();
        
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

        //Border blackline = BorderFactory.createLineBorder(Color.black,1);

        for(int i = 0; i<this.p.HAUTEUR;i++){
            for(int j = 0; j<this.p.LARGEUR;j++){
                CaseGraphique ptest = new CaseGraphique(i,j,p,this);
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
        this.setSize(1650, 1000);

        // changer l'icon de la fenetre
        ImageIcon image = new ImageIcon("img/logo.png");
        this.setIconImage(image.getImage()); //change l'icon de la frame

        //Menus
        /*JMenuBar jm = new JMenuBar();
        JMenu m = new JMenu("Jeu");
        JMenuItem mi = new JMenuItem("Partie");

        m.add(mi);
        jm.add(m);
        this.setJMenuBar(jm);*/

        //===GridbagLayout===
        GridBagConstraints c = new GridBagConstraints();


        //Potager
        //Gestion potager
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight=2;
        c.weightx = 0.6;
        c.weighty = 1;

        c.anchor = GridBagConstraints.CENTER;

        c.fill = GridBagConstraints.BOTH;
        jp.add(buildPotager(), c);

        //Menu
        c.gridx = 1;
        c.gridy = 0;
        c.gridwidth = 1;
        c.gridheight=1;
        c.weightx = 0.3;
        c.weighty = 1;

        c.fill = GridBagConstraints.HORIZONTAL;
        jp.add(buildMenu(), c);

        

        //infoPannel
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight=1;
        c.weightx = 0.2;

        //c.fill = GridBagConstraints.BOTH;
        jp.add(this.InfoP, c);

        this.pack();

        this.setVisible(true);
    }


    private JTabbedPane buildMenu() {
        JTabbedPane tabbedPane = new JTabbedPane();

        JComponent panel1 = new InventairePanel();
        panel1.setBounds(0, 0, 100, 100);

        tabbedPane.addTab("Inventaire", panel1);
        
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        List<ShopElementCulture> l = new ArrayList<ShopElementCulture>();
        HashMap<Integer, Integer> prix = new HashMap<>();
        prix.put(1,3);
        
        l.add(new ShopElementCulture("Carotte", 2, prix));
        JComponent panel2 = new Shop(l,p);
        panel2.setBounds(0, 0, 100, 100);
        tabbedPane.add("Shop", panel2);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);


        return tabbedPane;
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

        Tileset.charger();

        add(buildPotager());
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
                    tabG[i][j].update();
                }
                else {
                    //TODO:Remplacer par de la terre
                }
            }
        }

        this.InfoP.revalidate();
        this.InfoP.repaint();

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
        System.out.println(this.getSize().getHeight()+" "+ this.getSize().getWidth());
        if(this.getSize().getHeight() == 1038.0 && this.getSize().getWidth() == 1938.0){
            System.out.println("-----------------------");
            for(int i=0; i<this.p.HAUTEUR; i++) {
                for (int j = 0; j < this.p.LARGEUR; j++) {
                    this.tabG[i][j].rechargerImg();
                }
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
