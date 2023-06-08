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
    private Vue v;// pointeur sur la vue ref

    private JProgressBar progressionPousse;
    private boolean afficherBarPlante;

    private JProgressBar progressionMort;
    private boolean afficherBarMort;

    private IndicateurBesoin iconBesoin;


    private ImageGraphique imgFond;
    private ImageGraphique imgPlante;
    private ImageGraphique imgSelection;

    public CaseGraphique(int y, int x, Potager p, Vue v) {
        super();
        addMouseListener(this);
        this.x = x;
        this.y = y;
        this.p = p;
        this.v = v;

        // bordure
        //setBorderSimple();

        // img de séléction
        this.imgSelection = new ImageGraphique(ew(100), eh(100), "selection");
        this.imgSelection.setBounds(0,0,ew(100),eh(100));
        this.imgSelection.setVisible(false);
        add(this.imgSelection, Integer.valueOf(1)); //au milieu


        //icon de besoin
        this.iconBesoin = new IndicateurBesoin(ew(100), eh(100));
        this.iconBesoin.setBounds(ew(0),eh(15),ew(100),eh(70));
        this.iconBesoin.setVisible(true);
        add(this.iconBesoin, Integer.valueOf(3)); // presque devant

        // bar de progression pousse
        this.progressionPousse = new JProgressBar(0);
        this.progressionPousse.setForeground(new Color(0xc9b48d));
        this.progressionPousse.setBounds(ew(10),eh(85),ew(80),eh(10));
        add(this.progressionPousse, Integer.valueOf(2)); // presque devant
        this.progressionPousse.setVisible(false);

        // bar de progression pousse
        this.progressionMort = new JProgressBar(0);
        this.progressionMort.setForeground(new Color(0XB50321));
        this.progressionMort.setBounds(ew(15),eh(5),ew(70),eh(7));
        add(this.progressionMort, Integer.valueOf(2)); // presque devant
        this.progressionMort.setVisible(false);


        // img de la plante
        this.imgPlante = new ImageGraphique(ew(80), eh(80), 0);
        this.imgPlante.setBounds(ew(10),eh(10),ew(80),eh(80));
        add(this.imgPlante, Integer.valueOf(1)); //au milieu

        // img du fond
        this.imgFond = new ImageGraphique(ew(100), eh(100), "sol_case");
        this.imgFond.setVisible(true);
        this.imgFond.setBounds(0,0,ew(100),eh(100));
        add(this.imgFond, Integer.valueOf(0)); //tout derrière

        afficherBarPlante(this.p.estUneculture(this.y, this.x));
    }


    public int ew(double pourcent){
        //return (int)Math.floor(getSize().width * pourcent /100);
        return (int)Math.floor((this.v.getSize().height)/10 * pourcent /100);
    }

    public int eh(double pourcent){
        //return (int)Math.floor(getSize().height * pourcent /100);
        return (int)Math.floor((this.v.getSize().height)/10 * pourcent /100);
    }

    public void setBorderSimple() {
        setBorder(BorderFactory.createLineBorder(Color.black, 1));
    }

    public void over(boolean isOver){
        this.imgSelection.setVisible(isOver);
    }
    public void updateBar() {
        if (this.p.estUneculture(this.y, this.x)) {
            this.progressionPousse.setValue(this.p.getDeveloppement(this.y, this.x));
            if(this.p.getDeveloppement(this.y, this.x) == 100){
                this.progressionPousse.setForeground(new Color(0x69B00B));
            }else{
                this.progressionPousse.setForeground(new Color(0xc9b48d));
            }

            this.progressionMort.setValue(this.p.niveauDeSurvie(this.y, this.x));
            afficherBarMort(this.p.niveauDeSurvie(this.y, this.x) > 0);
            if(this.p.niveauDeSurvie(this.y, this.x) > 100){
                afficherPlanteMorte();
            }
        }
        //afficherBarPlante(this.p.estUneculture(this.y, this.x));
    }

    public void afficherBarPlante(boolean bool) {
        this.afficherBarPlante = bool;
        this.progressionPousse.setVisible(bool);
        this.imgPlante.setVisible(bool);
        if(bool){
            //on change de plante
            this.imgPlante.changerImg(p.getIdPlante(y,x));

            //on change de sol
            this.imgFond.changerImg("sol_culture");
            this.iconBesoin.setVisible(true);
        }else {
            //on change de sol
            this.imgFond.changerImg("sol_case");
            afficherBarMort(false);
            this.iconBesoin.setVisible(false);
        }
    }
    public void afficherPlanteMorte() {
        this.progressionPousse.setVisible(false);
        this.progressionMort.setVisible(false);
        this.iconBesoin.setVisible(false);
        this.imgPlante.changerImg("plante_morte");
    }

    public void afficherBarMort(boolean bool) {
        this.afficherBarMort = bool;
        this.progressionMort.setVisible(bool);
    }
    public void afficherBarMort() {
        afficherBarMort(true);
    }

    public void afficherAmenagement(boolean bool){
        if(bool) {
            afficherBarPlante(false);
            afficherBarMort(false);
            this.iconBesoin.setVisible(false);

            this.imgFond.changerImgAmenagement(p.getIdAmenagement(y,x));
        }
    }

    public void updateBesoin(){
        this.iconBesoin.afficherBesoin(p.getBesoin(this.y, this.x));
    }

    public void update (){
        updateBar();
        updateBesoin();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == 1) { // clique gauche
            if (p.caseLibre(y, x)) {
                if (this.p.planterSelection(y, x)) afficherBarPlante(true);
            } else {
                if (p.estPoussee(y, x)) {
                    this.p.recolter(y, x);
                    afficherBarPlante(false);
                } else if (!p.estVivante(y, x)) {
                    this.p.arracher(y, x);
                    afficherBarPlante(false);
                    afficherPlanteMorte();
                }
            }
        }
        else{ // clique droit ou autre
            if(p.caseLibre(y, x)){
                if(this.p.placerAmenagementSelectionner(y,x)) afficherAmenagement(true);
            }
        }
    }

    public void rechargerImg(){
        this.imgFond.changerTaille(ew(100), eh(100));
        this.imgSelection.changerTaille(ew(100), eh(100));
        this.imgPlante.changerTaille(ew(100), eh(100));
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
