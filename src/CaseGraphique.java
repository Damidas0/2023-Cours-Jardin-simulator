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

<<<<<<< HEAD
    private Potager p; //pointeur sur le potager
    private Vue v;// pointeur sur ka vue ref

=======
    private ImageIcon imgFond;
    private String cheminImgFond;
    private ImageIcon imgPlante;
    private String cheminImgPlante;
>>>>>>> e2d3e56d9f155332f93fb51e92aa87edafe91eb0
    private JProgressBar progressBar;
    private boolean afficherBar;


    /*public CaseGraphique() {
        this(0,0,);
    }*/

    public CaseGraphique(int y, int x, Potager p, Vue v){
        super();
        addMouseListener(this);

        this.x = x;
        this.y = y;
        this.p = p;
        this.v = v;

        // couleur de fond
        setBackground(Color.BLUE);
        // bordure
        setBorderSimple();

        setLayout(new BorderLayout());

        //bar de progression
        this.progressBar = new JProgressBar(0);
        this.progressBar.setForeground(new Color(0x69B00B));
        this.progressBar.setBorder(BorderFactory.createLineBorder(Color.red,2));
        add(this.progressBar, BorderLayout.SOUTH, Integer.valueOf(3)); // tout devant

        afficherBar(this.p.estUneculture(this.y, this.x));


        //img de la plante
        //sans la tile set
        this.cheminImgPlante = "img/salade.png";
        this.imgPlante = new ImageIcon(this.cheminImgPlante);

        JLabel planteLabel = new JLabel();
        planteLabel.setVisible(true);
        planteLabel.setOpaque(true);
        planteLabel.setIcon(this.imgPlante);

        add(planteLabel, BorderLayout.CENTER, Integer.valueOf(1)); //entre les 2


        /*avec la tile set
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("img/data.png"));
        } catch (java.io.IOException e) {
            System.out.println("ERREUR : Impossoble d'ouvrir le fichier img/data.png  "+ e.getMessage());
        }

        BufferedImage plantes = image.getSubimage(0, 0, 140, 140); // image du légume (x, y : coin supérieur gauche, w, h : largeur et hauteur)
        ImageIcon iconePlante = new ImageIcon(plantes.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH)); // icône redimentionnée

        JLabel planteLabel = new JLabel();
        planteLabel.setVisible(true);
        planteLabel.setIcon(iconePlante);

        add(planteLabel, BorderLayout.CENTER, Integer.valueOf(0)); //tout derrière
        */
    }

    public void changerImgPlante(String nouveauChemin){
        this.cheminImgPlante = nouveauChemin;
    }

    public void setBorderSimple(){
        setBorder(BorderFactory.createLineBorder(Color.black,1));
    }

    public void setBorderOver(){
        setBorder(BorderFactory.createDashedBorder(Color.black,3, 7, 5, false));
    }

    public void updateBar(){
        //System.out.println(this.afficherBar);
        if(this.p.estUneculture(this.y, this.x)) {
            this.progressBar.setValue(this.p.getDeveloppement(this.y, this.x));
        }
        afficherBar(this.p.estUneculture(this.y, this.x));
    }

    public void afficherBar(boolean bool){
        this.afficherBar = bool;
        this.progressBar.setVisible(bool);
    }

    public void afficherBar(){
        afficherBar(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //System.out.println("CA CLICCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCc");
        if (!p.estUneculture(y, x)) {
            this.p.planter(new Plante(), y, x);
        } else if (p.estPoussee(y, x)) {
            // TODO:récupérer le fruit du labeur de laterre (mettre dans un inventaire?)
            this.p.recolter(y, x);
        }
        // TODO Auto-generated method stub
        // throw new UnsupportedOperationException("Unimplemented method
        // 'mouseClicked'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setBorderOver();

        InfoPannel infoP = new InfoPannel();
        infoP.setSize(200, 200);
        infoP.setBounds(0,0,50,50); 
        infoP.setBackground(Color.black);
        infoP.setVisible(true);
        v.add(infoP);
        
        System.out.println(e.getX() + " / " + e.getY());
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setBorderSimple();
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
