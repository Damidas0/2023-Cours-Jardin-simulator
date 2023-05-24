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
    private Vue v;// vue ref


    public CaseGraphique() {
        super();
        
        setBackground(Color.white);
        this.x=0;
        this.y=0;
        this.p = null;

        
        addMouseListener(this);
        
    }

    public CaseGraphique(int y, int x, Potager p, Vue v){
        super();
        this.x = x;
        this.y = y;
        this.p = p;
        this.v = v;

        setBackground(Color.WHITE);
        addMouseListener(this);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!p.estUneculture(y, x)){
            this.p.planter(new Plante(), y, x);
        } else if(p.estPoussee(y, x)){
            //TODO:récupérer le fruit du labeur de laterre (mettre dans un inventaire?)
            this.p.recolter(y, x);
        }
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        InfoPannel infoP = new InfoPannel();
        infoP.setVisible(true);
        v.add(infoP);
        
        System.out.println(e.getX() + " / " + e.getY());
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        //throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    
}

