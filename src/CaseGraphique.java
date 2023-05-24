/*package Jardin;*/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;


public class CaseGraphique extends JPanel implements MouseListener{
    private int x; 
    private int y;

    private Potager p; //pointeur sur le potager


    public CaseGraphique() {
        super();
        
        setBackground(Color.white);
        this.x=0;
        this.y=0;
        this.p = null;

        
        /*addMouseListener(new MouseAdapter() {
        
            /*@Override
            public void mouseEntered(MouseEvent arg0) {
                super.mouseClicked(arg0);
                setBackground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                super.mouseExited(arg0);
                setBackground(Color.red);
            }
            
            
            
        });*/
        
    }

    public CaseGraphique(int y, int x, Potager p){
        super();
        this.x = x;
        this.y = y;
        this.p = p;

        setBackground(Color.WHITE);
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

