
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
    private InventairePanel ip;
    private Shop shopCulture;
    private Shop shopAmenagement;
    

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

        
        JPanel jp = new JPanel(new GridBagLayout());
        setContentPane(jp);

        this.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        this.setTitle("Le poti potager");
        this.setSize(1650, 1000);

        // changer l'icon de la fenetre
        ImageIcon image = new ImageIcon("img/logo.png");
        this.setIconImage(image.getImage()); //change l'icon de la frame

       

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

        c.fill = GridBagConstraints.BOTH;
        jp.add(buildMenu(), c);



        //infoPannel
        c.gridx = 1;
        c.gridy = 1;
        c.gridwidth = 1;
        c.gridheight=1;
        c.weighty=0.1;
        c.weightx = 0;

        //c.fill = GridBagConstraints.BOTH;
        jp.add(this.InfoP, c);
        
        this.pack();
        this.setTitle("Le poti potager");
        this.setSize(1650, 1000);
        this.setResizable(false);

        this.setVisible(true);
    }


    private JTabbedPane buildMenu() {
        UIManager.getLookAndFeelDefaults().put("TabbedPane:TabbedPaneTab.contentMargins", new Insets(0,0,100,100));
        JTabbedPane tabbedPane = new JTabbedPane();

        this.ip = new InventairePanel(p.getStock(), p.getStockAmenagement(),p);
        ip.setVisible(true);
        tabbedPane.addTab("Inventaire", ip);

        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);

        

        this.shopCulture = new Shop(1,p);
        this.shopCulture.setVisible(true);
        tabbedPane.add("Shop Culture", this.shopCulture);
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);



        this.shopAmenagement = new Shop(2,p);
        this.shopAmenagement.setVisible(true);
        tabbedPane.add("Shop Amenagement", this.shopAmenagement);
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);

        pack();
        return tabbedPane;
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

        this.ip.majInventaire(p.getStock(), p.getStockAmenagement());
        
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
        this.InfoP.majInfoPanel(SystemeMeteo.getMeteo(), SystemeMeteo.conditionGlobale.getHumidite(), SystemeMeteo.conditionGlobale.getEnsoleillement(), SystemeMeteo.conditionGlobale.getTemperature());
    }


    @Override
    public void componentResized(ComponentEvent e) {
        /*System.out.println(this.getSize().getHeight()+" "+ this.getSize().getWidth());
        if(this.getSize().getHeight() == 1038.0 && this.getSize().getWidth() == 1938.0){
            System.out.println("-----------------------");
            for(int i=0; i<this.p.HAUTEUR; i++) {
                for (int j = 0; j < this.p.LARGEUR; j++) {
                    this.tabG[i][j].rechargerImg();
                }
            }
        }*/
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