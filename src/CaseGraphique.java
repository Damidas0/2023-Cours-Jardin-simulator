/*package Jardin;*/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.Border;


public class CaseGraphique extends JPanel implements MouseListener{
    private int x; 
    private int y;

    private Potager p; //pointeur sur le potager


    public CaseGraphique() {
        super();

        //couleur de fond
        setBackground(Color.BLUE);
        //bordure
        setBorderSimple();

        this.x=0;
        this.y=0;
        this.p = null;

    }

    public CaseGraphique(int y, int x, Potager p){
        super();
        this.x = x;
        this.y = y;
        this.p = p;

        //couleur de fond
        setBackground(Color.WHITE);
        //bordure
        setBorderSimple();
    }

    public void setBorderSimple(){
        Border blackline = BorderFactory.createLineBorder(Color.black,1);
        setBorder(blackline);
    }

    public void setBorderOver(){
        Border boldline = BorderFactory.createLineBorder(Color.black,2);
        setBorder(boldline);
    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(!p.estUneculture(y, x)){
            this.p.planter(new Plante(), y, x);
        }
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    
}

