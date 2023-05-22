/*package Jardin;*/
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import java.awt.Color;
import java.awt.event.MouseAdapter;
import javax.swing.JPanel;

/**
 *
 * @author frederic
 */
public class Case extends JPanel implements Runnable{

    public Case() {
        super();
        
        setBackground(Color.white);
        Ordonnanceur.getOrdonnanceur().addRunable(this);
        
        addMouseListener(new MouseAdapter() {
        
            /*@Override
            public void mouseEntered(MouseEvent arg0) {
                super.mouseClicked(arg0);
                setBackground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
                super.mouseExited(arg0);
                setBackground(Color.red);
            }*/
            
            
            
        });
        
    }

    @Override
    public void run() {
        
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
    
}
