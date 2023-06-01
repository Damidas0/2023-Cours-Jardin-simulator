/*package Jardin;*/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;

import static javax.swing.text.StyleConstants.setIcon;

public class CaseGraphique extends JLayeredPane implements MouseListener {
    private int x;
    private int y;

    private Potager p; // pointeur sur le potager
    private Vue v;// pointeur sur ka vue ref

    private BufferedImage tileset;

    private ImageIcon iconSelection;
    private JLabel imgSelection;
    private ImageIcon iconFond;
    private JLabel imgFond;

    private ImageIcon iconPlante;
    private JLabel imgPlante;

    private JProgressBar progressBar;
    private boolean afficherBarPlante;

    /*
     * public CaseGraphique() {
     * this(0,0,);
     * }
     */

    public CaseGraphique(int y, int x, Potager p, Vue v, BufferedImage tileset) {
        super();
        addMouseListener(this);
        this.tileset = tileset;

        this.x = x;
        this.y = y;
        this.p = p;
        this.v = v;

        // bordure
        setBorderSimple();

        //setLayout(new BorderLayout());

        // img de séléction
        this.imgSelection = new JLabel();
        this.imgSelection.setBounds(ew(0),eh(0),ew(100),eh(100));
        add(this.imgSelection, Integer.valueOf(3)); //tout devant
        changerImgSelection(170,159,159,159);
        this.imgSelection.setVisible(false);

        // bar de progression
        this.progressBar = new JProgressBar(0);
        this.progressBar.setForeground(new Color(0xc9b48d));
        this.progressBar.setBounds(10,eh(85),ew(80),eh(10));
        add(this.progressBar, Integer.valueOf(2)); // presque devant


        // img de la plante
        this.imgPlante = new JLabel();
        this.imgPlante.setBounds(ew(10),eh(10),ew(80),eh(80));
        add(this.imgPlante, Integer.valueOf(1)); //au milieu
        changerImgPlante(0,0,140,140);


        // img du fond
        this.imgFond = new JLabel();
        this.imgFond.setVisible(true);
        this.imgFond.setBounds(0,0,ew(100),eh(100));
        add(this.imgFond, Integer.valueOf(0)); //tout derrière
        changerImgFond(170,0,159,159); // la terre



        afficherBarPlante(this.p.estUneculture(this.y, this.x));
    }


    public int ew(double pourcent){
        //return (int)Math.floor(getSize().width * pourcent /100);
        return (int)Math.floor((this.v.getSize().height-30)/10 * pourcent /100);
    }

    public int eh(double pourcent){
        //return (int)Math.floor(getSize().height * pourcent /100);
        return (int)Math.floor((this.v.getSize().height-30)/10 * pourcent /100);
    }

    public void changerImgPlante(int xCoinSupG, int yCoinSupG, int width, int height) {
        BufferedImage boutTileset = this.tileset.getSubimage(xCoinSupG, yCoinSupG, width, height); // image de la plante  (x, y : coin supérieur gauche, w, h : largeur et hauteur)
        this.iconPlante = new ImageIcon(boutTileset.getScaledInstance(ew(80), eh(80), java.awt.Image.SCALE_SMOOTH)); // icône redimentionnée
        this.imgPlante.setIcon(this.iconPlante);
    }

    public void changerImgSelection(int xCoinSupG, int yCoinSupG, int width, int height) {
        BufferedImage boutTileset = this.tileset.getSubimage(xCoinSupG, yCoinSupG, width, height); // image de la plante  (x, y : coin supérieur gauche, w, h : largeur et hauteur)
        this.iconSelection = new ImageIcon(boutTileset.getScaledInstance(ew(100), eh(100), java.awt.Image.SCALE_SMOOTH)); // icône redimentionnée
        this.imgSelection.setIcon(this.iconSelection);
    }

    public void changerImgFond(int xCoinSupG, int yCoinSupG, int width, int height) {
        BufferedImage boutTileset = this.tileset.getSubimage(xCoinSupG, yCoinSupG, width, height); // image de la plante  (x, y : coin supérieur gauche, w, h : largeur et hauteur)
        this.iconFond = new ImageIcon(boutTileset.getScaledInstance(ew(100), eh(100), java.awt.Image.SCALE_SMOOTH)); // icône redimentionnée
        this.imgFond.setIcon(this.iconFond);
    }

    public void rechargerImg(){
        changerImgSelection(170,159,159,159);
        changerImgPlante(0,0,140,140);
        changerImgFond(170,0,159,159); // la terre
    }

    public void setBorderSimple() {
        setBorder(BorderFactory.createLineBorder(Color.black, 1));
    }

    public void setBorderOver() {
        setBorder(BorderFactory.createDashedBorder(Color.black, 3, 7, 5, false));
    }

    public void over(boolean isOver){
        this.imgSelection.setVisible(isOver);
    }
    public void updateBar() {
        // System.out.println(this.afficherBar);
        if (this.p.estUneculture(this.y, this.x)) {
            this.progressBar.setValue(this.p.getDeveloppement(this.y, this.x));
            if(this.p.getDeveloppement(this.y, this.x) == 100){
                this.progressBar.setForeground(new Color(0x69B00B));
            }else{
                this.progressBar.setForeground(new Color(0xc9b48d));
            }
        }
        afficherBarPlante(this.p.estUneculture(this.y, this.x));
    }

    public void afficherBarPlante(boolean bool) {
        this.afficherBarPlante = bool;
        this.progressBar.setVisible(bool);
        this.imgPlante.setVisible(bool);
    }

    public void afficherBarPlante() {
        afficherBarPlante(true);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        // System.out.println("CA CLICCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCc");
        if (!p.estUneculture(y, x)) {
            this.p.planterSelection(y, x);
        } else if (p.estPoussee(y, x)) {
            // TODO:récupérer le fruit du labeur de laterre (mettre dans un inventaire?)
            this.p.recolter(y, x);
        }
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        over(true);

        if(this.p.estUneculture(y, x)){
            this.v.majInfoPanel(y,x);
        }

        //System.out.println(e.getX() + " / " + e.getY());
       

    }

    @Override
    public void mouseExited(MouseEvent e) {
        over(false);
        this.v.resetInfoPannel();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mouseReleased'");
    }

}
