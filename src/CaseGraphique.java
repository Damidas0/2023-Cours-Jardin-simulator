/*package Jardin;*/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.Border;

public class CaseGraphique extends JPanel implements MouseListener {
    private int x;
    private int y;

    private Potager p; //pointeur sur le potager
    private Vue v;// pointeur sur ka vue ref

    private JProgressBar progressBar;
    private boolean afficherBar;


    public CaseGraphique() {
        super();

        // couleur de fond
        setBackground(Color.BLUE);
        // bordure
        setBorderSimple();

        this.x = 0;
        this.y = 0;
        this.p = null;
    }

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
        add(this.progressBar, BorderLayout.SOUTH);

        afficherBar(this.p.estUneculture(this.y, this.x));
    }

    public void setBorderSimple(){
        Border blackline = BorderFactory.createLineBorder(Color.black,1);
        setBorder(blackline);
    }

    public void setBorderOver(){
        Border boldline = BorderFactory.createLineBorder(Color.black,3);
        setBorder(boldline);
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
